package com.moutum.equ.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.moutum.equ.domain.Notice;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title        : NoticeAction.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月6日 下午4:15:38
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/

@Controller @Scope("prototype")
public class NoticeAction extends BaseAction<Notice> 

{
    private static final long serialVersionUID = 1075586575624315363L;
    
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    Logger logger = Logger.getLogger(NoticeAction.class);
    
    public String manage()
    {
        return SUCCESS;
    }
    
    public String list()
    {
        int pageSize = Integer.parseInt(request.getParameter("rows")); //当前PAGE的条数
        int pageNum = Integer.parseInt(request.getParameter("page"));//当前PAGE的PAGE数
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        try
        {
            String title = request.getParameter("title").trim();
            
            if((title != null) && (title.length() > 0))
            {
                map.put("title",  title );
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
        try
        {
            String publisher = request.getParameter("publisher").trim();
            if((publisher != null) && (publisher.length() > 0))
            {
                map.put("publisher", publisher);
            }
            
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
        try
        {
            String noticeTimeST = request.getParameter("noticeTimeST").trim();
            if((noticeTimeST != null) && (noticeTimeST.length() > 0))
            {
                map.put("noticeTimeST", DateUtil.stringToDate(noticeTimeST + " 00:00:00", DateUtil.YYYY_MM_DD_HH_MM_SS));
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
        try
        {
            String noticeTimeED = request.getParameter("noticeTimeED").trim();
            
            if(noticeTimeED != null && noticeTimeED.length() > 0)
            {
                map.put("noticeTimeED", DateUtil.stringToDate(noticeTimeED + " 23:59:59", DateUtil.YYYY_MM_DD_HH_MM_SS));
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        PageBean page = noticeService.getPage(pageNum, pageSize, map);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        //设置总数据量
        jsonMap.put("total", page.getRecordCount()); 
        //当前显示数据
        jsonMap.put("rows", page.getRecordList());
        
        result = JSONObject.fromObject(jsonMap);
        return "list";
    }
    public String openSaveWindow()
    {
        User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
        request.setAttribute("loginuser", loginuser);
        return "open_s";
    }
 
    public void save()
    {
        try
        {
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            Notice notice = new Notice();
            notice.setTitle(request.getParameter("title").trim());
            notice.setPublisher(request.getParameter("publisher").trim());
            notice.setContent(request.getParameter("content").trim());
            notice.setNoticeTime(new Date());
          
            switch (noticeService.save(notice))
            {
            case 1:
                response.getWriter().print("添加成功");
                logger.debug("[BWQ]---操作:发布公告,结果:成功,操作者:" + loginuser.getLoginAccount());
                break;

            default:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:发布公告,结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (IOException e)
        {
            logger.info(e);
        }
    }
   
    
    public String openModifyWindow()
    {
        long noticeId = Long.parseLong(request.getParameter("noticeId").trim());
        request.setAttribute("notice", noticeService.select(noticeId));
        return "open_d";
    }
    
    
    
    public void delete()
    {
        long noticeId = Long.parseLong(request.getParameter("noticeId").trim());
        try
        {
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (noticeService.delete(noticeId))
            {
            case 1:
                response.getWriter().print("删除成功");
                logger.debug("[BWQ]---操作:删除公告,结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
    
            case -1:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:删除公告,结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            default:
                response.getWriter().print("公告不存在");
                logger.debug("[BWQ]---操作:删除公告,结果:公告不存在,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
    }
}