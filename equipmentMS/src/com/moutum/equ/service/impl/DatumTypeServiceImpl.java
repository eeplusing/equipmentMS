package com.moutum.equ.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.dao.DatumTypeDao;
import com.moutum.equ.dao.OperLogDao;
import com.moutum.equ.domain.DatumType;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.service.DatumTypeService;
import com.moutum.equ.util.SystemConstants;
import com.opensymphony.xwork2.ActionContext;

/**
 * @Title        : DatumTypeServiceImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015-3-28 下午04:43:09
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 */
@Service
public class DatumTypeServiceImpl implements DatumTypeService
{
    @Resource DatumTypeDao datumTypeDao;
    
    @Resource OperLogDao operLogDao;
    
    
    @Override
    public int delete(int datumTypeId)
    {
        try
        {
            if(datumTypeDao.getById((long)datumTypeId) != null)
            {
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                datumTypeDao.delete((long)datumTypeId);
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("删除文档类型：" + datumTypeId);
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
        int recordCount = datumTypeDao.getRecordCount();
        List<DatumType> recordList = datumTypeDao.getRecordList(pageNum, pageSize);
        return new PageBean(pageNum, pageSize, recordCount, recordList);
    }

    @Override
    public int modify(DatumType datumType)
    {
        try
        {
            if(datumTypeDao.getById(datumType.getDatumTypeId()) != null)
            {
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                datumTypeDao.update(datumType);
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("修改文档类型：" + datumType.getDatumTypeId() + "|" + datumType.getDatumTypeName());
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
    public int save(DatumType datumType)
    {
        try
        {
            if(datumTypeDao.select(datumType.getDatumTypeName()) == null)
            {
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                datumTypeDao.save(datumType);
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("添加文档类型：" + datumType.getDatumTypeId() + "|" + datumType.getDatumTypeName());
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