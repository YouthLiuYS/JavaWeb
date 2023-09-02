package com.hwadee.cqupt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwadee.cqupt.entity.MenuEntity;
import com.hwadee.cqupt.entity.UserEntity;
import com.hwadee.cqupt.entity.dto.ResultDto;
import com.hwadee.cqupt.entity.dto.UserDto;
import com.hwadee.cqupt.entity.dto.UserLoginDto;
import com.hwadee.cqupt.mapper.IMenuMapper;
import com.hwadee.cqupt.mapper.IUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 用户业务逻辑实现类
 * @author: lys
 * @date: 2023/8/1
 */
@SpringBootTest
public class UserServiceImplTest {

    @Resource
    private IUserService iUserService;

    @Resource
    private IMenuMapper iMenuMapper;

    @Resource
    private IUserMapper iUserMapper;

    @Test
    public void userRegister(){
        UserDto userDto = new UserDto();
        userDto.setName("王五");
        userDto.setAccount("wangwu");
        userDto.setPassword("123");
        userDto.setUserType(1);
        userDto.setAge(19);
        userDto.setEmail("123132@qq.com");
        userDto.setCreateDate(LocalDate.now());
        userDto.setUpdateDate(LocalDate.now());
        userDto.setPhone("19932381409");
        userDto.setRemark("A nice person");
        ResultDto resultDto = iUserService.userRegister(userDto);
        System.out.println(resultDto.getCode());
        System.out.println(resultDto.getMessage());
    }

    @Test
    public void checkAccount(){
        String account = "李四";
        ResultDto resultDto = iUserService.checkAccount(account);
        System.out.println(resultDto.getCode());
        System.out.println(resultDto.getMessage());
        System.out.println(resultDto.getData());
    }

    @Test
    public void userLogin(){
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setAccount("lisi");
        userLoginDto.setPassword("123");
        ResultDto resultDto = iUserService.userLogin(userLoginDto);
        System.out.println(resultDto.getCode());
        System.out.println(resultDto.getMessage());
        System.out.println(resultDto.getData());
    }

    @Test
    public void listMenuByUseId(){
        List<MenuEntity> menuList = iMenuMapper.listMenuByUserId(1);
        System.out.println(menuList.size());
    }

    @Test
    public void selectUserAndMenus(){
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("account","lisi").eq("password","123123");

        //不查询某些字段，只有mybatis plus自己封装的方法适用
        queryWrapper.select(UserEntity.class,info -> !info.getColumn().equals("password") && !info.getColumn().equals("createdate"));
        UserEntity userEntity = iUserMapper.selectUserAndMenus(queryWrapper);
        System.out.println(userEntity);

        //select account,.... from user where ..
        userEntity = iUserMapper.selectOne(queryWrapper);
        System.out.println(userEntity);

    }
}
