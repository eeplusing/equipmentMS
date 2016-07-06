package com.moutum.equ.service;

import java.util.Map;

import com.moutum.equ.domain.Notice;

import com.moutum.equ.dto.PageBean;

/************************************************************************************
 * @Title        : NoticeService.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 下午4:03:30
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface NoticeService
{
    
    PageBean getPage(int pageNum, int pageSize, Map<String, Object> map);
    
    int save(Notice notice);

    int delete(long Id);
    
    Notice select(long noticeId);
}