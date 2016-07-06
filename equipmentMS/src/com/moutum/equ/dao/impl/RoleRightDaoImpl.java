package com.moutum.equ.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moutum.equ.base.DaoSupportImpl;
import com.moutum.equ.dao.RoleRightDao;
import com.moutum.equ.domain.RoleRight;

/************************************************************************************
 * @Title        : RoleRightDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午11:25:13
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/

@Repository @Transactional
public class RoleRightDaoImpl extends DaoSupportImpl<RoleRight> implements RoleRightDao
{
    @Resource SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
    public RoleRight select(long roleId, long rightId)
    {
        List<RoleRight> list = sessionFactory.getCurrentSession()
                .createQuery("FROM RoleRight r WHERE r.roleId = ? AND r.rightId = ?")
                .setParameter(0, roleId).setParameter(1, rightId).list();
        RoleRight roleRight= null;
        if(list.size() > 0)
        {
            roleRight = list.get(0);
        }
        return roleRight;
    }

    @Override
    public void move(long roleId)
    {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM RoleRight r WHERE r.roleId = ?")
        .setParameter(0, roleId).executeUpdate();
    }
}