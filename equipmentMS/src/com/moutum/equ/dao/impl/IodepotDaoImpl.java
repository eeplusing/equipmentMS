package com.moutum.equ.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moutum.equ.base.DaoSupportImpl;
import com.moutum.equ.dao.IodepotDao;
import com.moutum.equ.domain.Iodepot;

/************************************************************************************
 * @Title        : IodepotDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午5:11:23
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Repository @Transactional
public class IodepotDaoImpl extends DaoSupportImpl<Iodepot> implements IodepotDao
{
    @Resource SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Iodepot> getRecordList(int pageNum, int pageSize, String sparepartNo)
    {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("FROM Iodepot i WHERE i.goodId = ? ")
                .setParameter(0, sparepartNo);
        query.setFirstResult(pageSize*(pageNum - 1));
        query.setMaxResults(pageSize);
        return query.list();
    }

    @Override
    public int getRecordCount(String sparepartNo)
    {
        Query query = sessionFactory.getCurrentSession()
        .createQuery("SELECT COUNT(i) FROM Iodepot i WHERE i.goodId = ? ")
        .setParameter(0, sparepartNo);
        return Integer.parseInt(query.list().get(0).toString());
    }

}