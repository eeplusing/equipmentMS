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
import com.moutum.equ.dao.ProvideBillDao;
import com.moutum.equ.domain.ProvideBill;

/************************************************************************************
 * @Title        : ProvideBillDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年8月19日 上午10:43:33
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Repository @Transactional
public class ProvideBillDaoImpl extends DaoSupportImpl<ProvideBill> implements ProvideBillDao
{
    @Resource SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<ProvideBill> getAll(Map<String, Object> map)
    {
        String hql = "FROM ProvideBill pb WHERE 1 = 1";
        Map<Integer, Object> valueMap = new HashMap<Integer, Object>();
        int index = 0;
        if(null != map.get("sdate"))
        {
            hql += " AND pb.equipmentStartDate >= ?";
            valueMap.put(index++, map.get("sdate"));
        }
        
        if(null != map.get("equNo"))
        {
            hql += " AND pb.equipmentNo like ?";
            valueMap.put(index++, map.get("equNo") + "%");
        }        
        if(null != map.get("edate"))
        {
            hql += " AND pb.equipmentStartDate <= ?";
            valueMap.put(index++, map.get("edate"));
        }  
        hql += " ORDER BY pb.equipmentStartDate DESC";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        for(int i = 0; i < valueMap.size(); i++)
        {
            query.setParameter(i, valueMap.get(i));
        }
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public ProvideBill getByNo(String equipmentNo)
    {
        List<ProvideBill> list = sessionFactory.getCurrentSession().createQuery("FROM ProvideBill pb WHERE pb.equipmentNo = ?").setParameter(0, equipmentNo).list();
        if(list.size() > 0)
        {
            return list.get(0);
        }
        return null;
    }
}