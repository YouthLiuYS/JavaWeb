package com.hwadee.cqupt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwadee.cqupt.entity.ApplyEntity;
import com.hwadee.cqupt.entity.LabEntity;
import com.hwadee.cqupt.entity.dto.LabDto;
import com.hwadee.cqupt.entity.dto.ResultDto;
import com.hwadee.cqupt.enums.ResultCode;
import com.hwadee.cqupt.mapper.ILabMapper;
import com.hwadee.cqupt.service.ILabService;
import com.hwadee.cqupt.utils.ExcelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
@Service
public class LabServiceImpl implements ILabService {

    @Resource
    private ILabMapper iLabMapper;


    @Override
    public ResultDto listLabs(Integer labNum, Integer applyStatus) {
        QueryWrapper<LabEntity> queryWrapper = new QueryWrapper<>();
        if (null != labNum){
            // 模糊查询 like '%user_num' = #(applyNum)
            queryWrapper.like("lab_num",labNum);
        }
        if (null != applyStatus){
            queryWrapper.eq("apply_status",applyStatus);
        }
        List<LabEntity> listLabs = iLabMapper.listLabs(queryWrapper);
        return ResultDto.success(listLabs);
    }

    @Override
    public ResultDto insertLab(@RequestBody @Valid LabDto labDto){
        LabEntity labEntity = new LabEntity();
        BeanUtils.copyProperties(labDto,labEntity);

        int num = iLabMapper.insert(labEntity);

        if (num > 0){
            return ResultDto.success("新增实验室成功","新增实验室成功");
        }

        return ResultDto.fail(ResultCode.SERVER_ERROR,"新增实验室失败","新增实验室失败");
    }

    @Override
    public ResultDto deleteLabById(@NotNull Integer labId){

        int num = iLabMapper.deleteById(labId);

        if (num > 0){
            return ResultDto.success("删除实验室成功","删除实验室成功");
        }

        return ResultDto.fail(ResultCode.SERVER_ERROR,"删除实验室失败","删除实验室失败");
    }

    @Override
    public ResultDto getLabById(@NotNull Integer labId) {
        return ResultDto.success(iLabMapper.selectById(labId));
    }

    @Override
    public ResultDto updateLab(@RequestBody @Valid LabDto labDto){
        LabEntity labEntity = new LabEntity();
        BeanUtils.copyProperties(labDto,labEntity);

        int num = iLabMapper.updateById(labEntity);

        if (num > 0){
            return ResultDto.success("修改实验室成功","修改实验室成功");
        }

        return ResultDto.fail(ResultCode.SERVER_ERROR,"修改实验室失败","修改实验室失败");
    }

    @Override
    public List<LabEntity> exportLab( Integer labId, Integer applyStatus) {
        QueryWrapper<LabEntity> queryWrapper = new QueryWrapper<>();

        return iLabMapper.listLabs(queryWrapper);
    }
}
