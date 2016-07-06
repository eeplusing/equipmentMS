package com.moutum.equ.domain;

import java.util.Date;

/************************************************************************************
 * @Title        : Iodepot.java
 * @Description : 出入库记录信息
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午2:45:58
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class Iodepot
{
    private long iodepotId;//信息编号
    
    private String goodId;//物品编号
    
    private long typeId;//类别
    
    private int iodepotOpertype;//操作类型(0:出库;1:入库)
    
    private Date iodepotTime;//时间(yyyy-MM-dd HH:mm:ss)
    
    private String iodepotGetter;//出库人
    
    private String iodepotReturner;//入库人
    
    private String iodepotOperator;//操作人员
    
    private String iodepotRemark;//备注信息
    
    private String retainField1;//预留字段1
    
    private String retainField2;//预留字段2
    
    private String retainField3;//预留字段3

    public long getIodepotId()
    {
        return iodepotId;
    }

    public void setIodepotId(long iodepotId)
    {
        this.iodepotId = iodepotId;
    }

    public String getGoodId()
    {
        return goodId;
    }

    public void setGoodId(String goodId)
    {
        this.goodId = goodId;
    }

    public long getTypeId()
    {
        return typeId;
    }

    public void setTypeId(long typeId)
    {
        this.typeId = typeId;
    }

    public int getIodepotOpertype()
    {
        return iodepotOpertype;
    }

    public void setIodepotOpertype(int iodepotOpertype)
    {
        this.iodepotOpertype = iodepotOpertype;
    }

    public Date getIodepotTime()
    {
        return iodepotTime;
    }

    public void setIodepotTime(Date iodepotTime)
    {
        this.iodepotTime = iodepotTime;
    }

    public String getIodepotGetter()
    {
        return iodepotGetter;
    }

    public void setIodepotGetter(String iodepotGetter)
    {
        this.iodepotGetter = iodepotGetter;
    }

    public String getIodepotReturner()
    {
        return iodepotReturner;
    }

    public void setIodepotReturner(String iodepotReturner)
    {
        this.iodepotReturner = iodepotReturner;
    }

    public String getIodepotOperator()
    {
        return iodepotOperator;
    }

    public void setIodepotOperator(String iodepotOperator)
    {
        this.iodepotOperator = iodepotOperator;
    }

    public String getIodepotRemark()
    {
        return iodepotRemark;
    }

    public void setIodepotRemark(String iodepotRemark)
    {
        this.iodepotRemark = iodepotRemark;
    }

    public String getRetainField1()
    {
        return retainField1;
    }

    public void setRetainField1(String retainField1)
    {
        this.retainField1 = retainField1;
    }

    public String getRetainField2()
    {
        return retainField2;
    }

    public void setRetainField2(String retainField2)
    {
        this.retainField2 = retainField2;
    }

    public String getRetainField3()
    {
        return retainField3;
    }

    public void setRetainField3(String retainField3)
    {
        this.retainField3 = retainField3;
    }
}