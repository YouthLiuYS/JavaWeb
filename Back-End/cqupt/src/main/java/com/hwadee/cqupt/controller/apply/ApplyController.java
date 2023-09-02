package com.hwadee.cqupt.controller.apply;

import com.hwadee.cqupt.entity.ApplyEntity;
import com.hwadee.cqupt.entity.dto.ApplyDto;
import com.hwadee.cqupt.entity.dto.ResultDto;
import com.hwadee.cqupt.service.IApplyService;
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
@Api("申请相关接口")
@RestController
@RequestMapping("apply")
public class ApplyController {

    @Resource
    private IApplyService iApplyService;
    /**
     * 通过用户id，查询申请列表
     * @param userId
     * @return
     */
    @ApiOperation("1 申请列表")
    @GetMapping("/listApplysByUserId")
    public ResultDto listApplysByUserId(@NotNull Integer userId, Integer applyNum, Integer applyStatus){
        return iApplyService.listApplysByUserId(userId,applyNum,applyStatus);
    }

    @ApiOperation("2 新增申请")
    @PostMapping("/insertApply")
    /**
     * 新增申请
     * @param applyDto
     * @return
     */
    public ResultDto insertApply(@RequestBody @Valid ApplyDto applyDto){
        return iApplyService.insertApply(applyDto);
    }

    @ApiOperation("3 删除申请")
    @GetMapping("/deleteApplyById")
    /**
     * 删除申请
     * @param applyId
     * @return
     */
    public ResultDto deleteApplyById(@NotNull Integer applyId){
       return iApplyService.deleteApplyById(applyId);
    }

    @ApiOperation("4 审批申请")
    @PostMapping("/reviewApply")
    /**
     *
     * 审批申请
     * @param applyDto
     * @return
     */
    public ResultDto reviewApply(@RequestBody @Valid ApplyDto applyDto){
        return iApplyService.reviewApply(applyDto);
    }


    /**
     * 通过id，查询申请信息
     * @param applyId
     * @return
     */
    @ApiOperation("5 查询申请信息")
    @GetMapping("/getApplyById")
    public ResultDto getApplyById(@NotNull Integer applyId){
        return iApplyService.getApplyById(applyId);
    }

    @ApiOperation("6 编辑申请")
    @PostMapping("/updateApply")
    /**
     * 新增申请
     * @param applyDto
     * @return
     */
    public ResultDto updateApply(@RequestBody @Valid ApplyDto applyDto){
        return iApplyService.updateApply(applyDto);
    }

    @ApiOperation("7 提交申请")
    @PostMapping("/submitApply")
    /**
     * 提交申请
     * @param applyDto
     * @return
     */
    public ResultDto submitApply(@RequestBody @Valid ApplyDto applyDto){
        return iApplyService.submitApply(applyDto);
    }


    @ApiOperation("8 撤回申请")
    @GetMapping("/withDrawApply")
    /**
     * 提交申请
     * @param applyDto
     * @return
     */
    public ResultDto withDrawApply(@NotNull Integer applyId){
        return iApplyService.withDrawApply(applyId);
    }

    /**
     * 列出所有申请
     * @return
     */
    @ApiOperation("9 列出所有申请")
    @GetMapping("/listApplys")
    public ResultDto listApplys(){
        return iApplyService.listApplys();
    }

    /**
     * 列出教师端所有申请
     * @return
     */
    @ApiOperation("9 列出教师端所有申请")
    @GetMapping("/listApplysTeacher")
    public ResultDto listApplysTeacher(){
        return iApplyService.listApplysTeacher();
    }

    /**
     * 通过条件，列出教师端所有申请
     * @return
     */
    @ApiOperation("10 列出教师端所有条件申请")
    @GetMapping("/listApplysTeacherByInfor")
    public ResultDto listApplysTeacherByInfor(Integer applyNum, Integer applyStatu){
        return iApplyService.listApplysTeacherByInfor(applyNum, applyStatu);
    }

    @GetMapping("/exportApply")
    public void exportApply(HttpServletResponse response,
                            @NotNull Integer userId,
                            Integer applyNum,
                            Integer applyStatus) throws IOException {
        List<ApplyEntity> list = iApplyService.exportApply(userId,applyNum,applyStatus);
        ExcelUtil.outputExcel(response,list,ApplyEntity.class,"申请列表.xls");
    }
}
