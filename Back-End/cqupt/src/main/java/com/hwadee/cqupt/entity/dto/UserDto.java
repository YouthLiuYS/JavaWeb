package com.hwadee.cqupt.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * 用于user的数据传输
 * @author: lys
 * @date: 2023/7/29
 */
public class UserDto {

    @ApiModelProperty("用户id")
    //用户id
    private Integer userId;
    @NotBlank
    @Length(min = 3, max = 10, message = "账号长度3-10位")
    @ApiModelProperty("账号")
    //账号
    private String account;
    @NotBlank
    @Length(min = 6, message = "密码长度不能小于6位")
    @ApiModelProperty("密码")
    //密码
    private String password;
    @NotBlank
    @ApiModelProperty("姓名")
    //姓名
    private String name;
    @ApiModelProperty("电话")
    //电话
    private String phone;
    @Email(message = "请输入正确的邮箱格式")
    @ApiModelProperty("邮箱")
    //邮箱
    private String email;
    @ApiModelProperty("简介")
    //简介
    private String remark;
    @ApiModelProperty("类型")
    //用户类型
    private Integer userType;
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //创建日期
    private LocalDate createDate;
    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //更新日期
    private LocalDate updateDate;
    //年龄
    private Integer age;

    //无参构造方法
    public UserDto() {
    }

    //有参构造方法
    public UserDto(Integer userId, String account, String password, String name, String phone, String email, String remark, Integer userType, LocalDate createDate, LocalDate updateDate, Integer age) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.remark = remark;
        this.userType = userType;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.age = age;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", remark='" + remark + '\'' +
                ", userType=" + userType +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", age=" + age +
                '}';
    }
}
