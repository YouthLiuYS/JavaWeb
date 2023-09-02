package com.hwadee.cqupt.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class FacilityDto {

    @TableId(value = "facility_id", type = IdType.AUTO)
    private Integer facilityId;
    @NotNull
    private String facilityName;
    @NotNull
    private Integer facilityType;
    @ExcelProperty("实验室id")

    //实验室id
    private Integer labId;
    @ExcelProperty("实验室编号")
    //实验室编号
    private Integer labNum;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")//以json字符串的方式提交数据
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//以表单的形式提交数据
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")//以json字符串的方式提交数据
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//以表单的形式提交数据
    private LocalDateTime updateDate;

    public FacilityDto() {

    }

    public FacilityDto(Integer facilityId, String facilityName, Integer facilityType, Integer labId, Integer labNum, LocalDateTime createDate, LocalDateTime updateDate) {
        this.facilityId = facilityId;
        this.facilityName = facilityName;
        this.facilityType = facilityType;
        this.labId = labId;
        this.labNum = labNum;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public Integer getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(Integer facilityType) {
        this.facilityType = facilityType;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "FacilityDto{" +
                "facilityId=" + facilityId +
                ", facilityName='" + facilityName + '\'' +
                ", facilityType=" + facilityType +
                ", labId=" + labId +
                ", labNum=" + labNum +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
