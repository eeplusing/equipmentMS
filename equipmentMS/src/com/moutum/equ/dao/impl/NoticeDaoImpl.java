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
import com.moutum.equ.dao.NoticeDao;
import com.moutum.equ.domain.Notice;

/************************************************************************************
 * @Title        : NoticeDaoImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年4月2日 下午4:25:03
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Repository @Transactional
public class NoticeDaoImpl extends DaoSupportImpl<Notice> implements NoticeDao
{
    @Resource SessionFactory sessionFactory;

    @Override
    public int getRecordCount(Map<String, Object> map)
    {
        Map<Integer, Object> valueMap = new HashMap<Integer, Object>();
        int index = 0;
        String hql = "SELECT COUNT(n) FROM Notice n WHERE 1 = 1";
        
        if(null != map.get("title"))
        {
            
            hql += " AND n.title like ?";
            valueMap.put(index++, map.get("title"));
        }
        if(null != map.get("publisher"))
        {
           
            hql += " AND n.publisher like ?";
            valueMap.put(index++, map.get("publisher"));
        }
        if(null != map.get("noticeTimeST"))
        {
            hql += " AND n.noticeTime > ?";
            valueMap.put(index++, map.get("noticeTimeST"));
        }
        if(null != map.get("noticeTimeED"))
        {
            hql += " AND n.noticeTime < ?";
            valueMap.put(index++, map.get("noticeTimeED"));
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
    public List<Notice> getRecordList(int pageNum, int pageSize, Map<String, Object> map)
    {
        Map<Integer, Object> valueMap = new HashMap<Integer, Object>();
        int index = 0;
        String hql = "FROM Notice n WHERE 1 = 1";
        
        if(null != map.get("title"))
        {
            
            hql += " AND n.title like ?";
            valueMap.put(index++, map.get("title"));
        }
        if(null != map.get("publisher"))
        {
           
            hql += " AND n.publisher like ?";
            valueMap.put(index++, map.get("publisher"));
        }
        if(null != map.get("noticeTimeST"))
        {
            hql += " AND n.noticeTime > ?";
            valueMap.put(index++, map.get("noticeTimeST"));
        }
        if(null != map.get("noticeTimeED"))
        {
            hql += " AND n.noticeTime < ?";
            valueMap.put(index++, map.get("noticeTimeED"));
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