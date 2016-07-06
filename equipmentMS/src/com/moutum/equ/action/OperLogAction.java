package com.moutum.equ.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.moutum.equ.domain.OperLog;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.util.DateUtil;

/************************************************************************************
 * @Title        : UserAction.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月6日 下午4:15:38
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/

@Controller @Scope("prototype")
public class OperLogAction extends BaseAction<OperLog>
{
    private static final long serialVersionUID = 1075586571422315363L;
    
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    Logger logger = Logger.getLogger(OperLogAction.class);
    
    public String manage()
    {
        return SUCCESS;
    }
    
    public String list()
    {
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("page"));
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        try
        {
            int operType = Integer.parseInt(request.getParameter("operType").trim());
            if(operType >= 0)
            {
                map.put("operType",  operType);
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
        try
        {
            String operator = request.getParameter("operator").trim();
            if((operator != null) && (operator.length() > 0))
            {
                map.put("operator", operator);
            }
            
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
        try
        {
            String operLogTimeST = request.getParameter("operLogTimeST").trim();
            if((operLogTimeST != null) && (operLogTimeST.length() > 0))
            {
                map.put("operLogTimeST", DateUtil.stringToDate(operLogTimeST + " 00:00:00", DateUtil.YYYY_MM_DD_HH_MM_SS));
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        try
        {
            String operLogTimeED = request.getParameter("operLogTimeED").trim();
            if((operLogTimeED != null)&&operLogTimeED.length() > 0)
            {
                map.put("operLogTimeED", DateUtil.stringToDate(operLogTimeED + " 23:59:59", DateUtil.YYYY_MM_DD_HH_MM_SS));
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        PageBean page = operLogService.getPage(pageNum, pageSize, map);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        //设置总数据量
        jsonMap.put("total", page.getRecordCount());
        //当前显示数据
        jsonMap.put("rows", page.getRecordList());
        
        result = JSONObject.fromObject(jsonMap); 
        return "list";
    }
}