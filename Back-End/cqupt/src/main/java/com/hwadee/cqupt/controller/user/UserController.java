package com.hwadee.cqupt.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwadee.cqupt.entity.UserEntity;
import com.hwadee.cqupt.entity.dto.ResultDto;
import com.hwadee.cqupt.entity.dto.UserDto;
import com.hwadee.cqupt.entity.dto.UserLoginDto;
import com.hwadee.cqupt.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 控制层，接收前端数据，返回数据给前端，同时做一些数据校验
 * 用户相关的接口，包括：用户注册、用户登录、用户注销等
 * 接口的参数如果大于5个，建议使用dto
 * @author: lys
 * @date: 2023/7/29
 */
@Validated
@Api("用户相关的接口")
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private IUserService iUserService;
    /**
     * 用户注册，用户名、密码、姓名这几个属性不能为空
     * @param userDto
     * @return
     */
    @ApiOperation("1用户注册")
    @PostMapping("/userRegister")
    public ResultDto userRegister(@RequestBody @Valid UserDto userDto){
        ResultDto resultDto = new ResultDto();
        //Todo 调用service注册逻辑
        //模拟学生注册
        return iUserService.userRegister(userDto);

    }

    /**
     * 检查用户名是否存在
     * @param account
     * @return
     */
    @ApiOperation(value = "2检查用户名是否存在", notes = "用户名存在则返回true，用户名不存在，返回false")
    @GetMapping("/checkAccount")
    public ResultDto checkAccount(@NotBlank(message = "用户名不能为空")  String account){
        //Todo 调用service验证逻辑

        return ResultDto.success(iUserService.checkAccount(account));
    }

    /**
     * 用户登录
     * @param userLoginDto
     * @return
     */
    @ApiOperation("3用户登录")
    @PostMapping("/userLogin")
    public ResultDto userLogin(@RequestBody @Valid UserLoginDto userLoginDto){
        //Todo 调用service登录逻辑
        return iUserService.userLogin(userLoginDto);
    }

    /**
     * 用户注销
     * @param userDto
     * @return
     */
    @ApiOperation("4用户注销")
    @PostMapping("/userLoginOut")
    public ResultDto userLoginOut(@Valid UserDto userDto){
        return null;
    }
}
