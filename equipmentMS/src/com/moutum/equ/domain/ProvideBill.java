package com.moutum.equ.domain;

import java.util.Date;
import java.util.Set;

/************************************************************************************
 * @Title        : ProvideBill.java
 * @Description : 设备发放明细
 * @Author       : BianWeiqing
 * @DateTime     : 2015年8月19日 上午9:27:25
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class ProvideBill
{
    private long id;//明细ID
    
    private String equipmentNo;// 批次编号
    
    private String equipmentName;// 批次名称

    private String equipmentModle;// 规格型号
    
    private int amount;//数量
    
    private int surplusAmount;//数量
    
    private Date equipmentStartDate;// 启用日期(yyyy-MM-dd)
    
    private int equipmentUseYears;// 使用年限
    
    private String areaCode;// 区段号
    
    private Set<ReceiveBill> receiveBills;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getEquipmentNo()
    {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo)
    {
        this.equipmentNo = equipmentNo;
    }

    public String getEquipmentName()
    {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName)
    {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentModle()
    {
        return equipmentModle;
    }

    public void setEquipmentModle(String equipmentModle)
    {
        this.equipmentModle = equipmentModle;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public Date getEquipmentStartDate()
    {
        return equipmentStartDate;
    }

    public void setEquipmentStartDate(Date equipmentStartDate)
    {
        this.equipmentStartDate = equipmentStartDate;
    }

    public int getEquipmentUseYears()
    {
        return equipmentUseYears;
    }

    public void setEquipmentUseYears(int equipmentUseYears)
    {
        this.equipmentUseYears = equipmentUseYears;
    }

    public String getAreaCode()
    {
        return areaCode;
    }

    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }

    public Set<ReceiveBill> getReceiveBills()
    {
        return receiveBills;
    }

    public void setReceiveBills(Set<ReceiveBill> receiveBills)
    {
        this.receiveBills = receiveBills;
    }

    public int getSurplusAmount()
    {
        return surplusAmount;
    }

    public void setSurplusAmount(int surplusAmount)
    {
        this.surplusAmount = surplusAmount;
    }
}