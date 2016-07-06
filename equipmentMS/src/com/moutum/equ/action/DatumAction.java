package com.moutum.equ.action;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.moutum.equ.domain.Datum;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.util.DateUtil;
import com.moutum.equ.util.EncodeConvertor;
import com.moutum.equ.util.SystemConstants;
import com.moutum.equ.util.UTF8;
import com.moutum.equ.util.XMLUtil;
import com.opensymphony.xwork2.ActionContext;

/********************************************************************************
 * @Title        : DatumAction.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015-3-28 下午10:12:29
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ********************************************************************************/
@Controller @Scope("prototype")
public class DatumAction extends BaseAction<Datum>
{

    private static final long serialVersionUID = -1443574235461554481L;
    
    private File datumFile;
    
    private String datumFileFileName;
    
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    Logger logger = Logger.getLogger(DatumAction.class);
    
    /********************************************************************************
     * 进入文档信息查看页面
     * @return
     ********************************************************************************/
    public String manage()
    {
        Map<String, List<?>> map = datumService.getSelectLists();
        for(String s : map.keySet())
        {
            request.setAttribute(s, map.get(s));
        }
        return "manage";
    }
    
    /********************************************************************************
     * 查看，查询文档信息
     * @return
     ********************************************************************************/
    public String list()
    {
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("page"));
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        try
        {
            int datumTypeId = Integer.parseInt(request.getParameter("datumTypeId"));
            if(datumTypeId > 0)
            {
                map.put("datumTypeId", (long)datumTypeId);
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        try
        {
            int datumFormatId = Integer.parseInt(request.getParameter("datumFormatId"));
            if(datumFormatId > 0)
            {
                map.put("datumFormatId", (long)datumFormatId);
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        try
        {
            String datumName = request.getParameter("datumName");
            if(null != datumName && !"".equals(datumName.trim()))
            {
                map.put("datumName", "%" + datumName + "%");
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
        try
        {
            long equipmentId = Long.parseLong(request.getParameter("equipmentId"));
            if(equipmentId > 0)
            {
                map.put("equipmentId", equipmentId);
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
        String requestType = request.getParameter("requestType");
        
        PageBean page = datumService.getPage(pageNum, pageSize, map, requestType);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        //设置总数据量
        jsonMap.put("total", page.getRecordCount()); 
        //当前显示数据
        jsonMap.put("rows", page.getRecordList());
        
        result = JSONObject.fromObject(jsonMap);
        return "list";
    }
    /********************************************************************************
     * 根据设备ID查询设备关联文档信息
     * @return
     ********************************************************************************/
    public String equList()
    {
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("page"));
        
        long equipmentId = 0;
        try
        {
            equipmentId = Long.parseLong(request.getParameter("equipmentId").trim());
        }
        catch (Exception e)
        {
            logger.info(e);
        }
        
        PageBean page = datumService.getPage(pageNum, pageSize, equipmentId);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        //设置总数据量
        jsonMap.put("total", page.getRecordCount()); 
        //当前显示数据
        jsonMap.put("rows", page.getRecordList());
        
        result = JSONObject.fromObject(jsonMap);
        return "list";
    }
    
    /********************************************************************************
     * 保存文档信息
     ********************************************************************************/
    public void save()
    {
        try
        {
            String equIds = request.getParameter("equIds").trim();
            String[] es = null;
            if(equIds.length() > 0)
            {
                es = equIds.split(";");
            }
            
            Datum datum = new Datum();
            datum.setDatumFormatId(Long.parseLong(request.getParameter("datumFormatId")));
            datum.setDatumTypeId(Long.parseLong(request.getParameter("datumTypeId")));
            datum.setDatumPath(request.getParameter("datumPath"));
            datum.setDatumName(request.getParameter("datumName"));
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (datumService.save(datum, es))
            {
            case -1:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:保存文档信息,结果:操作失败,操作者:" + loginuser.getLoginAccount());
                break;
            case 0:
                response.getWriter().print("文档已经存在");
                logger.debug("[BWQ]---操作:保存文档信息,结果:操作失败文档已存在,操作者:" + loginuser.getLoginAccount());
                break;    
            default:
                response.getWriter().print("上传成功");
                logger.debug("[BWQ]---操作:保存文档信息,结果:操作成功,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    /********************************************************************************
     * 文档上传
     ********************************************************************************/
    public void load()
    {
        if(null != datumFile && datumFile.length() > 0)
        {
            File savepath = new File(XMLUtil.CONFIG_MAP.get("datumpath") + DateUtil.dateToString(new Date(), DateUtil.YYYY_MM_DD));
            if(!savepath.exists())
            {
                savepath.mkdirs();
            }
            File saveFile = new File(savepath,datumFileFileName);
            try
            {
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                String path = savepath + "/" + datumFileFileName;
                if(datumService.select(null, path))
                {
                    response.getWriter().print("ishave");
                    logger.debug("[BWQ]---操作:文档上传,结果:操作失败文档已存在,操作者:" + loginuser.getLoginAccount());
                }
                else//如果文档不存在则上传
                {
                    FileUtils.copyFile(datumFile,saveFile);
                    response.setContentType("text/html"); 
                    response.setCharacterEncoding("GBK");
                    response.getWriter().print(path.replace("\\", "/"));
                    logger.debug("[BWQ]---操作:文档上传,结果:操作成功,操作者:" + loginuser.getLoginAccount());
                }
            }
            catch (IOException e)
            {
                logger.info(e);
                try
                {
                    response.getWriter().print("error");
                }
                catch (IOException e1)
                {
                    logger.info(e1);
                }
            }
        }
        else
        {
            try
            {
                response.getWriter().print("empty");
            }
            catch (IOException e1)
            {
                logger.info(e1);
            }
        }
    }
    
    /********************************************************************************
     * 编辑文档(暂未用)
     ********************************************************************************/
    public void modify()
    {
        try
        {
            Datum datum = new Datum();
            datum.setDatumFormatId(Long.parseLong(request.getParameter("datumFormatId")));
            datum.setDatumTypeId(Long.parseLong(request.getParameter("datumTypeId")));
            datum.setDatumPath(request.getParameter("datumPath"));
            datum.setDatumName(request.getParameter("datumName"));
            
            switch (datumService.modify(datum))
            {
            case 1:
                response.getWriter().print("编辑成功");
                break;
                
            case 0:
                response.getWriter().print("该文档不存在");
                break;
                
            default:
                response.getWriter().print("操作失败");
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    /********************************************************************************
     * 删除文档
     ********************************************************************************/
    public void delete()
    {
        try
        {
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (datumService.delete(Integer.parseInt(request.getParameter("id"))))
            {
            case 1:
                response.getWriter().print("删除成功");
                logger.debug("[BWQ]---操作:删除文档,结果:删除成功,操作者:" + loginuser.getLoginAccount());
                break;
                
            case 0:
                response.getWriter().print("该文档不存在");
                logger.debug("[BWQ]---操作:删除文档,结果:文档不存在,删除失败,操作者:" + loginuser.getLoginAccount());
                break;
                
            default:
                response.getWriter().print("操作失败");
                logger.debug("[BWQ]---操作:删除文档,结果:操作失败,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    /********************************************************************************
     * 开启文档上传窗口
     * @return
     ********************************************************************************/
    public String uploadWindow()
    {
        Map<String, List<?>> map = datumService.getSelectLists();
        for(String s : map.keySet())
        {
            request.setAttribute(s, map.get(s));
        }
        return "open";
    }
    
    /********************************************************************************
     * 下载文档
     ********************************************************************************/
    public void download()
    {
        FileInputStream in = null;
        OutputStream os = null;
        
        
        try
        {
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            long datumId = Long.parseLong(request.getParameter("datumId"));
            Datum datum = datumService.select(datumId);
            String filePath = datum.getDatumPath();
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            Cookie cookie = new Cookie("fileDownload", "true");
            response.addCookie(cookie);
            response.addHeader("Content-Disposition","attachment;filename=" + EncodeConvertor.encodeConvert(fileName, "gb2312", "iso8859-1"));
            in = new FileInputStream(filePath);
            os = response.getOutputStream();
            byte[] b = new byte[1024 * 1024 * 4];
            int count = in.read(b);
            while(count > 0)
            {
                os.write(b, 0, count);
                count = in.read(b);
            }
            
            OperLog operLog = new OperLog();
            operLog.setOperator(loginuser.getLoginAccount());
            operLog.setOperLogTime(new Date());
            operLog.setOperType(SystemConstants.OPERLOG_TYPE_DATUM_UPLOAD);
            operLog.setOperLogContent("下载文档：" + datum.getDatumPath());
            operLogService.save(operLog);
        }
        catch (Exception e)
        {
            logger.info(e);
        }
        finally
        {
            try
            {
                in.close();
                os.close();
            }
            catch (Exception e2)
            {
                logger.info(e2);
            }
        }
    }
    
    /********************************************************************************
     * 浏览设备关联图片
     ********************************************************************************/
    public void image()
    {
        try
        {
            long equipmentId = Long.parseLong(request.getParameter("equipmentId"));
            List<Datum> datums = datumService.selectImage(equipmentId);
            if(datums.size() > 0)
            {
                
                StringBuffer stringBuffer = new StringBuffer("");
                for(Datum d : datums)
                {
                    stringBuffer.append(d.getDatumPath() + "[]");
                }
                response.setContentType("text/html"); 
                response.setCharacterEncoding("GBK");
                response.getWriter().print(stringBuffer.toString());
            }
            else
            {
                response.getWriter().print("no-image");
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    /********************************************************************************
     * 回显图片
     ********************************************************************************/
    public void showImage()
    {
        
        try
        {
            String imageUrl = UTF8.toUTF8(request.getParameter("imageUrl").trim());
            File file = new File(imageUrl);
            InputStream is = new FileInputStream(file);
            Image image = ImageIO.read(is);
            String imageType = imageUrl.substring(imageUrl.lastIndexOf(".") + 1);
            OutputStream out = response.getOutputStream();
            ImageIO.write((RenderedImage)image, imageType, out);
            out.flush();
            out.close();
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }

    public File getDatumFile()
    {
        return datumFile;
    }

    public void setDatumFile(File datumFile)
    {
        this.datumFile = datumFile;
    }

    public String getDatumFileFileName()
    {
        return datumFileFileName;
    }

    public void setDatumFileFileName(String datumFileFileName)
    {
        this.datumFileFileName = datumFileFileName;
    }
}