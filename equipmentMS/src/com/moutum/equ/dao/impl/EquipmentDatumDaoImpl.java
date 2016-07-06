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
import com.moutum.equ.dao.EquipmentDatumDao;
import com.moutum.equ.domain.EquipmentDatum;

/**
 * @Title        : EquipmentDatumDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015-3-28 下午08:50:04
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 */
@Repository @Transactional
public class EquipmentDatumDaoImpl extends DaoSupportImpl<EquipmentDatum> implements EquipmentDatumDao
{

    @Resource SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<EquipmentDatum> select(Long equipmentId, Long datumId)
    {
        StringBuffer hql = new StringBuffer("FROM EquipmentDatum ed WHERE 1=1");
        Map<Integer, Long> map = new HashMap<Integer, Long>();
        int index = 0;
        if(null != equipmentId)
        {
            hql.append(" AND ed.equipmentId = ?");
            map.put(index++, equipmentId);
        }
        if(null != datumId)
        {
            hql.append(" AND ed.datumId = ?");
            map.put(index++, datumId);
        }
        
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        for(Integer i : map.keySet())
        {
            query.setParameter(i, map.get(i));
        }
        return query.list();
    }
}