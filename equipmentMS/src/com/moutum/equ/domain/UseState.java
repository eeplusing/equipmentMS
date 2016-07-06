package com.moutum.equ.domain;
/************************************************************************************
 * @Title        : Usestate.java
 * @Description : 使用状况分类
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午2:15:56
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class UseState
{
    private long useStateId;//状态ID
    
    private String useStateName;//状况名称
    
    private String useStateRemark;//备注信息

    public long getUseStateId()
    {
        return useStateId;
    }

    public void setUseStateId(long useStateId)
    {
        this.useStateId = useStateId;
    }

    public String getUseStateName()
    {
        return useStateName;
    }

    public void setUseStateName(String useStateName)
    {
        this.useStateName = useStateName;
    }

    public String getUseStateRemark()
    {
        return useStateRemark;
    }

    public void setUseStateRemark(String useStateRemark)
    {
        this.useStateRemark = useStateRemark;
    }
}