package com.moutum.equ.domain;

import java.io.Serializable;

/************************************************************************************
 * @Title        : RoleModle.java
 * @Description : 角色模块
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午11:04:23
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class RoleModle implements Serializable
{
    private static final long serialVersionUID = -1726140115765395015L;

    private long roleId;//角色编号
    
    private long modleId;//模块编号

    public long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(long roleId)
    {
        this.roleId = roleId;
    }

    public long getModleId()
    {
        return modleId;
    }

    public void setModleId(long modleId)
    {
        this.modleId = modleId;
    }

    @Override
    public int hashCode()
    {
        int roleCode = (getRoleId() + "").hashCode();
        int modleCode = (getModleId() + "").hashCode();
        return roleCode*12 + modleCode*31;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof RoleModle))
        {
            return false;
        }
        RoleModle rm = (RoleModle)obj;
        return (this.modleId == rm.getModleId() && this.roleId == rm.getRoleId());
    }
}