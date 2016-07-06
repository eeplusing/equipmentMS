package com.moutum.equ.domain;
/************************************************************************************
 * @Title        : Right.java
 * @Description : 权限信息
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午10:44:19
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class Right
{
    private long rightId;//权限编号
    
    private String name;//权限名称
    
    private String functionName;//权限所对应的请求名称
    
    private long modleId;//权限所属模块编号
    
    private String remark;//权限描述

    public long getRightId()
    {
        return rightId;
    }

    public void setRightId(long rightId)
    {
        this.rightId = rightId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getFunctionName()
    {
        return functionName;
    }

    public void setFunctionName(String functionName)
    {
        this.functionName = functionName;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public long getModleId()
    {
        return modleId;
    }

    public void setModleId(long modleId)
    {
        this.modleId = modleId;
    }
}