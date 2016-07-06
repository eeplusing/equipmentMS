package com.moutum.equ.domain;

import java.io.Serializable;

/************************************************************************************
 * @Title        : Datum.java
 * @Description : 文档信息
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午2:59:49
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class Datum implements Serializable
{
    private static final long serialVersionUID = -7856563860400971363L;

    private long datumId;//文档ID
    
    private String datumName;//文档名称
    
    private long datumTypeId;//文档类别
    
    private long datumFormatId;//文档格式
    
    private String datumPath;//文档位置

    public long getDatumId()
    {
        return datumId;
    }

    public void setDatumId(long datumId)
    {
        this.datumId = datumId;
    }

    public String getDatumName()
    {
        return datumName;
    }

    public void setDatumName(String datumName)
    {
        this.datumName = datumName;
    }

    public long getDatumTypeId()
    {
        return datumTypeId;
    }

    public void setDatumTypeId(long datumTypeId)
    {
        this.datumTypeId = datumTypeId;
    }

    public long getDatumFormatId()
    {
        return datumFormatId;
    }

    public void setDatumFormatId(long datumFormatId)
    {
        this.datumFormatId = datumFormatId;
    }

    public String getDatumPath()
    {
        return datumPath;
    }

    public void setDatumPath(String datumPath)
    {
        this.datumPath = datumPath;
    }
}