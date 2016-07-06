package com.moutum.equ.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.dao.OperLogDao;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.dto.OperLogDto;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.service.OperLogService;
import com.moutum.equ.util.DateUtil;


/************************************************************************************
 * @Title : OperLogServiceImpl.java
 * @Description :
 * @Author : BianWeiqing
 * @DateTime : 2015年3月9日 下午4:04:12
 * @Copyright : 2015 Moutum All Rights Reserved
 * @version : V1.0
 ************************************************************************************/
@Service
public class OperLogServiceImpl implements OperLogService
{
   
    @Resource OperLogDao operLogDao;

    @Override
    public PageBean getPage(int pageNum, int pageSize, Map<String, Object> map)
    {
        int recordCount = operLogDao.getRecordCount(map);
        List<OperLog> recordList = operLogDao.getRecordList(pageNum, pageSize, map);
        List<OperLogDto> lt = new ArrayList<OperLogDto>();
        for(OperLog o : recordList)
        {
            OperLogDto od = new OperLogDto();
            od.setOperLogId(o.getOperLogId());
            od.setOperator(o.getOperator());
            od.setOperType(o.getOperType());
            od.setOperLogContent(o.getOperLogContent());
            od.setOperLogTime(DateUtil.dateToString(o.getOperLogTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
            od.setOperLogRemark(o.getOperLogRemark());
            lt.add(od);
            
        }
        return new PageBean(pageNum, pageSize, recordCount, lt);
    }

    @Override
    public void save(OperLog operLog)
    {
        operLogDao.save(operLog);
    }
}