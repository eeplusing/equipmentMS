package com.moutum.equ.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.dao.ModleDao;
import com.moutum.equ.dao.OperLogDao;
import com.moutum.equ.dao.RightDao;
import com.moutum.equ.dao.RoleModleDao;
import com.moutum.equ.dao.RoleRightDao;
import com.moutum.equ.domain.Modle;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.domain.Right;
import com.moutum.equ.domain.RoleModle;
import com.moutum.equ.domain.RoleRight;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.service.RightService;
import com.moutum.equ.util.SystemConstants;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title        : RightServiceImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 下午4:02:00
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Service
public class RightServiceImpl implements RightService
{
    @Resource RightDao rightDao;
    
    @Resource ModleDao modleDao;
    
    @Resource RoleModleDao roleModleDao;
    
    @Resource RoleRightDao roleRightDao;
    
    @Resource OperLogDao operLogDao;
    
    @Override
    public Map<String, String> getFunctionByRole(long roleId)
    {
        Map<String, String> rightMap = new HashMap<String, String>();
        List<Right> rights = rightDao.getRightsByRole(roleId);
        if(null != rights)
        {
            for(Right right : rights)
            {
                String[] functions = right.getFunctionName().split("\\|");
                for(String function : functions)
                {
                    rightMap.put(function, function);
                }
            }
        }
        return rightMap;
    }

    @Override
    public PageBean getPage(int pageNum, int pageSize)
    {
        int recordCount = rightDao.findAll().size();
        List<Right> recordList = rightDao.getRecordList(pageNum, pageSize);
        return new PageBean(pageNum, pageSize, recordCount, recordList);
    }

    @Override
    public Map<String, Map<String, List<Right>>> rightMap()
    {
        List<Modle> firstModles = modleDao.selectModle(0);
        Map<String, Map<String, List<Right>>> fmap = new HashMap<String, Map<String, List<Right>>>();
        for(Modle fm : firstModles)
        {
            List<Modle> secondModles = modleDao.selectModle(fm.getModleId());
            Map<String, List<Right>> smap = new HashMap<String, List<Right>>();
            for(Modle sm : secondModles)
            {
                smap.put(sm.getName(), rightDao.selectRight(sm.getModleId()));
            }
            fmap.put(fm.getName(), smap);
        }
        
        return fmap;
    }

    @Override
    public String getRightIdByRole(long roleId)
    {
        List<Right> rights = rightDao.getRightsByRole(roleId);
        StringBuffer stringBuffer = null;
        if(null != rights)
        {
            stringBuffer = new StringBuffer();
            for(Right right : rights)
            {
                stringBuffer.append(right.getRightId() + "[]");
            }
        }
        if(null != stringBuffer)
        {
            return stringBuffer.toString();
        }
        else
        {
            return "";
        }
    }

    @Override
    public int giveRight(String[] rids, long roleId)
    {
        try
        {
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");

            //第一步:删除角色所有的模块访问权限和系统操作权限
            roleModleDao.move(roleId);//删除角色的模块访问权限
            roleRightDao.move(roleId);//删除角色的系统操作权限
            
            //第二步:授权
            for(String rid : rids)
            {
                Right right = rightDao.getById(Long.parseLong(rid));//获取权限对象
                Modle modle = modleDao.getById(right.getModleId());//获取权限所属模块
                Modle fModle = modleDao.getById(modle.getParentId());//获取父模块
                if(null == roleModleDao.select(roleId, modle.getModleId()))//如果角色没有对应的模块访问权限，则为该角色增加访问权限
                {
                    RoleModle roleModle = new RoleModle();
                    roleModle.setModleId(modle.getModleId());
                    roleModle.setRoleId(roleId);
                    roleModleDao.save(roleModle);
                    
                    OperLog operLog = new OperLog();
                    operLog.setOperator(loginuser.getLoginAccount());
                    operLog.setOperLogTime(new Date());
                    operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                    operLog.setOperLogContent("授权：为角色" + roleId + "增加模块" + modle.getName() + "的访问权");
                    operLogDao.save(operLog);
                }
                if(null == roleModleDao.select(roleId, fModle.getModleId()))
                {
                    RoleModle roleModle = new RoleModle();
                    roleModle.setModleId(fModle.getModleId());
                    roleModle.setRoleId(roleId);
                    roleModleDao.save(roleModle);
                    
                    OperLog operLog = new OperLog();
                    operLog.setOperator(loginuser.getLoginAccount());
                    operLog.setOperLogTime(new Date());
                    operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                    operLog.setOperLogContent("授权：为角色" + roleId + "增加模块" + fModle.getName() + "的访问权");
                    operLogDao.save(operLog);
                }
                if(null == roleRightDao.select(roleId, right.getRightId()))
                {
                    RoleRight roleRight = new RoleRight();
                    roleRight.setRightId(right.getRightId());
                    roleRight.setRoleId(roleId);
                    roleRightDao.save(roleRight);
                    
                    OperLog operLog = new OperLog();
                    operLog.setOperator(loginuser.getLoginAccount());
                    operLog.setOperLogTime(new Date());
                    operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                    operLog.setOperLogContent("授权：为角色" + roleId + "增加权限" + right.getName() + "的访问权");
                    operLogDao.save(operLog);
                }
            }
            return 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        
    }
}