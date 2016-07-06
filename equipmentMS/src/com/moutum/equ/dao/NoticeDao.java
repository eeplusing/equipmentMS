package com.moutum.equ.dao;

import java.util.List;
import java.util.Map;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.Notice;

/************************************************************************************
 * @Title        : NoticeDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年4月2日 下午4:21:30
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface NoticeDao extends DaoSupport<Notice>
{

    int getRecordCount(Map<String, Object> map);

    List<Notice> getRecordList(int pageNum, int pageSize, Map<String, Object> map);

}