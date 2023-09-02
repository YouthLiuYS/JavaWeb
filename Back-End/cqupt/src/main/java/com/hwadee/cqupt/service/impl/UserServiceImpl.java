package com.hwadee.cqupt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwadee.cqupt.entity.MenuEntity;
import com.hwadee.cqupt.entity.StudentEntity;
import com.hwadee.cqupt.entity.UserEntity;
import com.hwadee.cqupt.entity.dto.ResultDto;
import com.hwadee.cqupt.entity.dto.UserDto;
import com.hwadee.cqupt.entity.dto.UserLoginDto;
import com.hwadee.cqupt.enums.ResultCode;
import com.hwadee.cqupt.enums.UserType;
import com.hwadee.cqupt.mapper.IMenuMapper;
import com.hwadee.cqupt.mapper.IStudentMapper;
import com.hwadee.cqupt.mapper.IUserMapper;
import com.hwadee.cqupt.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户业务逻辑实现类
 * @author: lys
 * @date: 2023/8/1
 */
//用在类上面，则类里面的所有方法都开启事务
//@Transactional
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserMapper iUserMapper;

    @Resource
    private IMenuMapper iMenuMapper;

    @Resource
    private IStudentMapper iStudentMapper;

    //@Transactional失效的几种情况
    //1 用在非public修饰的方法上
    //2 propagation设置错误
    //3 同一个类中的方法调用
    //4 异常被捕获
    //5 数据库不支持事务

    //事务
    //该方法方法开启事务
    @Transactional
    @Override
    public ResultDto userRegister(UserDto userDto) {
        ResultDto resultDto = new ResultDto();
        UserEntity userEntity = new UserEntity();
        //拷贝对象
        BeanUtils.copyProperties(userDto,userEntity);
        //1如果是学生注册，那么年纪不能大于35岁
        if (userEntity.getUserType() == UserType.STUDENT.getCode()){
            if (userEntity.getAge() > 35){
                return ResultDto.fail(ResultCode.USER_REGISTER_FAIL,"注册失败，学生年龄超过35岁",null);
            }
        }

        //调用持久层的新增用户方法
        int num = iUserMapper.insert(userEntity);

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setStuName(userEntity.getName());
        studentEntity.setAge(userEntity.getAge());
        studentEntity.setUserId(userEntity.getUserId());
        //新增用户业务表，如果是学生，则插入student
        int numStu = iStudentMapper.insert(studentEntity);


        if (num > 0){
            return ResultDto.success(userEntity);
        }
        return ResultDto.fail(ResultCode.USER_REGISTER_FAIL,"",null);
    }

    @Override
    public ResultDto checkAccount(String account) {
        //Todo 调用持久层验证方法
        //查询条件对象
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>();
        //通过用户名查询
        queryWrapper.eq("account",account);
        UserEntity userEntity = iUserMapper.selectOne(queryWrapper);
        //如果查询结果为空，则说明用户名不存在
        if (null == userEntity){
            return ResultDto.success(ResultCode.USER_ACCOUNT_NOTFOUND,ResultCode.USER_ACCOUNT_NOTFOUND.getMessage(),false);
        }

        //如果查询结果不为空，则说明用户名存在
        return ResultDto.success(ResultCode.USER_ACCOUNT_EXISTS,ResultCode.USER_ACCOUNT_EXISTS.getMessage(),true);
    }

    @Override
    public ResultDto userLogin(UserLoginDto userLoginDto) {
        //1拷贝对象
//        UserEntity userEntity = new UserEntity();
//        BeanUtils.copyProperties(userLoginDto,userEntity);
        //2取值
        String account = userLoginDto.getAccount();
        String password = userLoginDto.getPassword();

        //调用持久层用户登录逻辑
        //查询条件对象
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>();
        queryWrapper.eq("account",account).eq("password",password);
        UserEntity userEntity = iUserMapper.selectOne(queryWrapper);

        //如果未查询到用户，则说明用户名和密码不匹配
        if (null == userEntity){
            return ResultDto.fail(ResultCode.USER_LOGIN_NOTFOUND,ResultCode.USER_LOGIN_NOTFOUND.getMessage(),null);
        }

        //通过用户id，查询菜单集合
        //iMenuMapper.listMenuByUserId(userEntity.getUserId());
        List<MenuEntity> menuList = iMenuMapper.listMenuByUserId(userEntity.getUserId());
        userEntity.setMenuList(menuList);
        //如果查询到用户，则登录成功，返回userEntity对象
        return ResultDto.success("登录成功",userEntity);
    }
}
