package com.hwadee.cqupt.handler;

import com.hwadee.cqupt.entity.dto.ResultDto;
import com.hwadee.cqupt.enums.ResultCode;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理
 * @author: lys
 * @date: 2023/7/31
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数绑定异常，表单数据绑定到java bean出错时，会抛出BindException异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResultDto bindException(BindException e){
        FieldError fieldError = e.getFieldErrors().get(0);
        System.out.println("参数验证异常BindException："+fieldError.getDefaultMessage());
        return ResultDto.fail(ResultCode.PARAM_BIND_ERROR,ResultCode.PARAM_BIND_ERROR.getMessage(),fieldError.getDefaultMessage());
    }

    /**
     * 请求体解析并绑定到java bean出错时，会抛出MethodArgumentNotValidException异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultDto methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        FieldError fieldError = e.getFieldErrors().get(0);
        System.out.println("参数验证异常MethodArgumentNotValidException："+fieldError.getDefaultMessage());

        return ResultDto.fail(ResultCode.PARAM_IS_INVALID,ResultCode.PARAM_IS_INVALID.getMessage(),fieldError.getDefaultMessage());
    }

    /**
     * 普通参数（不是java bean）校验出错时，抛出ConstraintViolationException异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultDto ConstraintViolationExceptionHandler(ConstraintViolationException e){
        System.out.println("exception："+e.getMessage());
        return ResultDto.fail(ResultCode.PARAM_IS_INVALID,ResultCode.PARAM_IS_INVALID.getMessage(),e.getMessage());
    }

    /**
     * 服务器错误
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultDto Exception(Exception e){
        System.out.println("exception："+e.getMessage());
        return ResultDto.fail(ResultCode.SERVER_ERROR,ResultCode.SERVER_ERROR.getMessage(),e.getMessage());
    }

}
