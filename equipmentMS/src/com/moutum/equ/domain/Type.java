package com.moutum.equ.domain;
/************************************************************************************
 * @Title        : Type.java
 * @Description : 类别信息
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午2:05:29
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class Type
{
    private long typeId;//物品类别ID
    
    private String typeName;//类别名
    
    private long parentId;//父类ID

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

    public long getParentId()
    {
        return parentId;
    }

    public void setParentId(long parentId)
    {
        this.parentId = parentId;
    }
}