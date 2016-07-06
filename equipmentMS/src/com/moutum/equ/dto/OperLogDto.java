package com.moutum.equ.dto;

/************************************************************************************
 * @Title        : OperLogDto.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月31日 下午3:04:15
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class OperLogDto
{
    private long operLogId;//日志ID
    
    private int operType;//操作类型
    
    private String operLogContent;//操作信息
    
    private String operLogTime;//操作时间(yyyy-MM-dd HH:mm:ss)
    
    private String operator;//操作人员
    
    private String operLogRemark;//备注

    public long getOperLogId()
    {
        return operLogId;
    }

    public void setOperLogId(long operLogId)
    {
        this.operLogId = operLogId;
    }

    public int getOperType()
    {
        return operType;
    }

    public void setOperType(int operType)
    {
        this.operType = operType;
    }

    public String getOperLogContent()
    {
        return operLogContent;
    }

    public void setOperLogContent(String operLogContent)
    {
        this.operLogContent = operLogContent;
    }

    public String getOperLogTime()
    {
        return operLogTime;
    }

    public void setOperLogTime(String operLogTime)
    {
        this.operLogTime = operLogTime;
    }

    public String getOperator()
    {
        return operator;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public String getOperLogRemark()
    {
        return operLogRemark;
    }

    public void setOperLogRemark(String operLogRemark)
    {
        this.operLogRemark = operLogRemark;
    }
}