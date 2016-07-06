package com.moutum.equ.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.moutum.equ.domain.Modle;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.domain.Type;
import com.moutum.equ.domain.UseState;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.Tree;
import com.moutum.equ.util.SystemConstants;
import com.moutum.equ.util.XMLUtil;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title        : HomeAction.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月18日 上午9:02:19
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 * @param <ActionForward>
 ************************************************************************************/
@Controller @Scope("prototype")
public class HomeAction extends BaseAction<User>
{
    private static final long serialVersionUID = -6198065174380647135L;

    private User user;
    
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    Logger logger = Logger.getLogger(HomeAction.class);
    
    /********************************************************************************
     * 查询账号是否已经激活
     ********************************************************************************/
    public void checkUser()
    {
        try
        {
            User dbUser = homeService.getByLoginAccount(request.getParameter("loginAccount").trim());
            response.setContentType("text/html");
            if(null == dbUser)
            {
                response.getWriter().print("no-user");
            }
            else
            {
                if(dbUser.getIsActivated() == 0)
                {
                    response.getWriter().print("no-activated");
                }
                else
                {
                    response.getWriter().print("is-activated");
                }
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    /********************************************************************************
     * 账号激活
     ********************************************************************************/
    public void activate()
    {
        try
        {
            String account =  request.getParameter("account").trim();
            String loginPassword =  request.getParameter("loginPassword").trim();
            String userPhone =  request.getParameter("userPhone").trim();
            String email =  request.getParameter("email").trim();
            String userName =  request.getParameter("userName").trim();
            
            User dbUser = homeService.getByLoginAccount(account);
            if(null != dbUser)
            {
                dbUser.setEmail(email);
                dbUser.setIsActivated(1);
                dbUser.setLoginPassword(DigestUtils.md5Hex(DigestUtils.md5Hex(loginPassword) + "[" + account + "]"));
                dbUser.setUserPhone(userPhone);
                dbUser.setUserName(userName);
                switch (userService.modify(dbUser))
                {
                case 1:
                    ActionContext.getContext().getSession().put("login_user",dbUser);
                    Map<String, String> rightMap = rightService.getFunctionByRole(dbUser.getRoleId());
                    ActionContext.getContext().getSession().put("rightMap", rightMap);
                    logger.debug("[BWQ]---操作:登录系统,结果:成功,账号:" + dbUser.getLoginAccount());
                    
                    OperLog operLog = new OperLog();
                    operLog.setOperator(dbUser.getLoginAccount());
                    operLog.setOperLogTime(new Date());
                    operLog.setOperType(SystemConstants.OPERLOG_TYPE_LOGIN);
                    operLog.setOperLogContent("登录系统");
                    operLogService.save(operLog);
                    
                    response.getWriter().print("success");
                    logger.debug("[BWQ]---操作:账号激活,结果:成功,账号:" + account);
                    break;
                    
                case -1:
                    response.getWriter().print("error");
                    logger.debug("[BWQ]---操作:账号激活,结果:失败,账号:" + account);
                    break;
                default:
                    response.getWriter().print("no-user");
                    logger.debug("[BWQ]---操作:账号激活,结果:账号不存在,账号:" + account);
                    break;
                }
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    /********************************************************************************
     * 系统登陆
     * @return
     ********************************************************************************/
    public String login()
    {
        try
        {
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            ActionContext.getContext().getSession().remove("login_user");
            ActionContext.getContext().getSession().remove("rightMap");
            OperLog operLog = new OperLog();
            if(loginuser != null)
            {
            	operLog.setOperator(loginuser.getLoginAccount());
            	operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_LOGOUT);
                operLog.setOperLogContent("退出系统");
                operLogService.save(operLog);
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
        
        if(null == user || null == user.getLoginAccount())
        {
            return "login";
        }
        
        User dbUser = homeService.getByLoginAccount(user.getLoginAccount());
        
        if(null == dbUser)
        {
            return "login";
        }
        else
        {
            if(dbUser.getLoginPassword().equals(DigestUtils.md5Hex(DigestUtils.md5Hex(user.getLoginPassword()) + "[" + user.getLoginAccount() + "]")))
            {
                if(dbUser.getIsActivated() == 1)
                {
                    ActionContext.getContext().getSession().put("login_user",dbUser);
                    Map<String, String> rightMap = rightService.getFunctionByRole(dbUser.getRoleId());
                    ActionContext.getContext().getSession().put("rightMap", rightMap);
                    logger.debug("[BWQ]---操作:登录系统,结果:成功,账号:" + dbUser.getLoginAccount());
                    
                    Map<String, List<?>> map = equipmentService.getSelectLists();
                    for(String s : map.keySet())
                    {
                        request.setAttribute(s, map.get(s));
                    }
                    request.setAttribute("codepath", XMLUtil.CONFIG_MAP.get("equdoccodepath"));
                    
                    OperLog operLog = new OperLog();
                    operLog.setOperator(dbUser.getLoginAccount());
                    operLog.setOperLogTime(new Date());
                    operLog.setOperType(SystemConstants.OPERLOG_TYPE_LOGIN);
                    operLog.setOperLogContent("登录系统");
                    operLogService.save(operLog);
                    return "success";
                }
            }
        }
        return "login";
    }
    
    public void loadType()
    {
        try
        {
            StringBuffer typeNames = new StringBuffer("");
            List<Type> types = typeService.findAll();
            for(int i = 0; i < types.size(); i++)
            {
                if(i == types.size() - 1)
                {
                    typeNames.append(types.get(i).getTypeName());
                }
                else
                {
                    typeNames.append(types.get(i).getTypeName() + ";");
                }
            }
            response.getWriter().print(typeNames);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    public void loadState()
    {
        try
        {
            StringBuffer stateNames = new StringBuffer("");
            List<UseState> useStates = useStateService.findAll();
            for(int i = 0; i < useStates.size(); i++)
            {
                if(i == useStates.size() - 1)
                {
                    stateNames.append(useStates.get(i).getUseStateName());
                }
                else
                {
                    stateNames.append(useStates.get(i).getUseStateName() + ";");
                }
            }
            response.getWriter().print(stateNames);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    public String index()
    {
//        Map<String, List<?>> map = equipmentService.getSelectLists();
//        for(String s : map.keySet())
//        {
//            request.setAttribute(s, map.get(s));
//        }
        request.setAttribute("codepath", XMLUtil.CONFIG_MAP.get("equdoccodepath"));
        return "success";
    }
    
    public String menu()
    {
        List<Tree> trees = homeService.getModles((User)ActionContext.getContext().getSession().get("login_user"));
        StringBuffer json = new StringBuffer("[");
        for(int i = 0; i < trees.size(); i++)
        {
            Tree t = trees.get(i);
            json.append("{id:\"cf" + (i + 1) + "\",");
            json.append("text:\"" + t.getFirstName() + "\",");
            json.append("children:[");
            for(int j = 0; j < t.getModles().size(); j++)
            {
                Modle m = t.getModles().get(j);
                json.append("{id:\"cc" + (i + 1) + "" + (j + 1) + "\",");
                json.append("text:\"" + m.getName() + "\",");
                json.append("icon:\"f10\",");
                json.append("url:\"" + m.getUrl().substring(1) + "\"");
                if(j < t.getModles().size() - 1)
                {
                    json.append("},");
                }
                else
                {
                    json.append("}]");
                }
            }
            if(i < trees.size() - 1)
            {
                json.append("},");
            }
            else
            {
                json.append("}]");
            }
        }
        request.getSession().setAttribute("tree", json.toString());
        return "menu";
    }
        
    /********************************************************************************
     * 修改密码
     ********************************************************************************/
    public void modifyPass()
    {
        try
        {
            String oldPassword =  request.getParameter("oldPassword").trim();
            String newPassword =  request.getParameter("newPassword").trim();
            User dbUser = (User)ActionContext.getContext().getSession().get("login_user");
            oldPassword = DigestUtils.md5Hex(DigestUtils.md5Hex(oldPassword) + "[" + dbUser.getLoginAccount() + "]");
            newPassword = DigestUtils.md5Hex(DigestUtils.md5Hex(newPassword) + "[" + dbUser.getLoginAccount() + "]");
            if(oldPassword.equals(dbUser.getLoginPassword()))
            {
                dbUser.setLoginPassword(newPassword);
                switch (userService.modify(dbUser))
                {
                case 1:
                    ActionContext.getContext().getSession().remove("login_user");
                    ActionContext.getContext().getSession().remove("rightMap");
                    response.getWriter().print("success");
                    logger.debug("[BWQ]---操作:修改密码,结果:成功,账号:" + dbUser.getLoginAccount());
                    break;
                    
                case -1:
                    response.getWriter().print("error");
                    logger.debug("[BWQ]---操作:修改密码,结果:失败,账号:" + dbUser.getLoginAccount());
                    break;
                default:
                    response.getWriter().print("no-user");
                    logger.debug("[BWQ]---操作:修改密码,结果:账号不存在,账号:" + dbUser.getLoginAccount());
                    break;
                }
            }
            else
            {
                response.getWriter().print("pass-error");
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    public String openWin()
    {
        return "open";
    }
    
    public String main()
    {
        return "main";
    }
    
    public User getUser()
    {
        return user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }
}