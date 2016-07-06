package com.moutum.equ.dto;

/********************************************************************************
 * @Title        : SparepartDto.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015-3-29 下午09:17:28
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ********************************************************************************/
public class SparepartDto
{
    
    private long sparepartId;//备品ID
    
    private String sparepartNo;//备品编号
    
    private String sparepartName;//备品名称
    
    private String sparepartModle;//规格型号
    
    private String typeName;//类别名称
    
    private String sparepartUnit;//计量单位
    
    private double sparepartPrice;//单价
    
    private String sparepartProducer;//生产商
    
    private String sparepartSupplier;//经销商
    
    private String sparepartSetplace;//安放地点
    
    private String sparepartStartDate;//更换时间(yyyy-MM-dd)
    
    private int sparepartUsemonths;//更换周期,单位为"月"
    
    private String sparepartRemark;//备注信息
    
    private int stockNum;//库存数量

    public long getSparepartId()
    {
        return sparepartId;
    }

    public void setSparepartId(long sparepartId)
    {
        this.sparepartId = sparepartId;
    }

    public String getSparepartNo()
    {
        return sparepartNo;
    }

    public void setSparepartNo(String sparepartNo)
    {
        this.sparepartNo = sparepartNo;
    }

    public String getSparepartName()
    {
        return sparepartName;
    }

    public void setSparepartName(String sparepartName)
    {
        this.sparepartName = sparepartName;
    }

    public String getSparepartModle()
    {
        return sparepartModle;
    }

    public void setSparepartModle(String sparepartModle)
    {
        this.sparepartModle = sparepartModle;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getSparepartUnit()
    {
        return sparepartUnit;
    }

    public void setSparepartUnit(String sparepartUnit)
    {
        this.sparepartUnit = sparepartUnit;
    }

    public double getSparepartPrice()
    {
        return sparepartPrice;
    }

    public void setSparepartPrice(double sparepartPrice)
    {
        this.sparepartPrice = sparepartPrice;
    }

    public String getSparepartProducer()
    {
        return sparepartProducer;
    }

    public void setSparepartProducer(String sparepartProducer)
    {
        this.sparepartProducer = sparepartProducer;
    }

    public String getSparepartSupplier()
    {
        return sparepartSupplier;
    }

    public void setSparepartSupplier(String sparepartSupplier)
    {
        this.sparepartSupplier = sparepartSupplier;
    }

    public String getSparepartSetplace()
    {
        return sparepartSetplace;
    }

    public void setSparepartSetplace(String sparepartSetplace)
    {
        this.sparepartSetplace = sparepartSetplace;
    }

    public String getSparepartStartDate()
    {
        return sparepartStartDate;
    }

    public void setSparepartStartDate(String sparepartStartDate)
    {
        this.sparepartStartDate = sparepartStartDate;
    }

    public int getSparepartUsemonths()
    {
        return sparepartUsemonths;
    }

    public void setSparepartUsemonths(int sparepartUsemonths)
    {
        this.sparepartUsemonths = sparepartUsemonths;
    }

    public String getSparepartRemark()
    {
        return sparepartRemark;
    }

    public void setSparepartRemark(String sparepartRemark)
    {
        this.sparepartRemark = sparepartRemark;
    }

    public int getStockNum()
    {
        return stockNum;
    }

    public void setStockNum(int stockNum)
    {
        this.stockNum = stockNum;
    }
}