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

import com.moutum.equ.domain.Right;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title        : RightAction.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月20日 上午9:43:43
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Controller @Scope("prototype")
public class RightAction extends BaseAction<Right>
{

    private static final long serialVersionUID = -6303609052908055229L;
    
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    Logger logger = Logger.getLogger(RightAction.class);
    
    public String manage()
    {
        return "manage";
    }
    
    public String list()
    {
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("page"));
        
        PageBean page = rightService.getPage(pageNum, pageSize);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        //设置总数据量
        jsonMap.put("total", page.getRecordCount()); 
        //当前显示数据
        jsonMap.put("rows", page.getRecordList());
        
        result = JSONObject.fromObject(jsonMap);
        return "list";
    }
    
    public String getRightTree()
    {
        long roleId = Long.parseLong(request.getParameter("roleId"));
        request.setAttribute("rightMap", rightService.rightMap());
        request.setAttribute("roleId", roleId);
        request.setAttribute("hasRight", rightService.getRightIdByRole(roleId));
        return "tree";
    }
    
    public void save()
    {
        try
        {
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            String rightIds = request.getParameter("rightIds").trim();
            long roleId = Long.parseLong(request.getParameter("roleId").trim());
            
            if (null != rightIds && rightIds.length() > 0)
            {
                String[] rids = rightIds.split(";");
                switch (rightService.giveRight(rids, roleId))
                {
                case 1:
                    response.getWriter().print("授权成功");
                    logger.debug("[BWQ]---操作:授权,结果:成功,操作者:" + loginuser.getLoginAccount() + "角色:" + roleId);
                    break;

                case 0:
                    response.getWriter().print("操作失败");
                    logger.debug("[BWQ]---操作:授权,结果:失败,操作者:" + loginuser.getLoginAccount() + "角色:" + roleId);
                    break;
                }
            }
        }
        catch (Exception e)
        {
            logger.info(e);
            try
            {
                response.getWriter().print("系统发生错误");
            }
            catch (IOException e1)
            {
                logger.info(e1);
            }
        }
    }
}