package com.moutum.equ.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moutum.equ.base.DaoSupportImpl;
import com.moutum.equ.dao.OutstockDao;
import com.moutum.equ.domain.Outstock;
import com.moutum.equ.util.QueryHelper;

/************************************************************************************
 * @Title        : OutstockDaoImpl.java
 * @Description : 
 * @Author       : HuangWei
 * @DateTime     : 2015年8月20日 上午10:15:30
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Repository @Transactional
public class OutstockDaoImpl extends DaoSupportImpl<Outstock> implements OutstockDao
{
	@Resource SessionFactory sessionFactory;

	@Override
	public int getRecordCount(Map<String, Object> map) 
	{
		
		if(null ==map.get("instockId"))
		{
			QueryHelper queryHelper = new QueryHelper(Outstock.class, "o");
			List<Object> parameters = queryHelper.getParameters();
			if(null != map.get("sparepartNo"))
			{
				queryHelper.addCondition("sparepartNo like ?",  map.get("sparepartNo") + "%");
			}
			if(null !=map.get("instockTimeStart"))
			{
				queryHelper.addCondition("instockTime > ?", map.get("instockTimeStart"));
			} 
			if(null !=map.get("instockTimeEnd"))
			{
				queryHelper.addCondition("instockTime < ?", map.get("instockTimeEnd"));
			} 
			Query countQuery = getSession().createQuery(queryHelper.getCountQueryHql());
			if (parameters != null) { // 设置参数
				for (int i = 0; i < parameters.size(); i++) 
				{
					countQuery.setParameter(i, parameters.get(i));
				}
			}
			Long count = (Long) countQuery.uniqueResult(); // 执行查询
			return count.intValue();
		} 
		else
		{
			List<Object> list = new ArrayList<Object>();
			String SQL = "SELECT COUNT(o) FROM Outstock o, Instockandoutstock i WHERE o.outstockId = i.outstockId AND i.instockId = ?";
			list.add(Long.parseLong((String)map.get("instockId")));
			
			if(null != map.get("sparepartNo"))
			{
				SQL += " AND o.sparepartNo like ?";
				list.add( map.get("sparepartNo") + "%");
			}
			if(null != map.get("o.instockTimeStart"))
			{
				SQL += " AND instockTime > ?";
				list.add(map.get("instockTimeStart"));
			}
			if(null != map.get("instockTimeEnd"))
			{
				SQL += " AND o.instockTime < ?";
				list.add(map.get("instockTimeEnd"));
			}
			Query query = sessionFactory.getCurrentSession().createQuery(SQL);
			for(int i = 0 ; i < list.size(); i++)
			{
				query.setParameter(i, list.get(i));
			}
			return Integer.parseInt(query.list().get(0).toString());
		}
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Outstock> getRecordList(int pageNum, int pageSize,Map<String, Object> map) 
	{
		if(null ==map.get("instockId"))
		{
			QueryHelper queryHelper = new QueryHelper(Outstock.class, "o");
			List<Object> parameters = queryHelper.getParameters();
		
			if(null != map.get("sparepartNo"))
			{
				queryHelper.addCondition("sparepartNo like ?", map.get("sparepartNo") + "%");
			}
			if(null !=map.get("instockTimeStart"))
			{
				queryHelper.addCondition("instockTime > ?", map.get("instockTimeStart"));
			} 
			if(null !=map.get("instockTimeEnd"))
			{
				queryHelper.addCondition("instockTime < ?", map.get("instockTimeEnd"));
			} 
			queryHelper.addOrderProperty("instockTime", false);
		
			// 查询本页的数据列表
			Query listQuery = getSession().createQuery(queryHelper.getListQueryHql()); // 创建查询对象
			if (parameters != null) 
			{ // 设置参数
				for (int i = 0; i < parameters.size(); i++) 	
				{
					listQuery.setParameter(i, parameters.get(i));
				}
			}
			listQuery.setFirstResult((pageNum - 1) * pageSize);
			listQuery.setMaxResults(pageSize);
			List list = listQuery.list(); // 执行查询
			return list;
		}
		else
		{
			List<Object> list = new ArrayList<Object>();
			String SQL = "SELECT o FROM Outstock o, Instockandoutstock i WHERE o.outstockId = i.outstockId AND i.instockId = ?";
			list.add(Long.parseLong((String)map.get("instockId")));
			
			if(null != map.get("sparepartNo"))
			{
				SQL += " AND o.sparepartNo like ?";
				list.add( map.get("sparepartNo") + "%");
			}
			if(null != map.get("o.instockTimeStart"))
			{
				SQL += " AND instockTime > ?";
				list.add(map.get("instockTimeStart"));
			}
			if(null != map.get("instockTimeEnd"))
			{
				SQL += " AND o.instockTime < ?";
				list.add(map.get("instockTimeEnd"));
			}
			Query query = sessionFactory.getCurrentSession().createQuery(SQL);
			for(int i = 0 ; i < list.size(); i++)
			{
				query.setParameter(i, list.get(i));
			}
			query.setFirstResult((pageNum - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Outstock select(Outstock outstock) 
	{
		Outstock o = null;
	     List<Outstock> list = sessionFactory.getCurrentSession().createQuery("FROM Outstock o WHERE o.instockTime = ?  AND o.sparepartNo = ? AND o.outstockTime = ?").setParameter(0, outstock.getInstockTime()).setParameter(1, outstock.getSparepartNo()).setParameter(2, outstock.getOutstockTime()).list();
	     if(list.size() > 0)
	     {
	    	 o = list.get(0); 
	     }
	     return o;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Outstock> getRecordList(Map<String, Object> map) {
		QueryHelper queryHelper = new QueryHelper(Outstock.class, "o");
		List<Object> parameters = queryHelper.getParameters();
	
		if(null != map.get("sparepartNo"))
		{
			queryHelper.addCondition("sparepartNo like ?", map.get("sparepartNo") + "%");
		}
		if(null !=map.get("instockTimeStart"))
		{
			queryHelper.addCondition("instockTime > ?", map.get("instockTimeStart"));
		} 
		if(null !=map.get("instockTimeEnd"))
		{
			queryHelper.addCondition("instockTime < ?", map.get("instockTimeEnd"));
		} 
		queryHelper.addOrderProperty("instockTime", false);
	
		// 查询本页的数据列表
		Query listQuery = getSession().createQuery(queryHelper.getListQueryHql()); // 创建查询对象
		if (parameters != null) 
		{ // 设置参数
			for (int i = 0; i < parameters.size(); i++) 	
			{
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		List list = listQuery.list(); // 执行查询
		return list;
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<Outstock> expData(Map<String, Object> map)
    {
        String hql = "FROM Outstock o WHERE 1 = 1";
        Map<Integer, Object> valueMap = new HashMap<Integer, Object>();
        int index = 0;
        if(null != map.get("sdate"))
        {
            hql += " AND o.outstockTime >= ?";
            valueMap.put(index++, map.get("sdate"));
        }
        if(null != map.get("edate"))
        {
            hql += " AND o.outstockTime <= ?";
            valueMap.put(index++, map.get("edate"));
        }  
        if (null != map.get("sparepartNo"))
        {
            hql += " AND o.sparepartNo like ?";
            valueMap.put(index++, map.get("sparepartNo") + "%");
        }
        hql += " ORDER BY o.outstockTime DESC";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        for (int i = 0; i < valueMap.size(); i++) 
        {
            query.setParameter(i, valueMap.get(i));
        }
        return query.list();
    }
}
