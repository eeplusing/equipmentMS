package com.moutum.equ.domain;

import java.util.Date;

import com.moutum.equ.util.DateUtil;

/************************************************************************************
 * @Title        : Equipment.java
 * @Description : 设备信息
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午12:00:29
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class Equipment
{
    private long equipmentId;// 设备ID

    private String equipmentNo;// 设备编号

    private String equipmentName;// 设备名称

    private String equipmentModle;// 规格型号

    private String typeName;// 设备类型

    private String equipmentProducer;// 生产商

    private String equipmentSupplier;// 供应商

    private Date equipmentBuyTime;// 购置时间(yyyy-MM-dd)

    private String equipmentBuyType;// 购置方式

    private String equipmentRecipient;// 设备接收人

    private String equipmentProvider;// 设备发放人

    private String equipmentDirector;// 设备监督人

    private String useState;// 使用状况

    private Date equipmentStartDate;// 启用日期(yyyy-MM-dd)

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

    public Date getEquipmentBuyTime()
    {
        return equipmentBuyTime;
    }

    public void setEquipmentBuyTime(Date equipmentBuyTime)
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

    @Override
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer("{\"equipmentNo\":\"" + this.equipmentNo + "\",");
        stringBuffer.append("\"equipmentName\":\"" + this.equipmentName + "\",");
        stringBuffer.append("\"equipmentModle\":\"" + this.equipmentModle + "\",");
        stringBuffer.append("\"typeName\":\"" + this.typeName + "\",");
        stringBuffer.append("\"equipmentProducer\":\"" + this.equipmentProducer + "\",");
        stringBuffer.append("\"equipmentSupplier\":\"" + this.equipmentSupplier + "\",");
        stringBuffer.append("\"equipmentBuyTime\":\"" + DateUtil.dateToString(this.equipmentBuyTime, DateUtil.YYYY_MM_DD) + "\",");
        stringBuffer.append("\"equipmentBuyType\":\"" + this.equipmentBuyType + "\",");
        stringBuffer.append("\"equipmentRecipient\":\"" + this.equipmentRecipient + "\",");
        stringBuffer.append("\"equipmentProvider\":\"" + this.equipmentProvider + "\",");
        stringBuffer.append("\"equipmentDirector\":\"" + this.equipmentDirector + "\",");
        stringBuffer.append("\"useState\":\"" + this.useState + "\",");
        stringBuffer.append("\"equipmentStartDate\":\"" + DateUtil.dateToString(this.equipmentStartDate, DateUtil.YYYY_MM_DD) + "\",");
        stringBuffer.append("\"equipmentUseYears\":\"" + this.equipmentUseYears + "\",");
        stringBuffer.append("\"equipmentMonetaryAmount\":\"" + this.equipmentMonetaryAmount + "\",");
        stringBuffer.append("\"amount\":\"" + this.amount + "\",");
        stringBuffer.append("\"areaCode\":\"" + this.areaCode + "\",");
        stringBuffer.append("\"remark\":\"" + this.remark + "\"}");
        return stringBuffer.toString();
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