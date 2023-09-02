package com.hwadee.cqupt.service;

import com.hwadee.cqupt.entity.FacilityEntity;
import com.hwadee.cqupt.entity.LabEntity;
import com.hwadee.cqupt.entity.dto.FacilityDto;
import com.hwadee.cqupt.entity.dto.ResultDto;
import com.hwadee.cqupt.utils.ExcelUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;


public interface IFacilityService {
    ResultDto insertFacility(FacilityDto facilityDto);
    ResultDto deleteFacility(Integer facilityId);
    ResultDto updateFacility(FacilityDto facilityDto);
    ResultDto listFacilities(Integer facilityId, String facilityName);


    public ResultDto getFacilityById(Integer facilityId);


    public List<FacilityEntity> exportFacility(Integer facilityId);

}
