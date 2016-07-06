package com.moutum.equ.domain;

import java.io.Serializable;

/**
 * @Title        : EquipmentDatum.java
 * @Description :  物品文档关联信息
 * @Author       : BianWeiqing
 * @DateTime     : 2015-3-28 下午08:31:11
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 */
public class EquipmentDatum implements Serializable
{
    
    private static final long serialVersionUID = 1L;

    private long equipmentId;//设备ID
    
    private long datumId;//文档ID

    public long getEquipmentId()
    {
        return equipmentId;
    }

    public void setEquipmentId(long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public long getDatumId()
    {
        return datumId;
    }

    public void setDatumId(long datumId)
    {
        this.datumId = datumId;
    }
    
    @Override
    public int hashCode()
    {
        int equCode = (getEquipmentId() + "").hashCode();
        int datumCode = (getDatumId() + "").hashCode();
        return equCode*12 + datumCode*31;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof EquipmentDatum))
        {
            return false;
        }
        EquipmentDatum ed = (EquipmentDatum)obj;
       return (this.equipmentId == ed.getEquipmentId() && this.datumId == ed.getDatumId());
    }
}