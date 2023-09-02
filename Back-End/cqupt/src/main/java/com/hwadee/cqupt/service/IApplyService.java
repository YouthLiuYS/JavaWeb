package com.hwadee.cqupt.service;

import com.hwadee.cqupt.entity.ApplyEntity;
import com.hwadee.cqupt.entity.dto.ApplyDto;
import com.hwadee.cqupt.entity.dto.ResultDto;
import com.hwadee.cqupt.enums.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 申请的业务逻辑
 * @author: lys
 * @date: 2023/8/4
 */
public interface IApplyService {

    /**
     * 通过用户id，查询申请列表
     * @param userId
     * @return
     */
    ResultDto listApplysByUserId(Integer userId, Integer applyNum, Integer applyStatu);

    /**
     * 新增申请
     * @param applyDto
     * @return
     */
    public ResultDto insertApply(ApplyDto applyDto);

    /**
     * 删除申请
     * @param applyId
     * @return
     */
    public ResultDto deleteApplyById(Integer applyId);

    /**
     * 审批申请
     * @param applyDto
     * @return
     */
    public ResultDto reviewApply(ApplyDto applyDto);

    /**
     * 通过id，查询申请信息
     * @return
     */
    ResultDto getApplyById(Integer applyId);

    /**
     * 编辑申请信息
     * @return
     */
    public ResultDto updateApply(ApplyDto applyDto);

    /**
     * 提交申请信息
     * @return
     */
    public ResultDto submitApply(ApplyDto applyDto);

    /**
     * 提交申请
     * @param
     * @return
     */
    public ResultDto withDrawApply(@NotNull Integer applyId);

    /**
     * 列出所有申请
     * @return
     */
    public ResultDto listApplys();

    /**
     * 列出教师端所有申请
     * @return
     */
    public ResultDto listApplysTeacher();

    /**
     * 通过条件，列出教师端所有申请
     * @return
     */
    public ResultDto listApplysTeacherByInfor(Integer applyNum, Integer applyStatu);


    /**
     * 导出信息
     * @return
     */
    public List<ApplyEntity> exportApply(Integer userId, Integer applyNum, Integer applyStatus);
}
