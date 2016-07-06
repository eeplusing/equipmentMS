package com.moutum.equ.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moutum.equ.base.DaoSupportImpl;
import com.moutum.equ.dao.DatumTypeDao;
import com.moutum.equ.domain.DatumType;

/************************************************************************************
 * @Title        : DatumTypeDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午5:05:55
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Repository @Transactional
public class DatumTypeDaoImpl extends DaoSupportImpl<DatumType> implements DatumTypeDao
{

    @Resource SessionFactory sessionFactory;
    
    @Override
    public int getRecordCount()
    {
        return Integer.parseInt(sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(d) FROM DatumType d")
                .list().get(0).toString());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DatumType> getRecordList(int pageNum, int pageSize)
    {
        return sessionFactory.getCurrentSession().createQuery("FROM DatumType d")
        .setFirstResult(pageSize*(pageNum - 1)).setMaxResults(pageSize).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public DatumType select(String datumtypeName)
    {
        List<DatumType> list = sessionFactory.getCurrentSession()
        .createQuery("FROM DatumType d WHERE d.datumTypeName = ?")
        .setParameter(0, datumtypeName).list();
        if (list.size() > 0)
        {
            return list.get(0);
        }
        return null;
    }
}