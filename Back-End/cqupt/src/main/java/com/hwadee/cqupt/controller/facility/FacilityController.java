package com.hwadee.cqupt.controller.facility;


import com.hwadee.cqupt.entity.FacilityEntity;
import com.hwadee.cqupt.entity.LabEntity;
import com.hwadee.cqupt.entity.dto.FacilityDto;
import com.hwadee.cqupt.entity.dto.ResultDto;
import com.hwadee.cqupt.service.IFacilityService;
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


@Validated
@Api("仿真设备相关接口")
@RestController
@RequestMapping("facility")
public class FacilityController {
    @Resource
    private IFacilityService iFacilityService;

    @ApiOperation("1增加设备")
    @PostMapping("/insertFacility")
    public ResultDto insertFacility(@RequestBody @Valid FacilityDto facilityDto){
        return iFacilityService.insertFacility(facilityDto);
    }

    @ApiOperation("2删除设备")
    @GetMapping("/deleteFacility")
    public ResultDto deleteFacility(@NotNull Integer facilityId){
        return iFacilityService.deleteFacility(facilityId);
    }

    @ApiOperation("3更新设备")
    @PostMapping("/updateFacility")
    public ResultDto updateFacility(@RequestBody @Valid FacilityDto facilityDto){
        return iFacilityService.updateFacility(facilityDto);
    }

    @ApiOperation("4查询设备")
    @GetMapping("/listFacilities")
    public ResultDto listFacilities(Integer facilityId, String facilityName){
        return iFacilityService.listFacilities(facilityId,facilityName);
    }

    /**
     * 通过id，查询设备信息
     * @param facilityId
     * @return
     */
    @ApiOperation("5 查询设备信息")
    @GetMapping("/getFacilityById")
    public ResultDto getFacilityById(@NotNull Integer facilityId){
        return iFacilityService.getFacilityById(facilityId);
    }

    @GetMapping("/exportFacility")
    public void exportFacility(HttpServletResponse response, Integer facilityId) throws IOException {
        List<FacilityEntity> list = iFacilityService.exportFacility(facilityId);
        ExcelUtil.outputExcel(response,list,FacilityEntity.class,"仿真设备管理列表.xls");
    }

}
