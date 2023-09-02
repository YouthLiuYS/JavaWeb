package com.hwadee.cqupt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 用户实体
 * @author: lys
 * @date: 2023/7/29
 */
@ApiModel("用户实体")
@TableName("user")
public class UserEntity {

    @ApiModelProperty("用户id")
    //type = IdType.AUTO 定义主键自增长
    @TableId(value = "user_id",type = IdType.AUTO)
    //用户id
    private Integer userId;
    @NotNull(message = "账号不能为空")
    @ApiModelProperty("账号")
    //账号
    private String account;
    @ApiModelProperty("密码")
    //密码
    private String password;
    @ApiModelProperty("姓名")
    //姓名
    private String name;
    @ApiModelProperty("电话")
    //电话
    private String phone;
    @ApiModelProperty("邮箱")
    //邮箱
    private String email;
    @ApiModelProperty("简介")
    //简介
    private String remark;
    @ApiModelProperty("用户类型")
    @TableField(value = "user_type")
    //用户类型
    private Integer userType;
    @ApiModelProperty("创建时间")
    //创建日期
    private LocalDate createDate;
    @ApiModelProperty("更新时间")
    //更新日期
    private LocalDate updateDate;

    //年龄
    private Integer age;
    @TableField(exist = false)
    private List<MenuEntity> menuList;
    //无参构造方法
    public UserEntity() {
    }

    //有参构造方法


    public UserEntity(Integer userId, String account, String password, String name, String phone, String email, String remark, Integer userType, LocalDate createDate, LocalDate updateDate, Integer age) {
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

    public List<MenuEntity> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuEntity> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
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
                ", menuList=" + menuList +
                '}';
    }
}
