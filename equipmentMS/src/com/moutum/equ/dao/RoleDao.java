package com.moutum.equ.dao;

import java.util.List;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.Role;

/************************************************************************************
 * @Title        : RoleDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午11:21:57
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface RoleDao extends DaoSupport<Role>
{
    Role select(String name);

    int getRecordCount();

    List<Role> getRecordList(int pageNum, int pageSize);

}