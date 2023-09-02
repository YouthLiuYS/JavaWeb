package com.hwadee.cqupt.entity.dto;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录的dto
 * @author: lys
 * @date: 2023/7/29
 */
public class UserLoginDto {

    @NotBlank
    private String account;
    @NotBlank
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLoginDto() {
    }

    public UserLoginDto(String account, String password) {
        this.account = account;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginDto{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
