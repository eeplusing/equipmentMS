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

import com.moutum.equ.domain.UseState;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title        : UseStateAction.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月25日 上午11:30:19
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Controller @Scope("prototype")
public class UseStateAction extends BaseAction<UseState>
{

    private static final long serialVersionUID = 2914831178458692239L;
    
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    Logger logger = Logger.getLogger(UseStateAction.class);
    
    public String manage()
    {
        return "manage";
    }
    
    public String list()
    {
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("page"));
        
        PageBean page = useStateService.getPage(pageNum, pageSize);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        //设置总数据量
        jsonMap.put("total", page.getRecordCount()); 
        //当前显示数据
        jsonMap.put("rows", page.getRecordList());
        
        result = JSONObject.fromObject(jsonMap);
        return "list";
    }
    
    public void save()
    {
        try
        {
            UseState useState = new UseState();
            useState.setUseStateName(request.getParameter("useStateName"));
            useState.setUseStateRemark(request.getParameter("useStateRemark"));
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (useStateService.save(useState))
            {
            case -1:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:添加设备使用状态,结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
                
            case 0:
                response.getWriter().print("该使用状态已经存在");
                logger.debug("[BWQ]---操作:添加设备使用状态,结果:状态已经存在,操作者:" + loginuser.getLoginAccount());
                break;
                
            default:
                response.getWriter().print("添加成功");
                logger.debug("[BWQ]---操作:添加设备使用状态,结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    public void modify()
    {
        try
        {
            UseState useState = new UseState();
            useState.setUseStateId(Integer.parseInt(request.getParameter("useStateId")));
            useState.setUseStateName(request.getParameter("useStateName"));
            useState.setUseStateRemark(request.getParameter("useStateRemark"));
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (useStateService.modify(useState))
            {
            case 1:
                response.getWriter().print("编辑成功");
                logger.debug("[BWQ]---操作:修改设备使用状态信息,结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
                
            case 0:
                response.getWriter().print("该使用状态不存在");
                logger.debug("[BWQ]---操作:修改设备使用状态信息,结果:使用状态不存在,操作者:" + loginuser.getLoginAccount());
                break;
                
            default:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:修改设备使用状态信息,结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    public void delete()
    {
        try
        {
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (useStateService.delete(Integer.parseInt(request.getParameter("id"))))
            {
            case 1:
                response.getWriter().print("删除成功");
                logger.debug("[BWQ]---操作:删除设备使用状态,结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
                
            case 0:
                response.getWriter().print("该使用状态不存在");
                logger.debug("[BWQ]---操作:删除设备使用状态,结果:状态不存在,操作者:" + loginuser.getLoginAccount());
                break;
                
            default:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:删除设备使用状态,结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
}