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
import com.moutum.equ.dao.TypeDao;
import com.moutum.equ.domain.Type;

/************************************************************************************
 * @Title        : TypeDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午5:14:24
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Repository @Transactional
public class TypeDaoImpl extends DaoSupportImpl<Type> implements TypeDao
{
    @Resource SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
    public Type select(Type type)
    {
        Type t = null;
        List<Type> list = sessionFactory.getCurrentSession()
                         .createQuery("FROM Type t WHERE t.typeName = ? AND t.parentId = ?")
                         .setParameter(0, type.getTypeName()).setParameter(1, type.getParentId()).list();
        if(list.size() > 0)
        {
           t = list.get(0); 
        }
        return t;
    }

    @Override
    public int getRecordCount(Map<String, Object> map)
    {
        Map<Integer, Object> valueMap = new HashMap<Integer, Object>();
        int index = 0;
        String hql = "SELECT COUNT(t) FROM Type t WHERE 1 = 1";
        
        if(null != map.get("typeName"))
        {
            hql += " AND t.typeName like ?";
            valueMap.put(index++, map.get("typeName"));
        }
        if(null != map.get("parentId"))
        {
            hql += " AND t.parentId = ?";
            valueMap.put(index++, map.get("parentId"));
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
    public List<Type> getRecordList(int pageNum, int pageSize, Map<String, Object> map)
    {
        Map<Integer, Object> valueMap = new HashMap<Integer, Object>();
        int index = 0;
        String hql = "FROM Type t WHERE 1 = 1";
        
        if(null != map.get("typeName"))
        {
            hql += " AND t.typeName like ?";
            valueMap.put(index++, map.get("typeName"));
        }
        if(null != map.get("parentId"))
        {
            hql += " AND t.parentId = ?";
            valueMap.put(index++, map.get("parentId"));
        }
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        for(int i = 0; i < valueMap.size(); i++)
        {
            query.setParameter(i, valueMap.get(i));
        }
        query.setFirstResult(pageSize*(pageNum - 1));
        query.setMaxResults(pageSize);
        return query.list();
    }
}