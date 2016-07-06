package com.moutum.equ.domain;
/************************************************************************************
 * @Title        : Modle.java
 * @Description : 功能模块信息
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午11:04:23
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class Modle
{
    private long modleId;//模块编号
    
    private String name;//名称
    
    private long parentId;//父模块编号
    
    private String url;//模块URL

    public long getModleId()
    {
        return modleId;
    }

    public void setModleId(long modleId)
    {
        this.modleId = modleId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getParentId()
    {
        return parentId;
    }

    public void setParentId(long parentId)
    {
        this.parentId = parentId;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}