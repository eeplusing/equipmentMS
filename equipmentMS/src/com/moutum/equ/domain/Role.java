package com.moutum.equ.domain;
/************************************************************************************
 * @Title        : Role.java
 * @Description : 角色信息
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午11:04:23
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class Role
{
    private long roleId;//角色编号
    
    private String name;//名称
    
    private String describe;//描述

    public long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(long roleId)
    {
        this.roleId = roleId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescribe()
    {
        return describe;
    }

    public void setDescribe(String describe)
    {
        this.describe = describe;
    }
}