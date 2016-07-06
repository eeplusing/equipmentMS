package com.moutum.equ.dto;
/************************************************************************************
 * @Title        : Bill.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月31日 上午9:39:40
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class Bill
{
    private long typeId;
    
    private String typeName;
    
    private int amount;
    
    private int minamount;
    
    public long getTypeId()
    {
        return typeId;
    }
    
    public void setTypeId(long typeId)
    {
        this.typeId = typeId;
    }
    
    public String getTypeName()
    {
        return typeName;
    }
    
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }
    
    public int getAmount()
    {
        return amount;
    }
    
    public void setAmount(int amount)
    {
        this.amount = amount;
    }
    
    public int getMinamount()
    {
        return minamount;
    }
    
    public void setMinamount(int minamount)
    {
        this.minamount = minamount;
    }
    
    @Override
    public String toString()
    {
        return this.typeId + "|" + this.typeName + "|" + this.amount + "|" + this.minamount;
    }
}