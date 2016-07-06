package com.moutum.equ.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.dao.IodepotDao;
import com.moutum.equ.domain.Iodepot;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.dto.IodepotDto;
import com.moutum.equ.service.IodepotService;
import com.moutum.equ.util.DateUtil;

/************************************************************************************
 * @Title        : IodepotServiceImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年4月22日 上午11:04:34
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Service
public class IodepotServiceImpl implements IodepotService
{
    @Resource IodepotDao iodepotDao;
    
    @Override
    public PageBean getPage(int pageNum, int pageSize, String sparepartNo)
    {
        int recordCount = iodepotDao.getRecordCount(sparepartNo);
        List<Iodepot> recordList = iodepotDao.getRecordList(pageNum, pageSize, sparepartNo);
        List<IodepotDto> list = new ArrayList<IodepotDto>();
        for(Iodepot iodepot : recordList)
        {
            IodepotDto iodepotDto = new IodepotDto();
            iodepotDto.setGoodId(iodepot.getGoodId());
            iodepotDto.setIodepotGetter(iodepot.getIodepotGetter());
            iodepotDto.setIodepotId(iodepot.getIodepotId());
            iodepotDto.setIodepotOperator(iodepot.getIodepotOperator());
            iodepotDto.setIodepotOpertype(iodepot.getIodepotOpertype());
            iodepotDto.setIodepotRemark(iodepot.getIodepotRemark());
            iodepotDto.setIodepotReturner(iodepot.getIodepotReturner());
            iodepotDto.setIodepotTime(DateUtil.dateToString(iodepot.getIodepotTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
            iodepotDto.setTypeId(iodepot.getTypeId());
            iodepotDto.setRetainField1(iodepot.getRetainField1());
            iodepotDto.setRetainField2(iodepot.getRetainField1());
            iodepotDto.setRetainField3(iodepot.getRetainField1());
            list.add(iodepotDto);
        }
        
        return new PageBean(pageNum, pageSize, recordCount, list);
    }
}