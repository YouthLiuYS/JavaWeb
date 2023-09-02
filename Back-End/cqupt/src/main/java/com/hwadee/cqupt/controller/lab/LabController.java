package com.hwadee.cqupt.controller.lab;

import com.hwadee.cqupt.entity.ApplyEntity;
import com.hwadee.cqupt.entity.LabEntity;
import com.hwadee.cqupt.entity.dto.ApplyDto;
import com.hwadee.cqupt.entity.dto.LabDto;
import com.hwadee.cqupt.entity.dto.ResultDto;
import com.hwadee.cqupt.service.IApplyService;
import com.hwadee.cqupt.service.ILabService;
import com.hwadee.cqupt.utils.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * 申请接口，为了简便，这里的申请和审批是同一个人，实际肯定是不同的角色
 * @author: lys
 * @date: 2023/8/4
 */
@Validated
@Api("实验室相关接口")
@RestController
@RequestMapping("lab")
public class LabController {

    @Resource
    private ILabService iLabService;

    /**
     * 通过实验室id，查询实验室列表
     * @return
     */
    @ApiOperation("1 实验室列表")
    @GetMapping("/listLabs")
    public ResultDto listLabs(Integer labNum, Integer applyStatus){
        return iLabService.listLabs(labNum, applyStatus);
    }

    @ApiOperation("2 新增实验室")
    @PostMapping("/insertLab")
    /**
     * 新增申请
     * @param applyDto
     * @return
     */
    public ResultDto insertLab(@RequestBody @Valid LabDto labDto){
        return iLabService.insertLab(labDto);
    }

    @ApiOperation("3 删除实验室")
    @GetMapping("/deleteLabById")
    /**
     * 删除申请
     * @param applyId
     * @return
     */
    public ResultDto deleteLabById(@NotNull Integer labId){
        return iLabService.deleteLabById(labId);
    }

    /**
     * 通过id，查询申请信息
     * @param labId
     * @return
     */
    @ApiOperation("4 查询实验室信息")
    @GetMapping("/getLabById")
    public ResultDto getLabById(@NotNull Integer labId){
        return iLabService.getLabById(labId);
    }

    @ApiOperation("5 编辑实验室")
    @PostMapping("/updateLab")
    /**
     * 新增申请
     * @param labDto
     * @return
     */
    public ResultDto updateLab(@RequestBody @Valid LabDto labDto){
        return iLabService.updateLab(labDto);
    }

    @GetMapping("/exportLab")
    public void exportLab(HttpServletResponse response, Integer labId, Integer applyStatus) throws IOException {
        List<LabEntity> list = iLabService.exportLab(labId,applyStatus);
        ExcelUtil.outputExcel(response,list,LabEntity.class,"实验室管理列表.xls");
    }
}
