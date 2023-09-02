package com.hwadee.cqupt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwadee.cqupt.entity.FacilityEntity;
import com.hwadee.cqupt.entity.LabEntity;
import com.hwadee.cqupt.entity.dto.FacilityDto;
import com.hwadee.cqupt.entity.dto.ResultDto;
import com.hwadee.cqupt.enums.ResultCode;
import com.hwadee.cqupt.mapper.IFacilityMapper;
import com.hwadee.cqupt.service.IFacilityService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FacilityServiceImpl implements IFacilityService {

    @Resource
    private IFacilityMapper iFacilityMapper;

    @Override
    public ResultDto insertFacility(FacilityDto facilityDto) {
        FacilityEntity facility = new FacilityEntity();
        BeanUtils.copyProperties(facilityDto,facility);

        int num = iFacilityMapper.insert(facility);

        if(num > 0){
            return ResultDto.success("新增设备成功","新增设备成功");
        }

        return ResultDto.fail(ResultCode.SERVER_ERROR,"新增设备失败","新增设备失败");
    }

    @Override
    public ResultDto deleteFacility(Integer facilityId) {
        int num = iFacilityMapper.deleteById(facilityId);

        if(num > 0){
            return ResultDto.success("删除设备成功","删除设备成功");
        }

        return ResultDto.fail(ResultCode.SERVER_ERROR,"删除设备失败","删除设备失败");
    }

    @Override
    public ResultDto updateFacility(FacilityDto facilityDto) {
        FacilityEntity facility = new FacilityEntity();
        BeanUtils.copyProperties(facilityDto,facility);

        int num = iFacilityMapper.updateById(facility);

        if(num > 0){
            return ResultDto.success("更新成功","更新成功");
        }

        return ResultDto.fail(ResultCode.SERVER_ERROR,"更新失败","更新失败");
    }

    @Override
    public ResultDto listFacilities(Integer facilityId, String facilityName) {
        QueryWrapper<FacilityEntity> queryWrapper = new QueryWrapper<>();
        if(facilityId!=null){
            queryWrapper.like("facility_id",facilityId);
        }
        if(facilityName!=null){
            queryWrapper.like("facility_name",facilityName);
        }
        List<FacilityEntity> list = iFacilityMapper.listFacilities(queryWrapper);
        return ResultDto.success(list);
    }

    @Override
    public ResultDto getFacilityById(Integer facilityId) {
        return ResultDto.success(iFacilityMapper.selectById(facilityId));
    }

    @Override
    public List<FacilityEntity> exportFacility(Integer facilityId) {
        QueryWrapper<FacilityEntity> queryWrapper = new QueryWrapper<>();

        return iFacilityMapper.listFacilities(queryWrapper);
    }
}
