package com.moutum.equ.domain;
/************************************************************************************
 * @Title        : DatumType.java
 * @Description : 文档类别
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午3:04:28
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class DatumType
{
    private long datumTypeId;//类别ID
    
    private String datumTypeName;//类别名称
    
    private String remark;//描述

    public long getDatumTypeId()
    {
        return datumTypeId;
    }

    public void setDatumTypeId(long datumTypeId)
    {
        this.datumTypeId = datumTypeId;
    }

    public String getDatumTypeName()
    {
        return datumTypeName;
    }

    public void setDatumTypeName(String datumTypeName)
    {
        this.datumTypeName = datumTypeName;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}