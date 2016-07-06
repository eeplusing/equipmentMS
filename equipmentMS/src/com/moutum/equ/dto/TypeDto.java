package com.moutum.equ.dto;
/************************************************************************************
 * @Title        : TypeDto.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年5月12日 下午2:57:50
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class TypeDto
{
    private long typeId;//物品类别ID
    
    private String typeName;//类别名
    
    private String parentName;//父类名

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

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }
}