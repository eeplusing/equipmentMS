package com.moutum.equ.dto;

/************************************************************************************
 * @Title        : ProvideBillDto.java
 * @Description : 发放明细展示及数据导出类
 * @Author       : BianWeiqing
 * @DateTime     : 2015年8月19日 上午10:01:33
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class ProvideBillDto
{
    private long id;//明细ID
    
    private String equipmentNo;// 批次编号
    
    private String equipmentName;// 批次名称

    private String  equipresource;//来源
    
    private String equipmentModle;// 规格型号
    
    private String provideDate;//发放时间
    
    private int equipmentUseYears;// 使用年限
    
    private String putplace;//存放地点
    
    private int amount;//数量
    
    private String receiver;//领取人
    
    private int flag;//旗帜
    
    public int getFlag()
    {
        return flag;
    }

    public void setFlag(int flag)
    {
        this.flag = flag;
    }

    private String remark;//备注
    
    public String getPutplace()
    {
        return putplace;
    }
    
    public void setPutplace(String putplace)
    {
        this.putplace = putplace;
    }
    

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

    public int getEquipmentUseYears()
    {
        return equipmentUseYears;
    }

    public void setEquipmentUseYears(int equipmentUseYears)
    {
        this.equipmentUseYears = equipmentUseYears;
    }

    public String getEquipresource()
    {
        return equipresource;
    }

    public void setEquipresource(String equipresource)
    {
        this.equipresource = equipresource;
    }

    public String getProvideDate()
    {
        return provideDate;
    }

    public void setProvideDate(String provideDate)
    {
        this.provideDate = provideDate;
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

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

}