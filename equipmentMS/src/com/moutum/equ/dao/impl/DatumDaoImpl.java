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
import com.moutum.equ.dao.DatumDao;
import com.moutum.equ.domain.Datum;

/************************************************************************************
 * @Title        : DatumDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午5:03:42
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Repository @Transactional
public class DatumDaoImpl extends DaoSupportImpl<Datum> implements DatumDao
{
    
    @Resource SessionFactory sessionFactory;

    @Override
    public int getRecordCount(Map<String, Object> map, Long[] datumIds)
    {
        Map<Integer, Object> valueMap = new HashMap<Integer, Object>();
        int index = 0;
        String hql = "SELECT COUNT(d) FROM Datum d WHERE 1 = 1";
        if(null != map.get("datumTypeId"))
        {
            hql += " AND d.datumTypeId = ?";
            valueMap.put(index++, map.get("datumTypeId"));
        }
        if(null != map.get("datumFormatId"))
        {
            hql += " AND d.datumFormatId = ?";
            valueMap.put(index++, map.get("datumFormatId"));
        }
        if(null != map.get("datumName"))
        {
            hql += " AND d.datumName like ?";
            valueMap.put(index++, map.get("datumName"));
        }
        if(null != datumIds)
        {
            hql += " AND d.datumId NOT IN (:ids)";
        }
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        for(int i = 0; i < valueMap.size(); i++)
        {
            query.setParameter(i, valueMap.get(i));
        }
        if(null != datumIds)
        {
            query.setParameterList("ids", datumIds);
        }
        
        return Integer.parseInt(query.list().get(0).toString());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Datum> getRecordList(int pageNum, int pageSize, Map<String, Object> map, Long[] datumIds)
    {
        Map<Integer, Object> valueMap = new HashMap<Integer, Object>();
        int index = 0;
        String hql = "FROM Datum d WHERE 1 = 1";
        if(null != map.get("datumTypeId"))
        {
            hql += " AND d.datumTypeId = ?";
            valueMap.put(index++, map.get("datumTypeId"));
        }
        if(null != map.get("datumFormatId"))
        {
            hql += " AND d.datumFormatId = ?";
            valueMap.put(index++, map.get("datumFormatId"));
        }
        if(null != map.get("datumName"))
        {
            hql += " AND d.datumName like ?";
            valueMap.put(index++, map.get("datumName"));
        }
        if(null != datumIds)
        {
            hql += " AND d.datumId NOT IN (:ids)";
        }
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        for(int i = 0; i < valueMap.size(); i++)
        {
            query.setParameter(i, valueMap.get(i));
        }
        if(null != datumIds)
        {
            query.setParameterList("ids", datumIds);
        }
        query.setFirstResult(pageSize*(pageNum - 1));
        query.setMaxResults(pageSize);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Datum select(String datumName, String datumPath)
    {
        Query query = null;
        if(null != datumName && !"".equals(datumName.trim()))
        {
            if(null != datumPath && !"".equals(datumPath.trim()))
            {
                query = sessionFactory.getCurrentSession()
                .createQuery("FROM Datum d WHERE d.datumName = ? OR d.datumPath = ? ")
                .setParameter(0, datumName)
                .setParameter(1, datumPath);
            }
            else
            {
                query = sessionFactory.getCurrentSession()
                .createQuery("FROM Datum d WHERE d.datumName = ?")
                .setParameter(0, datumName);
            }
        }
        else
        {
            if(null != datumPath && !"".equals(datumPath.trim()))
            {
                query = sessionFactory.getCurrentSession()
                .createQuery("FROM Datum d WHERE d.datumPath = ? ")
                .setParameter(0, datumPath);
            }
        }
        List<Datum> list = query.list();
        if(list.size() > 0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int getRecordCount(long equipmentId)
    {
        String hql = "SELECT COUNT(d) FROM Datum d, EquipmentDatum ed WHERE d.datumId = ed.datumId AND d.datumFormatId != 6 AND ed.equipmentId = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, equipmentId);
        return Integer.parseInt(query.list().get(0).toString());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Datum> getRecordList(int pageNum, int pageSize, long equipmentId)
    {
        String hql = "SELECT d FROM Datum d, EquipmentDatum ed WHERE d.datumId = ed.datumId AND d.datumFormatId != 6 AND ed.equipmentId = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, equipmentId);
        query.setFirstResult(pageSize*(pageNum - 1));
        query.setMaxResults(pageSize);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Datum> selectImage(long equipmentId)
    {
        String hql = "SELECT d FROM Datum d, EquipmentDatum ed WHERE  d.datumId = ed.datumId AND d.datumFormatId = 6 AND ed.equipmentId = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, equipmentId);
        return query.list();
    }
}