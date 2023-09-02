package com.hwadee.cqupt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwadee.cqupt.entity.ApplyEntity;
import com.hwadee.cqupt.entity.dto.ApplyDto;
import com.hwadee.cqupt.entity.dto.ResultDto;
import com.hwadee.cqupt.enums.ResultCode;
import com.hwadee.cqupt.mapper.IApplyMapper;
import com.hwadee.cqupt.service.IApplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 申请业务逻辑实现类
 * @author: lys
 * @date: 2023/8/4
 */
@Service
public class ApplyServiceImpl implements IApplyService {

    @Resource
    private IApplyMapper iApplyMapper;

    @Override
    public ResultDto listApplysByUserId(Integer userId, Integer applyNum, Integer applyStatu) {
//        QueryWrapper<ApplyEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_id",userId);
//        queryWrapper.orderByAsc("apply_datetime");

//        List<ApplyEntity> listApplys = iApplyMapper.selectList(queryWrapper);
        QueryWrapper<ApplyEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        if (null != applyNum){
            // 模糊查询 like '%user_num' = #(applyNum)
            queryWrapper.like("apply_num",applyNum);
        }
        if (null != applyStatu){
            queryWrapper.eq("apply_status",applyStatu);
        }
        List<ApplyEntity> listApplys = iApplyMapper.listApplys(queryWrapper);
        return ResultDto.success(listApplys);
    }

    @Override
    public ResultDto insertApply(ApplyDto applyDto) {
        ApplyEntity applyEntity = new ApplyEntity();
        BeanUtils.copyProperties(applyDto,applyEntity);

        int num = iApplyMapper.insert(applyEntity);

        if (num > 0){
            return ResultDto.success("新增申请成功","新增申请成功");
        }

        return ResultDto.fail(ResultCode.SERVER_ERROR,"新增审批失败","新增审批失败");
    }

    @Override
    public ResultDto deleteApplyById(Integer applyId) {

        if((iApplyMapper.selectById(applyId)).getApplyStatus() == 0) {
            int num = iApplyMapper.deleteById(applyId);
            if (num > 0) {
                return ResultDto.success("删除申请成功", "删除申请成功");
            }
        }

        return ResultDto.fail(ResultCode.SERVER_ERROR,"删除审批失败","删除审批失败");
    }

    @Override
    public ResultDto reviewApply(ApplyDto applyDto) {
        ApplyEntity applyEntity = new ApplyEntity();
        BeanUtils.copyProperties(applyDto,applyEntity);
        int num = iApplyMapper.updateById(applyEntity);
        if (num > 0){
            return ResultDto.success("审批申请成功","审批申请成功");
        }

        return ResultDto.fail(ResultCode.SERVER_ERROR,"审批失败","审批失败");
    }

    @Override
    public ResultDto getApplyById(Integer applyId) {
        return ResultDto.success(iApplyMapper.selectById(applyId));
    }

    @Override
    public ResultDto updateApply(ApplyDto applyDto) {
        ApplyEntity applyEntity = new ApplyEntity();
        BeanUtils.copyProperties(applyDto,applyEntity);

        int num = iApplyMapper.updateById(applyEntity);

        if (num > 0){
            return ResultDto.success("修改申请成功","修改申请成功");
        }

        return ResultDto.fail(ResultCode.SERVER_ERROR,"修改申请失败","修改申请失败");
    }

    @Override
    public ResultDto submitApply(ApplyDto applyDto) {
        ApplyEntity applyEntity = new ApplyEntity();
        BeanUtils.copyProperties(applyDto,applyEntity);
        if (applyEntity.getApplyStatus() != 0){
            return ResultDto.success("申请状态不为未审核状态","申请状态不为未审核状态");
        }

        applyEntity.setApplyStatus(3);
        int num = iApplyMapper.updateById(applyEntity);

        if (num > 0){
            return ResultDto.success("提交申请成功","提交申请成功");
        }

        return ResultDto.fail(ResultCode.SERVER_ERROR,"提交申请失败","提交申请失败");
    }

    @Override
    public ResultDto withDrawApply(Integer applyId) {
        ApplyEntity applyEntity = iApplyMapper.selectById(applyId);
        if (applyEntity.getApplyStatus() != 3){
            return ResultDto.success("申请状态不为未审核状态","申请状态不为未审核状态");
        }
        applyEntity.setApplyStatus(4);
        int num = iApplyMapper.updateById(applyEntity);
        if (num > 0){
            return ResultDto.success("撤回申请成功","撤回申请成功");
        }

        return ResultDto.fail(ResultCode.SERVER_ERROR,"撤回申请失败","撤回申请失败");
    }

    @Override
    public ResultDto listApplys(){
        QueryWrapper<ApplyEntity> queryWrapper = new QueryWrapper<>();
        List<ApplyEntity> listApplys = iApplyMapper.listApplys(queryWrapper);
        return ResultDto.success(listApplys);
    }

    @Override
    public ResultDto listApplysTeacher(){
        QueryWrapper<ApplyEntity> queryWrapper = new QueryWrapper<>();
        List<ApplyEntity> listApplys = iApplyMapper.listApplysTeacher(queryWrapper);
        return ResultDto.success(listApplys);
    }

    @Override
    public ResultDto listApplysTeacherByInfor(Integer applyNum, Integer applyStatu){
        QueryWrapper<ApplyEntity> queryWrapper = new QueryWrapper<>();
        if (null != applyNum){
            // 模糊查询 like '%user_num' = #(applyNum)
            queryWrapper.like("apply_num",applyNum);
        }
        if (null != applyStatu){
            queryWrapper.eq("apply_status",applyStatu);
        }
        queryWrapper.ne("apply_status",4);
        queryWrapper.ne("apply_status",0);
        List<ApplyEntity> listApplys = iApplyMapper.listApplysTeacher(queryWrapper);
        return ResultDto.success(listApplys);
    }

    @Override
    public List<ApplyEntity> exportApply(Integer userId, Integer applyNum, Integer applyStatus) {
        QueryWrapper<ApplyEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        if(null != applyNum){
            //模糊查询  like '%user_num%' = #{applyNum}
            queryWrapper.like("apply_num",applyNum);
        }
        if(null != applyStatus){
            queryWrapper.eq("apply_status",applyStatus);
        }
        return iApplyMapper.listApplys(queryWrapper);
    }

}
