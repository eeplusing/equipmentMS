package com.moutum.equ.domain;
/************************************************************************************
 * @Title        : DatumFormat.java
 * @Description : 文档格式
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午3:06:18
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class DatumFormat
{
    private long datumFormatId;//格式ID
    
    private String datumFormatName;//格式名称
    
    private String remark;

    public long getDatumFormatId()
    {
        return datumFormatId;
    }

    public void setDatumFormatId(long datumFormatId)
    {
        this.datumFormatId = datumFormatId;
    }

    public String getDatumFormatName()
    {
        return datumFormatName;
    }

    public void setDatumFormatName(String datumFormatName)
    {
        this.datumFormatName = datumFormatName;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}