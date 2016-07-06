package com.moutum.equ.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moutum.equ.base.DaoSupportImpl;
import com.moutum.equ.dao.RightDao;
import com.moutum.equ.domain.Right;
import com.moutum.equ.domain.User;

/************************************************************************************
 * @Title        : RightDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午10:51:03
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Repository @Transactional
public class RightDaoImpl extends DaoSupportImpl<Right> implements RightDao
{
    @Resource SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Right> getRightsByUser(User user, String function)
    {
        List<Right> rights = sessionFactory.getCurrentSession().createQuery("SELECT r FROM Right r, RoleRight rt WHERE r.rightId = rt.rightId and rt.roleId = ? and r.functionName = ?").setParameter(0, user.getRoleId()).setParameter(1, function).list();
        if(rights.size() > 0)
        {
            return rights;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Right> getRightsByRole(long roleId)
    {
        List<Right> rights = sessionFactory.getCurrentSession()
                             .createQuery("SELECT r FROM Right r, RoleRight rt WHERE r.rightId = rt.rightId and rt.roleId = ? ")
                             .setParameter(0, roleId).list();
        if(rights.size() > 0)
        {
            return rights;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Right> getRecordList(int pageNum, int pageSize)
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Right")
                .setFirstResult(pageSize*(pageNum - 1)).setMaxResults(pageSize).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Right> selectRight(long modleId)
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Right r WHERE r.modleId = ?").setParameter(0, modleId).list();
    }
}