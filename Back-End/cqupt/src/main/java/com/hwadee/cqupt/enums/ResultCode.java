package com.hwadee.cqupt.enums;

/**
 * 接口状态码的约束，为了全局统一
 * @author: lys
 * @date: 2023/7/31
 */
public enum ResultCode {

    SUCCESS(200,"成功"),

    SERVER_ERROR(500,"服务器错误"),

    USER_ACCOUNT_NOTFOUND(1001,"用户名不存在"),
    USER_ACCOUNT_EXISTS(1002,"用户名已经存在"),
    USER_LOGIN_NOTFOUND(1003,"用户名和密码不匹配"),
    USER_REGISTER_FAIL(1004,"用户注册失败"),

    PARAM_IS_INVALID(2001,"参数无效"),
    PARAM_BIND_ERROR(2002,"参数绑定错误");

    private Integer code;

    private String message;

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

    ResultCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
