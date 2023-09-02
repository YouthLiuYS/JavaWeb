package com.hwadee.cqupt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

/**
 * 学生业务实体
 * @author: lys
 * @date: 2023/8/10
 */
@TableName(value = "student")
public class StudentEntity {

    @TableId(value = "stu_id",type = IdType.AUTO)
    private Integer stuId;
    private String stuName;
    @TableField(value = "stu_age")
    private Integer age;
    @TableField(value = "stu_sex")
    private Integer sex;
    private LocalDate createdate;//创建时间
    private LocalDate updatedate;//更新时间
    private Integer userId;

    public StudentEntity() {
    }

    public StudentEntity(Integer stuId, String stuName, Integer age, Integer sex, LocalDate createdate, LocalDate updatedate, Integer userId) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.age = age;
        this.sex = sex;
        this.createdate = createdate;
        this.updatedate = updatedate;
        this.userId = userId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public LocalDate getCreatedate() {
        return createdate;
    }

    public void setCreatedate(LocalDate createdate) {
        this.createdate = createdate;
    }

    public LocalDate getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(LocalDate updatedate) {
        this.updatedate = updatedate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", createdate=" + createdate +
                ", updatedate=" + updatedate +
                ", userId=" + userId +
                '}';
    }
}
