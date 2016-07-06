package com.moutum.equ.dao;

import java.util.List;
import java.util.Map;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.OperLog;

/************************************************************************************
 * @Title        : OperLogDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午4:52:18
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface OperLogDao extends DaoSupport<OperLog>
{

    int getRecordCount(Map<String, Object> map);

    List<OperLog> getRecordList(int pageNum, int pageSize, Map<String, Object> map);
}