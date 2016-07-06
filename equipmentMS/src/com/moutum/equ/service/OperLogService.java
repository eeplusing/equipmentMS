package com.moutum.equ.service;

import java.util.Map;

import com.moutum.equ.domain.OperLog;
import com.moutum.equ.dto.PageBean;

/************************************************************************************
 * @Title        : OperLogService.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 下午4:03:30
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface OperLogService
{
    PageBean getPage(int pageNum, int pageSize, Map<String, Object> map);

    void save(OperLog operLog);
}