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

import com.moutum.equ.domain.Spacstock;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.opensymphony.xwork2.ActionContext;

/********************************************************************************
 * @Title        : SpacstockAction.java
 * @Description : 
 * @Author       : HuangWei
 * @DateTime     : 2015-8-21  17:19:09
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ********************************************************************************/
@Controller @Scope("prototype")
public class SpacstockAction  extends BaseAction<Spacstock> 
{

	private static final long serialVersionUID = 3442696719256515039L;
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	
	Logger logger = Logger.getLogger(SpacstockAction.class);
	
	public String manage()
	{
		return "manage";
	}

	public String list()
	 {
		String modle = request.getParameter("modle");
		int pageNum = Integer.parseInt(request.getParameter("page"));//当前页数
		int pageSize = Integer.parseInt(request.getParameter("rows")); //当前页面显示行数
		if(modle.equals("list"))
		{
	        
	        PageBean page = spacstockService.getPage(pageNum, pageSize);
	        
	        Map<String, Object> jsonMap = new HashMap<String, Object>();
	        //设置总数据量
	        jsonMap.put("total", page.getRecordCount()); 
	        //当前显示数据
	        jsonMap.put("rows", page.getRecordList());
	        
	        result = JSONObject.fromObject(jsonMap);
	        return "list";
		}
		else
		{
	        PageBean page = spacstockService.getPage(pageNum, pageSize, modle);
	        
	        Map<String, Object> jsonMap = new HashMap<String, Object>();
	        //设置总数据量
	        jsonMap.put("total", page.getRecordCount()); 
	        //当前显示数据
	        jsonMap.put("rows", page.getRecordList());
	        
	        result = JSONObject.fromObject(jsonMap);
	        return "list";
		}
	 }
	
	 public void modify()
	    {
	        User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
	        
	        try
	        {
	            long spacstockId = Long.parseLong(request.getParameter("spacstockId").trim());
	            int minamount = Integer.parseInt(request.getParameter("minamount").trim());
	            
	            switch (spacstockService.modify(spacstockId, minamount))
	            {
	            case 1:
	                response.getWriter().print("设置成功");
	                logger.debug("[BWQ]---操作:设置备品库存的上下限,结果:设置成功,操作者:" + loginuser.getLoginAccount());
	                break;

	            case 0:
	                response.getWriter().print("设置失败");
	                logger.debug("[BWQ]---操作:设置备品库存的上下限,结果:设置失败,操作者:" + loginuser.getLoginAccount());
	                break;
	            }
	        }
	        catch (Exception e)
	        {
	            logger.info(e);
	            try
	            {
	                response.getWriter().print("操作失败");
	                logger.debug("[BWQ]---操作:设置备品库存的上下限,结果:系统错误,操作者:" + loginuser.getLoginAccount());
	            }
	            catch (Exception e1)
	            {
	                logger.info(e1);
	            }
	        }
	    }
	 
	 public String alert()
	 {
		 return "alert";
	 }
	
}
