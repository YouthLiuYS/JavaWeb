package com.hwadee.cqupt.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 申请实体类
 * @author: lys
 * @date: 2023/8/4
 */
public class ApplyDto {

    //申请id
    private Integer applyId;
    @NotNull
    //申请编号
    private Integer applyNum;
    @NotNull
    //申请天数
    private Integer applyDay;
    //申请原因
    private String applyReason;
    //申请状态
    //0：待审批   1：审批通过   2：审批不通过
    private Integer applyStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //申请时间
    private LocalDateTime applyDatetime;
    //审批意见
    private String reviewComments;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //审批时间
    private LocalDateTime reviewDatetime;
    //用户id
    private Integer userId;

    public ApplyDto() {
    }

    public ApplyDto(Integer applyId, Integer applyNum, Integer applyDay, String applyReason, Integer applyStatus, LocalDateTime applyDatetime, String reviewComments, LocalDateTime reviewDatetime, Integer userId) {
        this.applyId = applyId;
        this.applyNum = applyNum;
        this.applyDay = applyDay;
        this.applyReason = applyReason;
        this.applyStatus = applyStatus;
        this.applyDatetime = applyDatetime;
        this.reviewComments = reviewComments;
        this.reviewDatetime = reviewDatetime;
        this.userId = userId;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
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

    @Override
    public String toString() {
        return "ApplyDto{" +
                "applyId=" + applyId +
                ", applyNum=" + applyNum +
                ", applyDay=" + applyDay +
                ", applyReason='" + applyReason + '\'' +
                ", applyStatus=" + applyStatus +
                ", applyDatetime=" + applyDatetime +
                ", reviewComments='" + reviewComments + '\'' +
                ", reviewDatetime=" + reviewDatetime +
                ", userId=" + userId +
                '}';
    }
}
