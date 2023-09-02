package com.hwadee.cqupt.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwadee.cqupt.entity.LabEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 实验室持久层
 * @author: lys
 * @date: 2023/8/4
 */
public interface ILabMapper extends BaseMapper<LabEntity> {
    /**
     * 一对一查询。把实验室和用户一起查出来
     * @param
     * @return
     */
//    and lab.lab_id = apply.lab_id
    @Select("select * from lab ${ew.customSqlSegment}")
    @Results(id="labMap",value = {
            @Result(property = "labId",column = "lab_id"),
            @Result(property = "labNum",column = "lab_num"),
            @Result(property = "applyDay",column = "apply_day"),
            @Result(property = "applyReason",column = "apply_reason"),
            @Result(property = "applyStatus",column = "apply_status"),
            @Result(property = "applyDatetime",column = "apply_datetime"),
            @Result(property = "reviewComments",column = "review_comments"),
            @Result(property = "reviewDatetime",column = "review_datetime"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "userEntity",column = "user_id",one = @One(select = "com.hwadee.cqupt.mapper.IUserMapper.selectById"))
    })
    List<LabEntity> listLabs(@Param("ew") QueryWrapper queryWrapper);
}
