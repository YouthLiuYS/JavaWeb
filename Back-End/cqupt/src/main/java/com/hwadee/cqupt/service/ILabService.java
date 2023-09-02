package com.hwadee.cqupt.service;

import com.hwadee.cqupt.entity.ApplyEntity;
import com.hwadee.cqupt.entity.LabEntity;
import com.hwadee.cqupt.entity.dto.LabDto;
import com.hwadee.cqupt.entity.dto.ResultDto;
import com.hwadee.cqupt.utils.ExcelUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * 实验室的业务逻辑
 * @author: lys
 * @date: 2023/8/4
 */
public interface ILabService {

    /**
     * 通过实验室id，查询实验室列表
     * @return
     */
    public ResultDto listLabs(Integer labNum, Integer applyStatus);

    /**
     * 新增申请
     * @param labDto
     * @return
     */
    public ResultDto insertLab(@RequestBody @Valid LabDto labDto);

    /**
     * 删除申请
     * @param labId
     * @return
     */
    public ResultDto deleteLabById(@NotNull Integer labId);

    /**
     * 通过id，查询申请信息
     * @param labId
     * @return
     */
    public ResultDto getLabById(@NotNull Integer labId);

    /**
     * 新增申请
     * @param labDto
     * @return
     */
    public ResultDto updateLab(@RequestBody @Valid LabDto labDto);

    public List<LabEntity> exportLab( Integer labId, Integer applyStatus);
}
