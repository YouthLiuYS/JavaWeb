package com.hwadee.cqupt.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwadee.cqupt.entity.UserEntity;
import org.apache.ibatis.annotations.*;

/**
 * 用户相关持久层
 * @author: lys
 * @date: 2023/8/1
 */
public interface IUserMapper extends BaseMapper<UserEntity> {

    //mybatis plus使用下面这种方式， QueryWrapper参数默认别名用：ew
    @Select("select * from user where ${ew.SqlSegment}")
    //一对多查询
    @Results({
            @Result(column = "user_id",property = "userId"),
            @Result(column = "user_id",property = "menuList",many = @Many(select = "com.hwadee.cqupt.mapper.IMenuMapper.listMenuByUserId"))
    })
    UserEntity selectUserAndMenus(@Param("ew") QueryWrapper queryWrapper);
}
