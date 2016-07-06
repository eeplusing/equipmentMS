package com.moutum.equ.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moutum.equ.base.DaoSupportImpl;
import com.moutum.equ.dao.RoleModleDao;
import com.moutum.equ.domain.RoleModle;

/************************************************************************************
 * @Title        : RoleModleDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午11:27:58
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/

@Repository @Transactional
public class RoleModleDaoImpl extends DaoSupportImpl<RoleModle> implements RoleModleDao
{
    @Resource SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<RoleModle> getByRoleIds(long roleId)
    {
        return sessionFactory.getCurrentSession().createQuery("FROM RoleModle rm WHERE rm.roleId = ?").setParameter(0, roleId).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public RoleModle select(long roleId, long modleId)
    {
        List<RoleModle> list = sessionFactory.getCurrentSession()
                               .createQuery("FROM RoleModle rm WHERE rm.roleId = ? AND rm.modleId = ?")
                               .setParameter(0, roleId).setParameter(1, modleId).list();
        RoleModle roleModle= null;
        if(list.size() > 0)
        {
            roleModle = list.get(0);
        }
        return roleModle;
    }

    @Override
    public void move(long roleId)
    {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM RoleModle rm WHERE rm.roleId = ?")
        .setParameter(0, roleId).executeUpdate();
    }

}