package com.moutum.equ.interceptot;

import java.util.Map;

import com.moutum.equ.domain.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/************************************************************************************
 * @Title        : MyInterceptot.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月6日 下午4:51:42
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class MyInterceptot extends AbstractInterceptor
{
    
    private static final long serialVersionUID = 6645338921893814285L;
    @SuppressWarnings("unchecked")
    @Override
    public String intercept(ActionInvocation invocation) throws Exception
    {
        
        ActionContext actionContext = invocation.getInvocationContext();
//        ServletContext context = (ServletContext) actionContext.get(StrutsStatics.SERVLET_CONTEXT);
//        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
//        UserService userService = (UserService)ctx.getBean("userServiceImpl");
        
        String functionName = actionContext.getName();//获取访问的action及方法名
        Map<String, Object> session = actionContext.getSession();
        User user = (User) session.get("login_user");
        Map<String, String> rightMap = (Map<String, String>)session.get("rightMap");
        if(null != user)
        {
            if(null != rightMap.get(functionName) || "admin".equals(user.getLoginAccount()))
            {
                return invocation.invoke();
            }
            else
            {
                return "no_right";
            }
        }
        else
        {
            return Action.LOGIN;
        }
    }
}