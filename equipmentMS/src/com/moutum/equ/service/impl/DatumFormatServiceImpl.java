package com.moutum.equ.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.dao.DatumFormatDao;
import com.moutum.equ.dao.OperLogDao;
import com.moutum.equ.domain.DatumFormat;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.service.DatumFormatService;
import com.moutum.equ.util.SystemConstants;
import com.opensymphony.xwork2.ActionContext;

/**
 * @Title        : DatumFormatServiceImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015-3-28 下午05:14:14
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 */
@Service
public class DatumFormatServiceImpl implements DatumFormatService
{
    @Resource DatumFormatDao datumFormatDao;
    
    @Resource OperLogDao operLogDao;
    
    @Override
    public int delete(int datumFormatId)
    {
        try
        {
            if(datumFormatDao.getById((long)datumFormatId) != null)
            {
                datumFormatDao.delete((long)datumFormatId);
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("修改文档格式：" + datumFormatId);
                operLogDao.save(operLog);
                return 1;
            }
            else
            {
                return 0;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public PageBean getPage(int pageNum, int pageSize)
    {
        int recordCount = datumFormatDao.getRecordCount();
        List<DatumFormat> recordList = datumFormatDao.getRecordList(pageNum, pageSize);
        return new PageBean(pageNum, pageSize, recordCount, recordList);
    }

    @Override
    public int modify(DatumFormat datumFormat)
    {
        try
        {
            if(datumFormatDao.getById(datumFormat.getDatumFormatId()) != null)
            {
                datumFormatDao.update(datumFormat);
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("修改文档格式：" + datumFormat.getDatumFormatId() + "|" + datumFormat.getDatumFormatName());
                operLogDao.save(operLog);
                return 1;
            }
            else
            {
                return 0;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int save(DatumFormat datumFormat)
    {
        try
        {
            if(datumFormatDao.select(datumFormat.getDatumFormatName()) == null)
            {
                datumFormatDao.save(datumFormat);
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("添加文档格式：" + datumFormat.getDatumFormatId() + "|" + datumFormat.getDatumFormatName());
                operLogDao.save(operLog);
                return 1;
            }
            else
            {
                return 0;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }
}