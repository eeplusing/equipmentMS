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

import com.moutum.equ.domain.DatumType;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.opensymphony.xwork2.ActionContext;

/**
 * @Title        : DatumTypeAction.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015-3-28 下午04:25:21
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 */
@Controller @Scope("prototype")
public class DatumTypeAction extends BaseAction<DatumType>
{
    
    private static final long serialVersionUID = -8966979025546783866L;
    
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    Logger logger = Logger.getLogger(DatumTypeAction.class);
    
    /********************************************************************************
     * 进入文档类型查看页面
     * @return
     ********************************************************************************/
    public String manage()
    {
        return "manage";
    }
    
    /********************************************************************************
     * 查看，浏览文档类型
     * @return
     ********************************************************************************/
    public String list()
    {
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("page"));
        
        PageBean page = datumTypeService.getPage(pageNum, pageSize);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        //设置总数据量
        jsonMap.put("total", page.getRecordCount()); 
        //当前显示数据
        jsonMap.put("rows", page.getRecordList());
        
        result = JSONObject.fromObject(jsonMap);
        return "list";
    }
    
    /********************************************************************************
     * 保存文档类型
     ********************************************************************************/
    public void save()
    {
        try
        {
            DatumType datumType = new DatumType();
            datumType.setDatumTypeName(request.getParameter("datumTypeName"));
            datumType.setRemark(request.getParameter("remark"));
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (datumTypeService.save(datumType))
            {
            case -1:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:保存文档类型,结果:操作失败,操作者:" + loginuser.getLoginAccount());
                break;
                
            case 0:
                response.getWriter().print("该文档类型已经存在");
                logger.debug("[BWQ]---操作:保存文档类型,结果:文档类型已经存在,操作者:" + loginuser.getLoginAccount());
                break;
                
            default:
                response.getWriter().print("添加成功");
                logger.debug("[BWQ]---操作:保存文档类型:" + datumType.getDatumTypeId() + "|" + datumType.getDatumTypeName() + ",结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    /********************************************************************************
     * 修改文档类型
     ********************************************************************************/
    public void modify()
    {
        try
        {
            DatumType datumType = new DatumType();
            datumType.setDatumTypeId(Integer.parseInt(request.getParameter("datumTypeId")));
            datumType.setDatumTypeName(request.getParameter("datumTypeName"));
            datumType.setRemark(request.getParameter("remark"));
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (datumTypeService.modify(datumType))
            {
            case 1:
                response.getWriter().print("编辑成功");
                logger.debug("[BWQ]---操作:修改文档类型:" + datumType.getDatumTypeId() + "|" + datumType.getDatumTypeName() + ",结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
                
            case 0:
                response.getWriter().print("该文档类型不存在");
                logger.debug("[BWQ]---操作:保存文档类型,结果:文档类型不存在,操作者:" + loginuser.getLoginAccount());
                break;
                
            default:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:保存文档类型,结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    /********************************************************************************
     * 删除文档类型
     ********************************************************************************/
    public void delete()
    {
        try
        {
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (datumTypeService.delete(Integer.parseInt(request.getParameter("id"))))
            {
            case 1:
                response.getWriter().print("删除成功");
                logger.debug("[BWQ]---操作:删除文档类型,结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
                
            case 0:
                response.getWriter().print("该文档类型不存在");
                logger.debug("[BWQ]---操作:删除文档类型,结果:文档类型不存在,操作者:" + loginuser.getLoginAccount());
                break;
                
            default:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:删除文档类型,结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
}