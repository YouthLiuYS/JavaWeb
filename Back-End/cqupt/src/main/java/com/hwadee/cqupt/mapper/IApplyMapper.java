package com.hwadee.cqupt.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwadee.cqupt.entity.ApplyEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 申请持久层
 * @author: lys
 * @date: 2023/8/4
 */
public interface IApplyMapper extends BaseMapper<ApplyEntity> {

    /**
     * 一对一查询。把申请和用户一起查出来
     * @param
     * @return
     */
    @Select("select * from apply where ${ew.SqlSegment}")
    @Results(id="applyMap",value = {
            @Result(property = "applyId",column = "apply_id"),
            @Result(property = "applyNum",column = "apply_num"),
            @Result(property = "applyDay",column = "apply_day"),
            @Result(property = "applyReason",column = "apply_reason"),
            @Result(property = "applyStatus",column = "apply_status"),
            @Result(property = "applyDatetime",column = "apply_datetime"),
            @Result(property = "reviewComments",column = "review_comments"),
            @Result(property = "reviewDatetime",column = "review_datetime"),
            @Result(property = "userId",column = "apply_id"),
            @Result(property = "userEntity",column = "user_id",one = @One(select = "com.hwadee.cqupt.mapper.IUserMapper.selectById"))
    })
    List<ApplyEntity> listApplys(@Param("ew") QueryWrapper queryWrapper);


    /**
     * 教师端一对一查询。把申请和用户一起查出来
     * @param
     * @return
     */
    @Select("select * from apply  ${ew.customSqlSegment}")
    @Results(id="applyMapTeacher",value = {
            @Result(property = "applyId",column = "apply_id"),
            @Result(property = "applyNum",column = "apply_num"),
            @Result(property = "applyDay",column = "apply_day"),
            @Result(property = "applyReason",column = "apply_reason"),
            @Result(property = "applyStatus",column = "apply_status"),
            @Result(property = "applyDatetime",column = "apply_datetime"),
            @Result(property = "reviewComments",column = "review_comments"),
            @Result(property = "reviewDatetime",column = "review_datetime"),
            @Result(property = "userId",column = "apply_id"),
            @Result(property = "userEntity",column = "user_id",one = @One(select = "com.hwadee.cqupt.mapper.IUserMapper.selectById"))
    })
    List<ApplyEntity> listApplysTeacher(@Param("ew") QueryWrapper queryWrapper);
}
