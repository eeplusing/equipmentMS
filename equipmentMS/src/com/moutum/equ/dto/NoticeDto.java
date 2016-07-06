package com.moutum.equ.dto;

/************************************************************************************
 * @Title        : NoticeDto.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年4月2日 下午3:49:28
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class NoticeDto
{
    private long noticeId;//id
    
    private String title;//标题
    
    private String content;//内容
    
    private String noticeTime;//发布时间
    
    private String publisher;//发布人

    public long getNoticeId()
    {
        return noticeId;
    }

    public void setNoticeId(long noticeId)
    {
        this.noticeId = noticeId;
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

    public String getNoticeTime()
    {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime)
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