package com.moutum.equ.dto;

/************************************************************************************
 * @Title        : EquipmentDto.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月26日 下午3:50:03
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class EquipmentDto
{
    private long equipmentId;// 设备ID

    private String equipmentNo;// 设备编号

    private String equipmentName;// 设备名称

    private String equipmentModle;// 规格型号

    private String typeName;// 设备类型

    private String equipmentProducer;// 生产商

    private String equipmentSupplier;// 供应商

    private String equipmentBuyTime;// 购置时间(yyyy-MM-dd)

    private String equipmentBuyType;// 购置方式

    private String equipmentRecipient;// 设备接收人

    private String equipmentProvider;// 设备发放人

    private String equipmentDirector;// 设备监督人

    private String useState;// 使用状况

    private String startDate;// 启用日期(yyyy-MM-dd)

    private int equipmentUseYears;// 使用年限

    private double equipmentMonetaryAmount;// 购买金额

    private int amount;// 数量
    
    private int  surplusAmount;// 剩余数量

    private String areaCode;// 区段号
    
    private String remark;//备注

    public long getEquipmentId()
    {
        return equipmentId;
    }

    public void setEquipmentId(long equipmentId)
    {
        this.equipmentId = equipmentId;
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

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getEquipmentProducer()
    {
        return equipmentProducer;
    }

    public void setEquipmentProducer(String equipmentProducer)
    {
        this.equipmentProducer = equipmentProducer;
    }

    public String getEquipmentSupplier()
    {
        return equipmentSupplier;
    }

    public void setEquipmentSupplier(String equipmentSupplier)
    {
        this.equipmentSupplier = equipmentSupplier;
    }

    public String getEquipmentBuyTime()
    {
        return equipmentBuyTime;
    }

    public void setEquipmentBuyTime(String equipmentBuyTime)
    {
        this.equipmentBuyTime = equipmentBuyTime;
    }

    public String getEquipmentBuyType()
    {
        return equipmentBuyType;
    }

    public void setEquipmentBuyType(String equipmentBuyType)
    {
        this.equipmentBuyType = equipmentBuyType;
    }

    public String getEquipmentRecipient()
    {
        return equipmentRecipient;
    }

    public void setEquipmentRecipient(String equipmentRecipient)
    {
        this.equipmentRecipient = equipmentRecipient;
    }

    public String getEquipmentProvider()
    {
        return equipmentProvider;
    }

    public void setEquipmentProvider(String equipmentProvider)
    {
        this.equipmentProvider = equipmentProvider;
    }

    public String getEquipmentDirector()
    {
        return equipmentDirector;
    }

    public void setEquipmentDirector(String equipmentDirector)
    {
        this.equipmentDirector = equipmentDirector;
    }

    public String getUseState()
    {
        return useState;
    }

    public void setUseState(String useState)
    {
        this.useState = useState;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public int getEquipmentUseYears()
    {
        return equipmentUseYears;
    }

    public void setEquipmentUseYears(int equipmentUseYears)
    {
        this.equipmentUseYears = equipmentUseYears;
    }

    public double getEquipmentMonetaryAmount()
    {
        return equipmentMonetaryAmount;
    }

    public void setEquipmentMonetaryAmount(double equipmentMonetaryAmount)
    {
        this.equipmentMonetaryAmount = equipmentMonetaryAmount;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public String getAreaCode()
    {
        return areaCode;
    }

    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
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