package com.moutum.equ.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moutum.equ.base.DaoSupportImpl;
import com.moutum.equ.dao.UserDao;
import com.moutum.equ.domain.User;

/************************************************************************************
 * @Title        : UserDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月6日 下午4:19:51
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/

@Repository @Transactional
public class UserDaoImpl extends DaoSupportImpl<User> implements UserDao
{
    @Resource SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
    public User getByLoginAccount(String loginAccount)
    {
        List<User> list = sessionFactory.getCurrentSession().createQuery("FROM User u WHERE u.loginAccount = ?").setParameter(0, loginAccount).list();
        User user = null;
        if(list.size() > 0)
        {
            user = list.get(0);
        }
        return user;
    }

    @Override
    public int getRecordCount(Map<String, Object> map)
    {
        Map<Integer, Object> valueMap = new HashMap<Integer, Object>();
        int index = 0;
        String hql = "SELECT COUNT(u) FROM User u WHERE u.loginAccount != 'admin'";
        
        if(null != map.get("loginAccount"))
        {
            hql += " AND u.loginAccount like ?";
            valueMap.put(index++, map.get("loginAccount"));
        }
        if(null != map.get("userName"))
        {
            hql += " AND u.userName like ?";
            valueMap.put(index++, map.get("userName"));
        }
        if(null != map.get("roleId"))
        {
            hql += " AND u.roleId = ?";
            valueMap.put(index++, map.get("roleId"));
        }
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        for(int i = 0; i < valueMap.size(); i++)
        {
            query.setParameter(i, valueMap.get(i));
        }
        return Integer.parseInt(query.list().get(0).toString());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getRecordList(int pageNum, int pageSize, Map<String, Object> map)
    {
        Map<Integer, Object> valueMap = new HashMap<Integer, Object>();
        int index = 0;
        String hql = "FROM User u WHERE u.loginAccount != 'admin'";
        
        if(null != map.get("loginAccount"))
        {
            hql += " AND u.loginAccount like ?";
            valueMap.put(index++, map.get("loginAccount"));
        }
        if(null != map.get("userName"))
        {
            hql += " AND u.userName like ?";
            valueMap.put(index++, map.get("userName"));
        }
        if(null != map.get("roleId"))
        {
            hql += " AND u.roleId = ?";
            valueMap.put(index++, map.get("roleId"));
        }
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        for(int i = 0; i < valueMap.size(); i++)
        {
            query.setParameter(i, valueMap.get(i));
        }
        query.setFirstResult(pageSize*(pageNum - 1));
        query.setMaxResults(pageSize);
        return query.list();
    }
}