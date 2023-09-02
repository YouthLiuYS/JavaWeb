package com.hwadee.cqupt.entity.dto;

import com.hwadee.cqupt.enums.ResultCode;

/**
 * 全局接口返回对象
 * @author: lys
 * @date: 2023/7/31
 */
public class ResultDto {
    //请求状态码
    private Integer code;
    //接口返回的消息
    private String message;
    //接口返回的数据，可以是：字符串、布尔、单个对象、集合等
    private Object data;

    //方法重载
    //成功
    public static ResultDto success(ResultCode resultCode,String message,Object data){
        return new ResultDto(resultCode.getCode(),message,data);
    }
    public static ResultDto success(String message,Object data){
        return new ResultDto(ResultCode.SUCCESS.getCode(),message,data);
    }
    public static ResultDto success(Object data){
        return new ResultDto(ResultCode.SUCCESS.getCode(),"",data);
    }

    //失败
    public static ResultDto fail(ResultCode resultCode,String message,Object data){
        return new ResultDto(resultCode.getCode(),message,data);
    }
    public static ResultDto fail(ResultCode resultCode,String message){
        return new ResultDto(resultCode.getCode(),message,null);
    }

    public ResultDto() {
    }

    public ResultDto(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
