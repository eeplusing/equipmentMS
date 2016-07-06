package com.moutum.equ.domain;

import java.io.Serializable;

/************************************************************************************
 * @Title        : RoleRight.java
 * @Description : 角色权限
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午11:04:23
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class RoleRight implements Serializable
{
    private static final long serialVersionUID = -3143690457159860447L;

    private long roleId;//角色编号
    
    private long rightId;//权限编号

    public long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(long roleId)
    {
        this.roleId = roleId;
    }

    public long getRightId()
    {
        return rightId;
    }

    public void setRightId(long rightId)
    {
        this.rightId = rightId;
    }

    @Override
    public int hashCode()
    {
        int roleCode = (getRoleId() + "").hashCode();
        int rightCode = (getRightId() + "").hashCode();
        return roleCode*12 + rightCode*31;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof RoleRight))
        {
            return false;
        }
        RoleRight r = (RoleRight)obj;
        return (this.rightId == r.getRightId() && this.roleId == r.getRoleId());
    }
}