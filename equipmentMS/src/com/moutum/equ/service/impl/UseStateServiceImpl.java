package com.moutum.equ.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.dao.OperLogDao;
import com.moutum.equ.dao.UseStateDao;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.domain.UseState;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.service.UseStateService;
import com.moutum.equ.util.SystemConstants;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title        : UseStateServiceImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月26日 下午2:34:13
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Service
public class UseStateServiceImpl implements UseStateService
{
    @Resource UseStateDao useStateDao;
    
    @Resource OperLogDao operLogDao;
    
    @Override
    public PageBean getPage(int pageNum, int pageSize)
    {
        int recordCount = useStateDao.getRecordCount();
        List<UseState> recordList = useStateDao.getRecordList(pageNum, pageSize);
        return new PageBean(pageNum, pageSize, recordCount, recordList);
    }

    @Override
    public int save(UseState useState)
    {
        try
        {
            if(useStateDao.select(useState.getUseStateName()) == null)
            {
                useStateDao.save(useState);
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("添加使用状态:" + useState.getUseStateId() + "|" + useState.getUseStateName());
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
    public int modify(UseState useState)
    {
        try
        {
            if(useStateDao.getById(useState.getUseStateId()) != null)
            {
                useStateDao.update(useState);
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("修改使用状态信息:" + useState.getUseStateId() + "|" + useState.getUseStateName());
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
    public int delete(int id)
    {
        try
        {
            if(useStateDao.getById((long)id) != null)
            {
                useStateDao.delete((long)id);

                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("删除使用状态:" + id);
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
    public List<UseState> findAll()
    {
        return useStateDao.findAll();
    }
}