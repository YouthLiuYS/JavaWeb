package com.hwadee.cqupt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwadee.cqupt.entity.MenuEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 持久层
 * @author: lys
 * @date: 2023/8/11
 */
public interface IMenuMapper extends BaseMapper<MenuEntity> {

    /**
     * 通过用户id查询菜单
     * @param userId
     * @return
     */
    @Select("select * from menu m join role_menu_mapping rm on rm.menu_id = m.menu_id JOIN role r on r.role_id = rm.role_id " +
            "JOIN user_role_mapping ur on ur.role_id = r.role_id join user u on u.user_id=ur.user_id  " +
            "where u.user_id= #{userId}")
    List<MenuEntity> listMenuByUserId(Integer userId);
}
