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
import com.moutum.equ.dao.InstockDao;
import com.moutum.equ.domain.Instock;
import com.moutum.equ.util.QueryHelper;

/************************************************************************************
 * @Title        : InstockDaoImpl.java
 * @Description : 
 * @Author       : HuangWei
 * @DateTime     : 2015年8月20日 上午10:15:30
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Repository @Transactional
public class InstockDaoImpl extends DaoSupportImpl<Instock> implements InstockDao
{
	@Resource SessionFactory sessionFactory;

	@Override
	public int getRecordCount(Map<String, Object> map) 
	{
		QueryHelper queryHelper = new QueryHelper(Instock.class, "i");
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
		
		Query countQuery = getSession().createQuery(queryHelper.getCountQueryHql());
        if (parameters != null) 
        { // 设置参数
            for (int i = 0; i < parameters.size(); i++) 
            {
                countQuery.setParameter(i, parameters.get(i));
            }
        }
        Long count = (Long) countQuery.uniqueResult(); // 执行查询
		return count.intValue();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Instock> getRecordList(int pageNum, int pageSize, Map<String, Object> map) 
	{
		QueryHelper queryHelper = new QueryHelper(Instock.class, "i");
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

	@SuppressWarnings("unchecked")
	@Override
	public Instock select(Instock instock) 
	{
		 Instock s = null;
	     List<Instock> list = sessionFactory.getCurrentSession().createQuery("FROM Instock i WHERE i.instockTime = ?").setParameter(0, instock.getInstockTime()).list();
	     if(list.size() > 0)
	     {
	    	 s = list.get(0); 
	     }
	     return s;
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<Instock> expData(Map<String, Object> map)
    {
        String hql = "FROM Instock i WHERE 1 = 1";
        Map<Integer, Object> valueMap = new HashMap<Integer, Object>();
        int index = 0;
        if (null != map.get("sdate"))
        {
            hql += " AND i.instockTime >= ?";
            valueMap.put(index++, map.get("sdate"));
        }
        if (null != map.get("sparepartNo"))
        {
            hql += " AND i.sparepartNo like ?";
            valueMap.put(index++, map.get("sparepartNo") + "%");
        }
        if (null != map.get("edate"))
        {
            hql += " AND i.instockTime <= ?";
            valueMap.put(index++, map.get("edate"));
        }  
        hql += " ORDER BY i.instockTime DESC";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        for (int i = 0; i < valueMap.size(); i++) 
        {
            query.setParameter(i, valueMap.get(i));
        }
        return query.list();
    }
}
