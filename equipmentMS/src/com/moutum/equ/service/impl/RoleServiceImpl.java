package com.moutum.equ.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.dao.OperLogDao;
import com.moutum.equ.dao.RoleDao;
import com.moutum.equ.dao.RoleModleDao;
import com.moutum.equ.dao.RoleRightDao;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.domain.Role;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.service.RoleService;
import com.moutum.equ.util.SystemConstants;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title : RoleServiceImpl.java
 * @Description :
 * @Author : BianWeiqing
 * @DateTime : 2015年3月9日 下午3:53:11
 * @Copyright : 2015 Moutum All Rights Reserved
 * @version : V1.0
 ************************************************************************************/
@Service
public class RoleServiceImpl implements RoleService
{
    
    @Resource RoleDao roleDao;
    
    @Resource RoleModleDao roleModleDao;
    
    @Resource RoleRightDao roleRightDao;
    
    @Resource OperLogDao operLogDao;
    
    @Override
    public int save(Role role)
    {
        if (null == roleDao.select(role.getName()))
        {
            try
            {
                roleDao.save(role);
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("添加角色：" + role.getName() + "|" + role.getDescribe());
                operLogDao.save(operLog);
                
                return 1;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return 0;
            }
        }
        return -1;
    }

    @Override
    public PageBean getPage(int pageNum, int pageSize)
    {
        int recordCount = roleDao.getRecordCount();
        List<Role> recordList = roleDao.getRecordList(pageNum, pageSize);
        return new PageBean(pageNum, pageSize, recordCount, recordList);
    }

    @Override
    public int modify(Role role)
    {
        Role r = roleDao.getById(role.getRoleId());

        if (null != r)
        {
            try
            {
                roleDao.update(role); 
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("更新角色信息：" + role.getRoleId() + "|" + role.getName() + "|" + role.getDescribe());
                operLogDao.save(operLog);
                
                return 1;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return -1;
            }
        }
        return 0;
    }

    @Override
    public int delete(long roleId)
    {
        Role role = roleDao.getById(roleId);
        if (null != role)
        {
            try
            {
                
                //第一步:删除角色所有的模块访问权限和系统操作权限
                roleModleDao.move(roleId);//删除角色的模块访问权限
                roleRightDao.move(roleId);//删除角色的系统操作权限
                
                //第二步:删除角色
                roleDao.delete(roleId);
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("删除角色：" + role.getRoleId() + "|" + role.getName() + "|" + role.getDescribe());
                operLogDao.save(operLog);
                
                return 1;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return -1;
            }
        }
        return 0;

    }

    @Override
    public List<Role> select()
    {
        return roleDao.getRecordList(1, 1000000);
    }
}