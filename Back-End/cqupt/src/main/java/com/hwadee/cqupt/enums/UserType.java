package com.hwadee.cqupt.enums;

/**
 * 用户类型约束，为了全局统一
 * @author: lys
 * @date: 2023/8/1
 */
public enum UserType {

    STUDENT(1,"学生"),
    TEACHER(2,"教师");

    private Integer code;

    private String userType;

    UserType(Integer code, String userType) {
        this.code = code;
        this.userType = userType;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
