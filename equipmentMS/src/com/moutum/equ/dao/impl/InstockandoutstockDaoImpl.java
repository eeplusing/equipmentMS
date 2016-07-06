package com.moutum.equ.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moutum.equ.base.DaoSupportImpl;
import com.moutum.equ.dao.InstockandoutstockDao;
import com.moutum.equ.domain.Instockandoutstock;

@Repository @Transactional
public class InstockandoutstockDaoImpl extends DaoSupportImpl<Instockandoutstock> implements InstockandoutstockDao 
{

	@Resource SessionFactory sessionFactory;
	
}
