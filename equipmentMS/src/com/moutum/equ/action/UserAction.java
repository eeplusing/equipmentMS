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

import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title        : UserAction.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月6日 下午4:15:38
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/

@Controller @Scope("prototype")
public class UserAction extends BaseAction<User>
{
    private static final long serialVersionUID = 1075081771422315363L;
    
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    Logger logger = Logger.getLogger(UserAction.class);
    
    public String manage()
    {
        request.setAttribute("roles",roleService.select());
        return SUCCESS;
    }
    
    /********************************************************************************
     * 获取用户列表
     * @return
     ********************************************************************************/
    public String list()
    {
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("page"));
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        try
        {
            String account = request.getParameter("account").trim();
            if(account != null && account.length() > 0)
            {
                map.put("loginAccount", "%" + account + "%");
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
        try
        {
            String name = request.getParameter("name").trim();
            if(name != null && name.length() > 0)
            {
                map.put("userName", "%" + name + "%");
            }
            
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
        try
        {
            long roleId = Long.parseLong(request.getParameter("roleId").trim());
            if(roleId > 0)
            {
                map.put("roleId", roleId);
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
        PageBean page = userService.getPage(pageNum, pageSize, map);
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
            User user = new User();
            user.setLoginAccount(request.getParameter("loginAccount").trim());
            user.setIsActivated(0);
            user.setRoleId(Long.parseLong(request.getParameter("roleId")));
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (userService.save(user))
            {
            case 1:
                response.getWriter().print("添加成功");
                logger.debug("[BWQ]---操作:添加账号[" + user.getLoginAccount() + "],结果:成功,操作者:" + loginuser.getLoginAccount());
                break;

            case -1:
                response.getWriter().print("账号被占用");
                logger.debug("[BWQ]---操作:添加账号[" + user.getLoginAccount() + "],结果:账号已存在,操作者:" + loginuser.getLoginAccount());
                break;
            default:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:添加账号[" + user.getLoginAccount() + "],结果:失败,操作者:" + loginuser.getLoginAccount());
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
            User user = new User();
            user.setUserId(Integer.parseInt(request.getParameter("userId")));
            user.setLoginAccount(request.getParameter("loginAccount"));
            user.setEmail(request.getParameter("email"));
            user.setIsActivated(1);
            user.setRoleId(Integer.parseInt(request.getParameter("roleId")));
            user.setUserName(request.getParameter("userName"));
            user.setUserPhone(request.getParameter("userPhone"));
            user.setLoginPassword(request.getParameter("loginPassword"));//初始化用户密码
            
            switch (userService.modify(user))
            {
            case 1:
                response.getWriter().print("编辑成功");
                break;

            case -1:
                response.getWriter().print("操作失败");
                break;
            default:
                response.getWriter().print("账号不存在");
                break;
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
    }
    
    public void modifyRole()
    {
        try
        {
            long userId = Long.parseLong(request.getParameter("userId"));
            long roleId = Long.parseLong(request.getParameter("roleId"));
            User user = userService.selectById(userId);
            user.setRoleId(roleId);
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (userService.modify(user))
            {
            case 1:
                response.getWriter().print("分配成功");
                logger.debug("[BWQ]---操作:分配角色[" + user.getLoginAccount() + ":" + roleId + "],结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
                
            case -1:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:分配角色[" + user.getLoginAccount() + ":" + roleId + "],结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            default:
                response.getWriter().print("账号不存在");
                logger.debug("[BWQ]---操作:分配角色[" + user.getLoginAccount() + ":" + roleId + "],结果:账号不存在,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
    }
    
    public void delete()
    {
        String loginAccount = request.getParameter("loginAccount");
        try
        {
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (userService.deleteUser(loginAccount))
            {
            case 1:
                response.getWriter().print("删除成功");
                logger.debug("[BWQ]---操作:删除账号[" + loginAccount + "],结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
    
            case -1:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:删除账号[" + loginAccount + "],结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            default:
                response.getWriter().print("账号不存在");
                logger.debug("[BWQ]---操作:删除账号[" + loginAccount + "],结果:账号不存在,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
    }
}