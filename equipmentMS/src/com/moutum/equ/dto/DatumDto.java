package com.moutum.equ.dto;
/********************************************************************************
 * @Title        : DatumDto.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015-3-28 下午11:51:53
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ********************************************************************************/
public class DatumDto
{
    private long datumId;//文档ID
    
    private String datumName;//文档名称
    
    private String datumTypeName;//文档类别
    
    private String datumFormatName;//文档格式
    
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

    public String getDatumTypeName()
    {
        return datumTypeName;
    }

    public void setDatumTypeName(String datumTypeName)
    {
        this.datumTypeName = datumTypeName;
    }

    public String getDatumFormatName()
    {
        return datumFormatName;
    }

    public void setDatumFormatName(String datumFormatName)
    {
        this.datumFormatName = datumFormatName;
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