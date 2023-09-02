package com.hwadee.cqupt.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 申请实体类
 * @author: lys
 * @date: 2023/8/4
 */
@TableName(value = "lab")
public class LabEntity {

    @ExcelProperty("实验室id")
    @TableId(value = "lab_id", type = IdType.AUTO)
    //申请id
    private Integer labId;
    @ExcelProperty("实验室编号")
    //申请编号
    private Integer labNum;
    @ExcelProperty("申请天数")
    //申请天数
    private Integer applyDay;
    @ExcelProperty("申请原因")
    //申请原因
    private String applyReason;
    @ExcelProperty("申请状态")
    //申请状态
    private Integer applyStatus;
    @ExcelProperty("申请时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //申请时间
    private LocalDateTime applyDatetime;
    @ExcelProperty("审批意见")
    //审批意见
    private String reviewComments;
    @ExcelProperty("审批时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //审批时间
    private LocalDateTime reviewDatetime;
    @ExcelIgnore
    //用户id
    private Integer userId;

    @ExcelIgnore
    @TableField(exist = false)
    private UserEntity userEntity;

    public LabEntity() {
    }

    public LabEntity(Integer labId, Integer labNum, Integer applyDay, String applyReason, Integer applyStatus, LocalDateTime applyDatetime, String reviewComments, LocalDateTime reviewDatetime, Integer userId, UserEntity userEntity) {
        this.labId = labId;
        this.labNum = labNum;
        this.applyDay = applyDay;
        this.applyReason = applyReason;
        this.applyStatus = applyStatus;
        this.applyDatetime = applyDatetime;
        this.reviewComments = reviewComments;
        this.reviewDatetime = reviewDatetime;
        this.userId = userId;
        this.userEntity = userEntity;
    }

    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    public Integer getLabNum() {
        return labNum;
    }

    public void setLabNum(Integer labNum) {
        this.labNum = labNum;
    }

    public Integer getApplyDay() {
        return applyDay;
    }

    public void setApplyDay(Integer applyDay) {
        this.applyDay = applyDay;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public LocalDateTime getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(LocalDateTime applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(String reviewComments) {
        this.reviewComments = reviewComments;
    }

    public LocalDateTime getReviewDatetime() {
        return reviewDatetime;
    }

    public void setReviewDatetime(LocalDateTime reviewDatetime) {
        this.reviewDatetime = reviewDatetime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "LabEntity{" +
                "labId=" + labId +
                ", labNum=" + labNum +
                ", applyDay=" + applyDay +
                ", applyReason='" + applyReason + '\'' +
                ", applyStatus=" + applyStatus +
                ", applyDatetime=" + applyDatetime +
                ", reviewComments='" + reviewComments + '\'' +
                ", reviewDatetime=" + reviewDatetime +
                ", userId=" + userId +
                ", userEntity=" + userEntity +
                '}';
    }
}
