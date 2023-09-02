package com.hwadee.cqupt.service;

import com.hwadee.cqupt.entity.ApplyEntity;
import com.hwadee.cqupt.entity.dto.ApplyDto;
import com.hwadee.cqupt.entity.dto.ResultDto;
import com.hwadee.cqupt.mapper.IApplyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 申请单元测试
 * @author: lys
 * @date: 2023/8/4
 */
@SpringBootTest
public class ApplyServiceImplTest {

    @Resource
    private IApplyService iApplyService;

    @Test
    public void listApplysByUserId(){
        ResultDto resultDto = iApplyService.listApplysByUserId(1,1,1);
        System.out.println(resultDto.getCode());
        System.out.println(resultDto.getMessage());
        System.out.println(resultDto.getData());
    }

    @Test
    public void insertApply(){
        ApplyDto applyDto = new ApplyDto();
        applyDto.setUserId(1);
        applyDto.setApplyNum(123);
        applyDto.setApplyDay(2);
        applyDto.setApplyDatetime(LocalDateTime.now());
        applyDto.setApplyStatus(0);
        applyDto.setApplyReason("上课");

        ResultDto resultDto = iApplyService.insertApply(applyDto);
        System.out.println(resultDto.getCode());
        System.out.println(resultDto.getMessage());
        System.out.println(resultDto.getData());
    }
    @Test
    public void deleteById(){
        ResultDto resultDto = iApplyService.deleteApplyById(1);
        System.out.println(resultDto.getCode());
        System.out.println(resultDto.getMessage());
        System.out.println(resultDto.getData());
    }

    @Test
    public void reviewApply(){
        ApplyDto applyDto = new ApplyDto();
        applyDto.setApplyId(2);
        applyDto.setUserId(1);
        applyDto.setApplyNum(123);
        applyDto.setApplyDay(2);
        applyDto.setApplyDatetime(LocalDateTime.now());
        applyDto.setApplyStatus(0);
        applyDto.setApplyReason("上课");
        applyDto.setReviewComments("同意");
        applyDto.setReviewDatetime(LocalDateTime.now());
        ResultDto resultDto = iApplyService.reviewApply(applyDto);
        System.out.println(resultDto.getCode());
        System.out.println(resultDto.getMessage());
        System.out.println(resultDto.getData());
    }

    @Test
    public void getApplyById(){
        ResultDto resultDto = iApplyService.getApplyById(8);
        System.out.println(resultDto.getCode());
        System.out.println(resultDto.getMessage());
        System.out.println(resultDto.getData());
    }

    @Resource
    private IApplyMapper iApplyMapper;

    @Test
    public void t(){
//        List<ApplyEntity> list = iApplyMapper.listApplys(1);
//        System.out.println(list.size());
//        System.out.println(list.get(0).getUserEntity().getName());
    }
}
