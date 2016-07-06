package com.moutum.equ.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.dao.OperLogDao;
import com.moutum.equ.dao.TypeDao;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.domain.Type;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.dto.TypeDto;
import com.moutum.equ.service.TypeService;
import com.moutum.equ.util.SystemConstants;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title        : TypeServiceImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月25日 上午11:50:48
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Service 
public class TypeServiceImpl implements TypeService
{
    @Resource TypeDao typeDao;
    
    @Resource OperLogDao operLogDao;
    
    @Override
    public PageBean getPage(int pageNum, int pageSize, Map<String, Object> map)
    {
        int recordCount = typeDao.getRecordCount(map);
        List<Type> recordList = typeDao.getRecordList(pageNum, pageSize, map);
        List<TypeDto> list = new ArrayList<TypeDto>();
        Map<Long, String> typeMap = new HashMap<Long, String>();
        typeMap.put(0l, "总类");
        for(Type type : typeDao.findAll())
        {
            typeMap.put(type.getTypeId(), type.getTypeName());
        }
        for(Type type : recordList)
        {
            TypeDto typeDto = new TypeDto();
            typeDto.setTypeId(type.getTypeId());
            typeDto.setTypeName(type.getTypeName());
            typeDto.setParentName(typeMap.get(type.getParentId()));
            list.add(typeDto);
        }
        return new PageBean(pageNum, pageSize, recordCount, list);
    }

    @Override
    public int save(Type type)
    {
        try
        {
            if(null != typeDao.select(type))
            {
                return 0;
            }
            else
            {
                typeDao.save(type);
                Type t = typeDao.select(type);
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("添加类型:" + type.getTypeName());
                operLogDao.save(operLog);
                
                return Integer.parseInt(t.getTypeId() + "");
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
            if(null != typeDao.getById((long)id))
            {
                typeDao.delete((long)id);
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("删除类型:" + id);
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
    public int modify(Type type)
    {
        try
        {
            if(null != typeDao.getById(type.getTypeId()))
            {
                typeDao.update(type);
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("修改类型:" + type.getTypeId() + "|" + type.getTypeName());
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
    public List<Type> findAll()
    {
        return typeDao.findAll();
    }

    @Override
    public Type getById(long id)
    {
        return typeDao.getById(id);
    }
}