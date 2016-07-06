package com.moutum.equ.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.moutum.equ.domain.Outstock;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;


/********************************************************************************
 * @Title        : OutstockAction.java
 * @Description : 
 * @Author       : HuangWei
 * @DateTime     : 2015-8-9 下午18:20:09
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ********************************************************************************/

@Controller @Scope("prototype")
public class OutstockAction extends BaseAction<Outstock>
{
	private static final long serialVersionUID = -6782540958682166826L;

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	
	Logger logger = Logger.getLogger(OutstockAction.class);
	
	 public String list()
	 {
	     try
         {
	         int pageNum = Integer.parseInt(request.getParameter("page"));//当前页数
	         int pageSize = Integer.parseInt(request.getParameter("rows")); //当前页面显示行数
	            
	         String sparepartNo = request.getParameter("sparepartNo");
	         String instockId = request.getParameter("instockId");
	         String instockTimeStart = request.getParameter("instockTimeStart").trim();
	         String instockTimeEnd = request.getParameter("instockTimeEnd").trim();
	         Map<String,Object> map = new HashMap<String,Object>();
	            
	         if(null == sparepartNo || "".equals(sparepartNo.trim()))
	         {
	             sparepartNo = "";
	         }
	         map.put("sparepartNo", sparepartNo);
	         if(!"".equals(instockTimeStart.trim()))
	         {
	             map.put("instockTimeStart", DateUtil.stringToDate( instockTimeStart+" 00:00:00", DateUtil.YYYY_MM_DD_HH_MM_SS));
	         }
	         if(!"".equals(instockTimeEnd.trim()))
	         {
	             map.put("instockTimeEnd", DateUtil.stringToDate(instockTimeEnd+" 23:59:59", DateUtil.YYYY_MM_DD_HH_MM_SS));
	         }
	         if(!"".equals(instockId.trim()))
	         {
	             map.put("instockId", instockId);
	         }
	            
	         PageBean page = outstockService.getPage(pageNum, pageSize, map);
	            
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
	     return "open";
	 }
	 
	 public void outStock()
	    {
	        try
	        {
	        	User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
	            String[] spartInfos =  request.getParameter("spartInfos").split("/");
	            
	            for(String outstockinfos : spartInfos)
	            {
	            	Map<String , String> outstockmap = new HashMap<String , String>();
	            	String[] outstockeles = outstockinfos.split(";");
	            	
	            	outstockmap.put("InstockId", outstockeles[0]);
	            	
	            	outstockmap.put("SparepartNo", outstockeles[1]);
	            	outstockmap.put("SparepartName", outstockeles[2]);
	            	outstockmap.put("SparepartModle", outstockeles[3]);
	            	outstockmap.put("TypeName", outstockeles[4]);
	            	outstockmap.put("SparepartUnit", outstockeles[5]);
	            	outstockmap.put("OutstockSparepartReceiver", outstockeles[9]);
	            	outstockmap.put("OutstockSparepartProvider", outstockeles[10]);
	            	outstockmap.put("InstockTime", outstockeles[6]);
	            	outstockmap.put("OutstockTime", DateUtil.dateToString(DateUtil.formatNewDate(DateUtil.YYYY_MM_DD_HH_MM_SS), DateUtil.YYYY_MM_DD_HH_MM_SS));
	            	outstockmap.put("SparepartUseplace", outstockeles[11]);
	            	outstockmap.put("OutstockAmount", outstockeles[12]);
	            	outstockmap.put("Remainnum", outstockeles[7]);
	            	outstockmap.put("InstockComment", outstockeles[8]);
	            	outstockmap.put("OutStockComment", outstockeles[13]);
	            	
	            	List<Outstock> failList = outstockService.outstock(outstockmap);
	            	
	            	 if(failList.size() > 0)
	                 {
	                     StringBuffer nos = new StringBuffer("");
	                     for(Outstock s : failList)
	                     {
	                         nos.append(s.getSparepartNo() + "\t");
	                     }
	                     response.getWriter().print("以下物品出库失败:" + nos.toString());
	                     logger.debug("[BWQ]---操作:备品出库,结果:以下物品出库失败:" + nos.toString() + ",操作者:" + loginuser.getLoginAccount());
	                 }
	                 else
	                 {
	                     response.getWriter().print("出库成功");
	                     logger.debug("[BWQ]---操作:备品出库,结果:成功,操作者:" + loginuser.getLoginAccount());
	                 }
	            	
	            }
	        }
	        catch (Exception e)
	        {
	        }
	    }
	 
	 
}
