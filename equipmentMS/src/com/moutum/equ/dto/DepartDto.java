package com.moutum.equ.dto;
/************************************************************************************
 * @Title        : DepartDto.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年4月3日 上午10:55:51
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class DepartDto
{
    private long departId;//部门ID
    
    private String departName;//部门名
    
    private String parentDepartName;//上级部门ID

    public long getDepartId()
    {
        return departId;
    }

    public void setDepartId(long departId)
    {
        this.departId = departId;
    }

    public String getDepartName()
    {
        return departName;
    }

    public void setDepartName(String departName)
    {
        this.departName = departName;
    }

    public String getParentDepartName()
    {
        return parentDepartName;
    }

    public void setParentDepartName(String parentDepart)
    {
        this.parentDepartName = parentDepart;
    }
    
    
}

