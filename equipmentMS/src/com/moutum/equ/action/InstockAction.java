package com.moutum.equ.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.moutum.equ.domain.Instock;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.util.DateUtil;
import com.moutum.equ.util.DeCode;
import com.moutum.equ.util.XMLUtil;
import com.opensymphony.xwork2.ActionContext;

/********************************************************************************
 * @Title        : InstockAction.java
 * @Description : 
 * @Author       : HuangWei
 * @DateTime     : 2015-8-19  18:19:09
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ********************************************************************************/

@Controller @Scope("prototype")
public class InstockAction extends BaseAction<Instock> 
{
	private static final long serialVersionUID = -5849242135594060230L;
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	
	Logger logger = Logger.getLogger(InstockAction.class);
	
	 public String list()
	 {
	     try
         {
	         int pageNum = Integer.parseInt(request.getParameter("page"));//当前页数
	            int pageSize = Integer.parseInt(request.getParameter("rows")); //当前页面显示行数
	            
	            String sparepartNo = request.getParameter("sparepartNo");
	            String instockTimeStart = request.getParameter("instockTimeStart").trim();
	            String instockTimeEnd = request.getParameter("instockTimeEnd").trim();
	            Map<String,Object> map = new HashMap<String,Object>();
	            String requestType = request.getParameter("requestType").trim();
	            
	            if(null == sparepartNo || "".equals(sparepartNo.trim()))
	            {
	                sparepartNo = "";
	            }
	            map.put("sparepartNo", sparepartNo);
	            
	            if(!"".equals(requestType))
	            {
	                map.put("requestType", requestType);
	            }
	            if(!"".equals(instockTimeStart.trim()))
	            {
	                map.put("instockTimeStart", DateUtil.stringToDate( instockTimeStart+" 00:00:00", DateUtil.YYYY_MM_DD_HH_MM_SS));
	            }
	            if(!"".equals(instockTimeEnd.trim()))
	            {
	                map.put("instockTimeEnd", DateUtil.stringToDate(instockTimeEnd+" 23:59:59", DateUtil.YYYY_MM_DD_HH_MM_SS));
	            }
	            
	            PageBean page = instockService.getPage(pageNum, pageSize, map);
	            
	            Map<String, Object> jsonMap = new HashMap<String, Object>();
	            //设置总数据量
	            jsonMap.put("total", page.getRecordCount()); 
	            //当前显示数据
	            jsonMap.put("rows", page.getRecordList());
	            
	            result = JSONObject.fromObject(jsonMap);
         }
         catch (Exception e)
         {
             logger.info(e.getMessage());
         }
	     return "list";	
	 }

	 public String openWindow()
	 {
		 request.setAttribute("types", instockService.getSelectLists());
	     return "open";
	 }
	 
	 /********************************************************************************
	 * 根据输入的备品编号生成二维码图片并将图片位置返回页面用于显示
	 ********************************************************************************/
	 public void createDoc()
	 {
		 String sparepartNo = request.getParameter("sparepartNo").trim();
	     String imgPath = XMLUtil.CONFIG_MAP.get("spartdoccodepath") + sparepartNo + ".png";
	        
	     try
	     {
	    	 response.setContentType("text/html");
	         if (DeCode.encode(sparepartNo, imgPath, 200, 200))
	         {
	        	 response.getWriter().print(imgPath + ";" + sparepartNo);
	         }
	         else
	         {
	        	 response.getWriter().print("error");
	         }
	     }
	     catch (IOException e)
	     {
	    	 logger.info(e);
	     }
	 }
	 
	 public void instock()
	 {
		 try
	        {
	            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
	            
	            switch (instockService.instock(createInstock(request)))
	            {
	            case 1:
	                response.getWriter().print("保存成功");
	                logger.debug("[BWQ]---操作:添加备品,结果:成功,操作者:" + loginuser.getLoginAccount());
	                break;

	            case 0:
	                response.getWriter().print("备品编号重复");
	                logger.debug("[BWQ]---操作:添加备品,结果:编号重复,操作者:" + loginuser.getLoginAccount());
	                break;
	                
	            default:
	                response.getWriter().print("操作失败");
	                logger.debug("[BWQ]---操作:添加备品,结果:失败,操作者:" + loginuser.getLoginAccount());
	                break;
	            }
	        }
	        catch (Exception e)
	        {
	            logger.info(e);
	        }
	 }
	 
	 private Instock createInstock(HttpServletRequest req)
	 {
		 Instock instock = new Instock();
	        
		 instock.setSparepartNo(req.getParameter("sparepartNo"));
	     instock.setSparepartName(req.getParameter("sparepartName"));
	     instock.setSparepartModle(req.getParameter("sparepartModle"));
	     instock.setTypeId(Long.parseLong(req.getParameter("typeId")));
	     instock.setSparepartUnit(req.getParameter("sparepartUnit"));
	     instock.setSparepartSetplace(req.getParameter("sparepartSetplace"));
	     instock.setInstockSparepartReceiver(req.getParameter("instockSparepartReceiver"));
	     instock.setInstockSparepartProvider(req.getParameter("instockSparepartProvider"));
	     instock.setInstockSparepartSupervisor(req.getParameter("instockSparepartSupervisor"));
	     instock.setInstockTime(DateUtil.formatNewDate(DateUtil.YYYY_MM_DD_HH_MM_SS));
	     instock.setInstockAmount(Integer.parseInt(req.getParameter("instockAmount")));
	     instock.setInstockComment(req.getParameter("instockComment"));
	     
	     return instock;
	  }
}
