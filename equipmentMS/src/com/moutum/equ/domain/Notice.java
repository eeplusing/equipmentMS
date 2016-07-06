package com.moutum.equ.domain;

import java.util.Date;

/************************************************************************************
 * @Title        : Notice.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年4月1日 上午9:47:32
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class Notice
{

    private long id;//id
    
    private String title;//标题
    
    private String content;//内容
    
    private Date noticeTime;//发布时间
    
    private String publisher;//发布人

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Date getNoticeTime()
    {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime)
    {
        this.noticeTime = noticeTime;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }
}