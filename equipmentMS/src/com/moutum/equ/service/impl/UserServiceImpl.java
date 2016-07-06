package com.moutum.equ.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.dao.OperLogDao;
import com.moutum.equ.dao.RightDao;
import com.moutum.equ.dao.RoleDao;
import com.moutum.equ.dao.UserDao;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.domain.Role;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.dto.UserDto;
import com.moutum.equ.service.UserService;
import com.moutum.equ.util.SystemConstants;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title : UserServiceImpl.java
 * @Description :
 * @Author : BianWeiqing
 * @DateTime : 2015年3月9日 下午4:04:12
 * @Copyright : 2015 Moutum All Rights Reserved
 * @version : V1.0
 ************************************************************************************/
@Service
public class UserServiceImpl implements UserService
{
    @Resource RightDao rightDao;
    
    @Resource UserDao userDao;
    
    @Resource RoleDao roleDao;
    
    @Resource OperLogDao operLogDao;
    
    @Override
    public boolean haveRight(User user, String function)
    {
        try
        {
            if(null == (Long)user.getRoleId())
            {
                return false;
            }
            if(null == rightDao.getRightsByUser(user, function))
            {
                return false;
            }
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public PageBean getPage(int pageNum, int pageSize, Map<String, Object> map)
    {
        Map<Long, String> roleMap = new HashMap<Long, String>();
        List<Role> roles = roleDao.findAll();
        for(Role role : roles)
        {
            roleMap.put(role.getRoleId(), role.getName());
        }
        int recordCount = userDao.getRecordCount(map);
        List<User> recordList = userDao.getRecordList(pageNum, pageSize, map);
        List<UserDto> list = new ArrayList<UserDto>();
        for(User user : recordList)
        {
            UserDto userDto = new UserDto();
            userDto.setEmail(user.getEmail());
            userDto.setIsActivated(user.getIsActivated());
            userDto.setLoginAccount(user.getLoginAccount());
            userDto.setLoginPassword(user.getLoginPassword());
            userDto.setRoleName(roleMap.get(user.getRoleId()));
            userDto.setUserId(user.getUserId());
            userDto.setUserName(user.getUserName());
            userDto.setUserPhone(user.getUserPhone());
            list.add(userDto);
        }
        return new PageBean(pageNum, pageSize, recordCount, list);
    }

    @Override
    public int save(User user)
    {
        if(null == userDao.getByLoginAccount(user.getLoginAccount()))
        {
            try
            {
                userDao.save(user);
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("添加账号：" + user.getLoginAccount());
                operLogDao.save(operLog);
                return 1;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return 0;
            }
        }
        return -1;
    }

    @Override
    public int modify(User user)
    {
        User u = userDao.getById(user.getUserId());
        if(null != u)
        {
            try
            {
                userDao.update(user);
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
    public User select(String loginAccount)
    {
        return userDao.getByLoginAccount(loginAccount);
    }

    @Override
    public int deleteUser(String loginAccount)
    {
        User user = userDao.getByLoginAccount(loginAccount);
        if(null != user)
        {
            try
            {
                userDao.delete(user.getUserId());
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("删除账号：" + user.getLoginAccount());
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
    public User selectById(long userId)
    {
        return userDao.getById(userId);
    }
}