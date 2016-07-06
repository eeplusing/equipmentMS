package com.moutum.equ.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.util.DateUtil;
import com.moutum.equ.util.SystemConstants;
import com.moutum.equ.dao.NoticeDao;
import com.moutum.equ.dao.OperLogDao;
import com.moutum.equ.domain.Notice;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.NoticeDto;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.service.NoticeService;
import com.opensymphony.xwork2.ActionContext;



/************************************************************************************
 * @Title : NoticeServiceImpl.java
 * @Description :
 * @Author : BianWeiqing
 * @DateTime : 2015年3月9日 下午4:04:12
 * @Copyright : 2015 Moutum All Rights Reserved
 * @version : V1.0
 ************************************************************************************/
@Service
public class NoticeServiceImpl implements NoticeService
{
   
    @Resource NoticeDao noticeDao;
    
    @Resource OperLogDao operLogDao;
    
    @Override
    public PageBean getPage(int pageNum, int pageSize, Map<String, Object> map)
    {
        int recordCount = noticeDao.getRecordCount(map);
        List<Notice> recordList = noticeDao.getRecordList(pageNum, pageSize, map);
        List<NoticeDto> lt = new ArrayList<NoticeDto>();
        for(Notice n : recordList)
        {
            NoticeDto nd = new NoticeDto();
            nd.setNoticeId(n.getId());
            nd.setTitle(n.getTitle());
            nd.setContent(n.getContent());
            nd.setPublisher(n.getPublisher());
            nd.setNoticeTime(DateUtil.dateToString(n.getNoticeTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
            lt.add(nd);
            
        }
        
        return new PageBean(pageNum, pageSize, recordCount, lt);
    }

    @Override
    public int save(Notice notice)
    {
        try
        {
            noticeDao.save(notice);
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            OperLog operLog = new OperLog();
            operLog.setOperator(loginuser.getLoginAccount());
            operLog.setOperLogTime(new Date());
            operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
            operLog.setOperLogContent("发布公告：" + notice.getTitle());
            operLogDao.save(operLog);
            
            return 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(long noticeId)
    {
       
       Notice notice = noticeDao.getById(noticeId);
         
       if(null != notice)
       {
           try
           {
               noticeDao.delete(noticeId);
               
               User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
               
               OperLog operLog = new OperLog();
               operLog.setOperator(loginuser.getLoginAccount());
               operLog.setOperLogTime(new Date());
               operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
               operLog.setOperLogContent("删除公告：" + notice.getTitle());
               operLogDao.save(operLog);
               
               return 1;
           }
           catch(Exception e)
           {
               e.printStackTrace();
               return -1;
           }
       }
       return 0;
    }

    @Override
    public Notice select(long noticeId)
    {
        return noticeDao.getById(noticeId);
    }
}