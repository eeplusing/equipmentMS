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
import com.moutum.equ.dao.EquipmentDao;
import com.moutum.equ.domain.Equipment;

/************************************************************************************
 * @Title        : EquipmentDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午5:08:37
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Repository @Transactional
public class EquipmentDaoImpl extends DaoSupportImpl<Equipment> implements EquipmentDao
{
    @Resource SessionFactory sessionFactory;
    
    @Override
    public int getRecordCount(Map<String, Object> map)
    {
        Map<Integer, Object> valueMap = new HashMap<Integer, Object>();
        int index = 0;
        String hql = "SELECT COUNT(e) FROM Equipment e WHERE 1 = 1";
        if(null != map.get("typeName"))
        {
            hql += " AND e.typeName = ?";
            valueMap.put(index++, map.get("typeName"));
        }
        if(null != map.get("useState"))
        {
            hql += " AND e.useState = ?";
            valueMap.put(index++, map.get("useState"));
        }
        if(null != map.get("sdate"))
        {
            hql += " AND e.equipmentStartDate >= ?";
            valueMap.put(index++, map.get("sdate"));
        }
        if(null != map.get("edate"))
        {
            hql += " AND e.equipmentStartDate <= ?";
            valueMap.put(index++, map.get("edate"));
        }
        if(null != map.get("equNo"))
        {
            hql += " AND e.equipmentNo like ?";
            valueMap.put(index++, map.get("equNo"));
        }
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        for(int i = 0; i < valueMap.size(); i++)
        {
            query.setParameter(i, valueMap.get(i));
        }
        return Integer.parseInt(query.list().get(0).toString());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Equipment> getRecordList(int pageNum, int pageSize, Map<String, Object> map)
    {
        Map<Integer, Object> valueMap = new HashMap<Integer, Object>();
        int index = 0;
        String hql = "FROM Equipment e WHERE 1 = 1";
        if(null != map.get("typeName"))
        {
            hql += " AND e.typeName = ?";
            valueMap.put(index++, map.get("typeName"));
        }
        if(null != map.get("useState"))
        {
            hql += " AND e.useState = ?";
            valueMap.put(index++, map.get("useState"));
        }
        if(null != map.get("sdate"))
        {
            hql += " AND e.equipmentStartDate >= ?";
            valueMap.put(index++, map.get("sdate"));
        }
        if(null != map.get("edate"))
        {
            hql += " AND e.equipmentStartDate <= ?";
            valueMap.put(index++, map.get("edate"));
        }
        if(null != map.get("equNo"))
        {
            hql += " AND e.equipmentNo like ?";
            valueMap.put(index++, map.get("equNo") + "%");
        }
       hql += " ORDER BY e.equipmentStartDate DESC";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        for(int i = 0; i < valueMap.size(); i++)
        {
            query.setParameter(i, valueMap.get(i));
        }
        query.setFirstResult(pageSize*(pageNum - 1));
        query.setMaxResults(pageSize);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Equipment select(Equipment equipment)
    {
        List<Equipment> list = sessionFactory.getCurrentSession().createQuery("FROM Equipment e WHERE e.equipmentNo = ?").setParameter(0, equipment.getEquipmentNo()).list();
        if(list.size() > 0)
        {
            return list.get(0);
        }
        return null;
    }

}