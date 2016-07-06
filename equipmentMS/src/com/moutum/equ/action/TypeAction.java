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

import com.moutum.equ.domain.Type;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title        : TypeAction.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月25日 上午11:07:02
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Controller @Scope("prototype")
public class TypeAction extends BaseAction<Type>
{

    private static final long serialVersionUID = -1625745458146150253L;
    
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    Logger logger = Logger.getLogger(TypeAction.class);
    
    public String manage()
    {
        List<Type> types = typeService.findAll();
        request.setAttribute("types", types);
        return "manage";
    }
    
    
    public String list()
    {
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("page"));
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        try
        {
            String name = request.getParameter("typeName");
            if(name != null)
            {
                map.put("typeName", "%" + name + "%");
            }
            
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
        try
        {
            int parentId = Integer.parseInt(request.getParameter("parentId"));
            if(parentId >= 0)
            {
                map.put("parentId", (long)parentId);
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
        PageBean page = typeService.getPage(pageNum, pageSize, map);
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
            Type type = new Type();
            type.setTypeName(request.getParameter("typeName"));
            int parentId = Integer.parseInt(request.getParameter("parentId"));
            type.setParentId(parentId > 0 ? parentId : 0);
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            int result = typeService.save(type);
            
            switch (result)
            {
            case -1:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:添加类型,结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
                
            case 0:
                response.getWriter().print("该类型已经存在");
                logger.debug("[BWQ]---操作:添加类型,结果:类型已经存在,操作者:" + loginuser.getLoginAccount());
                break;
                
            default:
                response.getWriter().print("添加成功;" + result);
                logger.debug("[BWQ]---操作:添加类型,结果:成功,操作者:" + loginuser.getLoginAccount());
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
            Type type = new Type();
            type.setTypeId(Integer.parseInt(request.getParameter("typeId")));
            type.setTypeName(request.getParameter("typeName"));
            type.setParentId(Integer.parseInt(request.getParameter("parentId")));
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (typeService.modify(type))
            {
            case 1:
                response.getWriter().print("编辑成功");
                logger.debug("[BWQ]---操作:修改类型信息,结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
                
            case 0:
                response.getWriter().print("该类型不存在");
                logger.debug("[BWQ]---操作:修改类型信息,结果:类型不存在,操作者:" + loginuser.getLoginAccount());
                break;
                
            default:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:修改类型信息,结果:失败,操作者:" + loginuser.getLoginAccount());
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
            
            switch (typeService.delete(Integer.parseInt(request.getParameter("id"))))
            {
            case 1:
                response.getWriter().print("删除成功");
                logger.debug("[BWQ]---操作:删除类型,结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
                
            case 0:
                response.getWriter().print("该类型不存在");
                logger.debug("[BWQ]---操作:删除类型,结果:类型不存在,操作者:" + loginuser.getLoginAccount());
                break;
                
            default:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:删除类型,结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
}