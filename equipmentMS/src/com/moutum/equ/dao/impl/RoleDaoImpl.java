package com.moutum.equ.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moutum.equ.base.DaoSupportImpl;
import com.moutum.equ.dao.RoleDao;
import com.moutum.equ.domain.Role;
import com.moutum.equ.util.XMLUtil;

/************************************************************************************
 * @Title        : RoleDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午11:22:42
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Repository @Transactional
public class RoleDaoImpl extends DaoSupportImpl<Role> implements RoleDao
{
    @Resource SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public Role select(String name)
    {
        List<Role> list = sessionFactory.getCurrentSession().createQuery("FROM Role r WHERE r.name = ?").setParameter(0, name).list();
        Role role = null;
        if(list.size() > 0)
        {
            role = list.get(0);
        }
        return role;
    }

    @Override
    public int getRecordCount()
    {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT COUNT(r) FROM Role r WHERE r.roleId !=" + XMLUtil.CONFIG_MAP.get("managerid"));
        return Integer.parseInt(query.list().get(0).toString());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getRecordList(int pageNum, int pageSize)
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Role r WHERE r.roleId !=" + XMLUtil.CONFIG_MAP.get("managerid")).setFirstResult(pageSize*(pageNum - 1)).setMaxResults(pageSize).list();
    }
}