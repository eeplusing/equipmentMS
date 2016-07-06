package com.moutum.equ.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moutum.equ.base.DaoSupportImpl;
import com.moutum.equ.dao.ModleDao;
import com.moutum.equ.domain.Modle;
import com.moutum.equ.domain.User;

/************************************************************************************
 * @Title        : ModleDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午10:51:03
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Repository @Transactional
public class ModleDaoImpl extends DaoSupportImpl<Modle> implements ModleDao
{

    @Resource SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Modle> selectFirstModleByUser(User user, long parentId)
    {
        return sessionFactory.getCurrentSession().createQuery("SELECT m FROM Modle m, RoleModle rm WHERE m.modleId = rm.modleId and rm.roleId = ? and m.parentId = ?").setParameter(0, user.getRoleId()).setParameter(1, parentId).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Modle> selectModle(long parentId)
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Modle m WHERE m.parentId = ?").setParameter(0, parentId).list();
    }
}