package com.moutum.equ.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.moutum.equ.util.XMLUtil;

/************************************************************************************
 * @Title        : ServletCListener.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年7月22日 下午1:46:37
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class ServletCListener implements ServletContextListener
{

    @Override
    public void contextDestroyed(ServletContextEvent event)
    {

    }

    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        //系统变量初始化
        XMLUtil.parserXml(event.getServletContext().getRealPath("/") + "/WEB-INF/classes/config.xml");
    }
}