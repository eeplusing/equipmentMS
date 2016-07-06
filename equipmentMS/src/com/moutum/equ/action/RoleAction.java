package com.moutum.equ.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.moutum.equ.dto.PageBean;
import com.moutum.equ.domain.Role;
import com.moutum.equ.domain.User;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title        : RoleAction.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 下午4:44:32
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Controller @Scope("prototype")
public class RoleAction extends BaseAction<Role>
{

    private static final long serialVersionUID = 2593655301651594965L;
    
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    Logger logger = Logger.getLogger(RoleAction.class);
    
    public String manage()
    {
        return SUCCESS;
    }
    
    public String list()
    {
        int pageSize = Integer.parseInt(request.getParameter("rows")); 
        int pageNum = Integer.parseInt(request.getParameter("page"));
        
        PageBean page = roleService.getPage(pageNum, pageSize);
        
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        //设置总数据量
        jsonMap.put("total", page.getRecordCount()); 
        //当前显示数据
        jsonMap.put("rows", page.getRecordList());
        
        result = JSONObject.fromObject(jsonMap);
        
        return "list";
    }

    public void delete()
    {
        long roleId = Long.parseLong(request.getParameter("roleId"));
        try
        {
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (roleService.delete(roleId))
            {
            case 1:
                response.getWriter().print("删除成功");
                logger.debug("[BWQ]---操作:删除角色" + roleId + ",结果:成功,操作者:" + loginuser.getLoginAccount());
                break;

            case -1:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:删除角色" + roleId + ",结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            default:
                response.getWriter().print("角色不存在");
                logger.debug("[BWQ]---操作:删除角色" + roleId + ",结果:角色不存在,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    public void save()
    {
        try
        {
            Role role = new Role();
            role.setName(request.getParameter("name"));
            role.setDescribe(request.getParameter("describe"));
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (roleService.save(role))
            {
            case 1:
                response.getWriter().print("添加成功");
                logger.debug("[BWQ]---操作:添加角色" + role.getRoleId() + "|" + role.getName() + ",结果:成功,操作者:" + loginuser.getLoginAccount());
                break;

            case -1:
                response.getWriter().print("角色名被占用");
                logger.debug("[BWQ]---操作:添加角色" + role.getRoleId() + "|" + role.getName() + ",结果:角色已存在,操作者:" + loginuser.getLoginAccount());
                break;
            default:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:添加角色" + role.getRoleId() + "|" + role.getName() + ",结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (IOException e)
        {
            logger.info(e);
        }
    }
    
    public void modify()
    {
        try
        {
            Role role = new Role();
            role.setRoleId(Long.parseLong(request.getParameter("roleId").trim()));
            role.setName(request.getParameter("name").trim());
            role.setDescribe(request.getParameter("describe").trim());
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (roleService.modify(role))
            {
            case 1:
                response.getWriter().print("编辑成功");
                logger.debug("[BWQ]---操作:编辑角色信息" + role.getRoleId() + "|" + role.getName() + ",结果:成功,操作者:" + loginuser.getLoginAccount());
                break;

            case -1:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:编辑角色信息" + role.getRoleId() + "|" + role.getName() + ",结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            default:
                response.getWriter().print("角色不存在");
                logger.debug("[BWQ]---操作:编辑角色信息" + role.getRoleId() + "|" + role.getName() + ",结果:角色不存在,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
    }
}