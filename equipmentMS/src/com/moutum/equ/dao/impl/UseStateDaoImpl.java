package com.moutum.equ.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moutum.equ.base.DaoSupportImpl;
import com.moutum.equ.dao.UseStateDao;
import com.moutum.equ.domain.UseState;

/************************************************************************************
 * @Title        : UseStateDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午5:14:58
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Repository @Transactional
public class UseStateDaoImpl extends DaoSupportImpl<UseState> implements UseStateDao
{
    @Resource SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public UseState select(String name)
    {
        List<UseState> list = sessionFactory.getCurrentSession()
                .createQuery("FROM UseState u WHERE u.useStateName = ?")
                .setParameter(0, name).list();
        if (list.size() > 0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int getRecordCount()
    {
        return Integer.parseInt(sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(u) FROM UseState u")
                .list().get(0).toString());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UseState> getRecordList(int pageNum, int pageSize)
    {
        return sessionFactory.getCurrentSession().createQuery("FROM UseState u")
                .setFirstResult(pageSize*(pageNum - 1)).setMaxResults(pageSize).list();
    }

}