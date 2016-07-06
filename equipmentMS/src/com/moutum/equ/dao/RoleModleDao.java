package com.moutum.equ.dao;

import java.util.List;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.RoleModle;

/************************************************************************************
 * @Title        : RoleModleDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午11:27:07
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface RoleModleDao extends DaoSupport<RoleModle>
{

    /********************************************************************************
     * 根据角色ID查询该角色所拥有的RoleModle对象集合
     * @param roleId
     * @return 
     ********************************************************************************/
    List<RoleModle> getByRoleIds(long roleId);

    /********************************************************************************
     * 根据角色ID,模块ID查询是否有该RoleModle对象
     * @param roleId
     * @param modleId
     * @return 有则返回该RoleModle对象,否则返回null
     ********************************************************************************/
    RoleModle select(long roleId, long modleId);

    /********************************************************************************
     * 根据角色ID删除该角色所有的模块访问权限
     * @param roleId
     ********************************************************************************/
    void move(long roleId);
}