package com.moutum.equ.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moutum.equ.base.DaoSupportImpl;
import com.moutum.equ.dao.DatumFormatDao;
import com.moutum.equ.domain.DatumFormat;

/************************************************************************************
 * @Title        : DatumFormatDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午5:04:50
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Repository @Transactional
public class DatumFormatDaoImpl extends DaoSupportImpl<DatumFormat> implements DatumFormatDao
{

    @Resource SessionFactory sessionFactory;
    
    @Override
    public int getRecordCount()
    {
        return Integer.parseInt(sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(d) FROM DatumFormat d")
                .list().get(0).toString());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DatumFormat> getRecordList(int pageNum, int pageSize)
    {
        return sessionFactory.getCurrentSession().createQuery("FROM DatumFormat d")
        .setFirstResult(pageSize*(pageNum - 1)).setMaxResults(pageSize).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public DatumFormat select(String datumFormatName)
    {
        List<DatumFormat> list = sessionFactory.getCurrentSession()
        .createQuery("FROM DatumFormat d WHERE d.datumFormatName = ?")
        .setParameter(0, datumFormatName).list();
        if (list.size() > 0)
        {
            return list.get(0);
        }
        return null;
    }
}