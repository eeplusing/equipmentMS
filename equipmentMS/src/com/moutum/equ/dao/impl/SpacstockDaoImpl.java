package com.moutum.equ.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moutum.equ.base.DaoSupportImpl;
import com.moutum.equ.dao.SpacstockDao;
import com.moutum.equ.domain.Spacstock;



/************************************************************************************
 * @Title        : SpacstockDaoImpl.java
 * @Description : 
 * @Author       : HuangWei
 * @DateTime     : 2015年8月21日 下午17:41:30
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Repository @Transactional
public class SpacstockDaoImpl extends DaoSupportImpl<Spacstock> implements SpacstockDao
{

	@Resource SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public Spacstock select(String spacstockNo) 
	{
		Spacstock spacstockFind = null;
		List<Spacstock> list = sessionFactory.getCurrentSession().createQuery("FROM Spacstock s WHERE s.sparepartNo = ?").setParameter(0,spacstockNo).list();
		if(0 < list.size())
		{
			spacstockFind = list.get(0);
		}
		return spacstockFind;
	}

	@Override
	public void modify(long spacstockId, int minamount) 
	{
		sessionFactory.getCurrentSession().createQuery("UPDATE Spacstock s SET s.spacstockMinamount = ? WHERE s.spacstockId = ?").setParameter(0, minamount).setParameter(1, spacstockId).executeUpdate();
	}

}
