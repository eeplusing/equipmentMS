package com.moutum.equ.dao;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.RoleRight;

/************************************************************************************
 * @Title        : RoleRightDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午11:23:56
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface RoleRightDao extends DaoSupport<RoleRight>
{

    /********************************************************************************
     * 根据角色ID,权限ID查询是否有该RoleRight对象
     * @param roleId
     * @param rightId
     * @return 有则返回该RoleRight对象,否则返回null
     ********************************************************************************/
    RoleRight select(long roleId, long rightId);

    /********************************************************************************
     * 根据角色ID删除该角色所有的系统操作权限
     * @param roleId
     ********************************************************************************/
    void move(long roleId);
    
}