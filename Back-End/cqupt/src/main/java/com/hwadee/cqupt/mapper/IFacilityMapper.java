package com.hwadee.cqupt.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwadee.cqupt.entity.FacilityEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IFacilityMapper extends BaseMapper<FacilityEntity> {

    /**
     * 一对一查询。把设备一起查出来
     * @param
     * @return
     */
    @Select("select * from facility ${ew.customSqlSegment}")
    @Results(id="facilityMap",value = {
            @Result(property = "facilityId",column = "facility_id"),
            @Result(property = "facilityName",column = "facility_name"),
            @Result(property = "facilityType",column = "facility_type"),
            @Result(property = "labId",column = "lab_id"),
            @Result(property = "labNum",column = "lab_num"),
            @Result(property = "createDate",column = "create_date"),
            @Result(property = "updateDate",column = "update_date")
    })
    List<FacilityEntity> listFacilities(@Param("ew") QueryWrapper queryWrapper);
}
