package com.moutum.equ.domain;

import java.util.Date;

/************************************************************************************
 * @Title        : ReceiveBill.java
 * @Description : 设备领取明细
 * @Author       : BianWeiqing
 * @DateTime     : 2015年8月19日 上午9:48:01
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class ReceiveBill
{
    private long id;//ID
    
    private String stationName;//站点名
    
    private int amount;//数量
    
    private String receiver;//领取人
    
    private Date provideDate;//发放时间
    
    private String remark;//备注

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getStationName()
    {
        return stationName;
    }

    public void setStationName(String stationName)
    {
        this.stationName = stationName;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public String getReceiver()
    {
        return receiver;
    }

    public void setReceiver(String receiver)
    {
        this.receiver = receiver;
    }

    public Date getProvideDate()
    {
        return provideDate;
    }

    public void setProvideDate(Date provideDate)
    {
        this.provideDate = provideDate;
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