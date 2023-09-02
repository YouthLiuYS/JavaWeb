package com.hwadee.cqupt.service;

import com.hwadee.cqupt.entity.dto.ResultDto;
import com.hwadee.cqupt.entity.dto.UserDto;
import com.hwadee.cqupt.entity.dto.UserLoginDto;

/**
 * 用户相关业务逻辑
 * @author: lys
 * @date: 2023/8/1
 */
public interface IUserService {

    /**
     * 用户注册
     * @param userDto
     * @return
     */
    ResultDto userRegister(UserDto userDto);

    /**
     * 检查用户名是否存在
     * @param account
     * @return
     */
    ResultDto checkAccount(String account);

    /**
     * 用户登录
     * @param userLoginDto
     * @return
     */

    ResultDto userLogin(UserLoginDto userLoginDto);

    /**
     * 用户注销
     * @param userDto
     * @return
     */


}
