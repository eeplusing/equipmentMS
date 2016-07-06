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

import com.moutum.equ.domain.DatumFormat;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.opensymphony.xwork2.ActionContext;

/**
 * @Title        : DatumFormatAction.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015-3-28 下午04:58:52
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 */
@Controller @Scope("prototype")
public class DatumFormatAction extends BaseAction<DatumFormat>
{

    private static final long serialVersionUID = 912117585759633243L;

    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    Logger logger = Logger.getLogger(DatumFormatAction.class);
    
    /********************************************************************************
     * 进入文档格式查看页面
     * @return
     ********************************************************************************/
    public String manage()
    {
        return "manage";
    }
    
    /********************************************************************************
     * 查看，浏览文档格式
     * @return
     ********************************************************************************/
    public String list()
    {
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("page"));
        
        PageBean page = datumFormatService.getPage(pageNum, pageSize);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        //设置总数据量
        jsonMap.put("total", page.getRecordCount()); 
        //当前显示数据
        jsonMap.put("rows", page.getRecordList());
        
        result = JSONObject.fromObject(jsonMap);
        return "list";
    }
    
    /********************************************************************************
     * 保存文档格式
     ********************************************************************************/
    public void save()
    {
        try
        {
            DatumFormat datumFormat = new DatumFormat();
            datumFormat.setDatumFormatName(request.getParameter("datumFormatName"));
            datumFormat.setRemark(request.getParameter("remark"));
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (datumFormatService.save(datumFormat))
            {
            case -1:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:保存文档格式,结果:操作失败,操作者:" + loginuser.getLoginAccount());
                break;
                
            case 0:
                response.getWriter().print("该文档格式已经存在");
                logger.debug("[BWQ]---操作:保存文档格式,结果:文档格式已经存在,操作者:" + loginuser.getLoginAccount());
                break;
                
            default:
                response.getWriter().print("添加成功");
                logger.debug("[BWQ]---操作:保存文档格式:" + datumFormat.getDatumFormatName() + ",结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    /********************************************************************************
     * 修改文档格式
     ********************************************************************************/
    public void modify()
    {
        try
        {
            DatumFormat datumFormat = new DatumFormat();
            datumFormat.setDatumFormatId(Integer.parseInt(request.getParameter("datumFormatId")));
            datumFormat.setDatumFormatName(request.getParameter("datumFormatName"));
            datumFormat.setRemark(request.getParameter("remark"));
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (datumFormatService.modify(datumFormat))
            {
            case 1:
                response.getWriter().print("编辑成功");
                logger.debug("[BWQ]---操作:修改文档格式:" + datumFormat.getDatumFormatName() + ",结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
                
            case 0:
                response.getWriter().print("该文档格式不存在");
                logger.debug("[BWQ]---操作:修改文档格式,结果:文档格式已经不存在,操作者:" + loginuser.getLoginAccount());
                break;
                
            default:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:修改文档格式,结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    /********************************************************************************
     * 删除文档格式
     ********************************************************************************/
    public void delete()
    {
        try
        {
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (datumFormatService.delete(Integer.parseInt(request.getParameter("id"))))
            {
            case 1:
                response.getWriter().print("删除成功");
                logger.debug("[BWQ]---操作:删除文档格式,结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
                
            case 0:
                response.getWriter().print("该文档格式不存在");
                logger.debug("[BWQ]---操作:删除文档格式,结果:文档格式不存在,操作者:" + loginuser.getLoginAccount());
                break;
                
            default:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:删除文档格式,结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
}