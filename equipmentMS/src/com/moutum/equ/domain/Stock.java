package com.moutum.equ.domain;
/************************************************************************************
 * @Title        : Stock.java
 * @Description : 库存信息
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午2:35:40
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class Stock
{
    private long stockId;//库存ID
    
    private String goodId;//物品编号
    
    private String goodName;//物品名称
    
    private long typeId;//类别ID
    
    private String stockPlace;//库位
    
    private int stockAmount;//库存量
    
    private int stockMinamount;//安全库存量
    
    public long getStockId()
    {
        return stockId;
    }

    public void setStockId(long stockId)
    {
        this.stockId = stockId;
    }

    public String getGoodId()
    {
        return goodId;
    }

    public void setGoodId(String goodId)
    {
        this.goodId = goodId;
    }

    public String getGoodName()
    {
        return goodName;
    }

    public void setGoodName(String goodName)
    {
        this.goodName = goodName;
    }

    public long getTypeId()
    {
        return typeId;
    }

    public void setTypeId(long typeId)
    {
        this.typeId = typeId;
    }

    public String getStockPlace()
    {
        return stockPlace;
    }

    public void setStockPlace(String stockPlace)
    {
        this.stockPlace = stockPlace;
    }

    public int getStockAmount()
    {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount)
    {
        this.stockAmount = stockAmount;
    }

    public int getStockMinamount()
    {
        return stockMinamount;
    }

    public void setStockMinamount(int stockMinamount)
    {
        this.stockMinamount = stockMinamount;
    }

}