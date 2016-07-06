package com.moutum.equ.action;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.moutum.equ.util.EncodeConvertor;
import com.moutum.equ.domain.Equipment;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.EquipmentDto;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.util.DateUtil;
import com.moutum.equ.util.DeCode;
import com.moutum.equ.util.ExcelUtil;
import com.moutum.equ.util.XMLUtil;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title        : EquipmentAction.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月25日 上午11:05:36
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Controller @Scope("prototype")
public class EquipmentAction extends BaseAction<Equipment>
{

    private static final long serialVersionUID = 861837795997831735L;
    
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    Logger logger = Logger.getLogger(EquipmentAction.class);
    
    /********************************************************************************
     * 进入设备浏览,查询页面
     * @return
     ********************************************************************************/
    public String manage()
    {
        Map<String, List<?>> map = equipmentService.getSelectLists();
        for(String s : map.keySet())
        {
            request.setAttribute(s, map.get(s));
        }
        return "manage";
    }
    
    /********************************************************************************
     * 加载设备信息列表
     * @return
     ********************************************************************************/
    public String list()
    {
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("page"));
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        try
        {
            String typeName = request.getParameter("typeName");
            if(!typeName.equals("ALL"))
            {
                map.put("typeName", typeName);
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
        try
        {
            String useState = request.getParameter("useState");
            if(!useState.equals("ALL"))
            {
                map.put("useState", useState);
            }
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
        try
        {
            String equNo = request.getParameter("equNo").trim();
            if(null != equNo && !equNo.equals("") && equNo.length() > 0)
            {
                map.put("equNo", equNo);
            }
            
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
        PageBean page = equipmentService.getPage(pageNum, pageSize, map);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        //设置总数据量
        jsonMap.put("total", page.getRecordCount());
        //当前显示数据
        jsonMap.put("rows", page.getRecordList());
        
        result = JSONObject.fromObject(jsonMap);
        return "list";
    }
    
    /********************************************************************************
     * 地理信息系统设备信息查看弹出框
     * @return
     ********************************************************************************/
    public String info()
    {
        int equId = Integer.parseInt(request.getParameter("eid"));
        request.setAttribute("equ", equipmentService.toEqudto(equipmentService.select(equId)));
        return "info";
    }
    
    /********************************************************************************
     * 保存设备信息
     * @return
     ********************************************************************************/
    public void save()
    {
        try
        {
            Equipment equ = createEquipment(request);
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            switch (equipmentService.save(equ))
            {
            case 1:
                response.getWriter().print("添加成功");
                logger.debug("[BWQ]---操作:添加设备:" + equ.getEquipmentNo() +",结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
                
            case 0:
                response.getWriter().print("设备编号重复");
                logger.debug("[BWQ]---操作:添加设备:" + equ.getEquipmentNo() +",结果:编号重复,操作者:" + loginuser.getLoginAccount());
                break;
                
            default:
                response.getWriter().print("添加失败");
                logger.debug("[BWQ]---操作:添加设备:" + equ.getEquipmentNo() +",结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e.getMessage());
        }
    }
    
    /********************************************************************************
     * 保存编辑后的设备信息
     * @return
     ********************************************************************************/
    public void modify()
    {
        
        try
        {
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            Equipment equipment = createEquipment(request);
            equipment.setEquipmentId(Long.parseLong(request.getParameter("equipmentId")));
            switch (equipmentService.modify(equipment))
            {
            case 1:
                response.getWriter().print("更新成功");
                logger.debug("[BWQ]---操作:更新设备信息:" + equipment.getEquipmentNo() +",结果:成功,操作者:" + loginuser.getLoginAccount());
                break;
                
            case 0:
                response.getWriter().print("该设备不存在");
                logger.debug("[BWQ]---操作:更新设备信息:" + equipment.getEquipmentNo() +",结果:设备不存在,操作者:" + loginuser.getLoginAccount());
                break;
                
            default:
                response.getWriter().print("更新失败");
                logger.debug("[BWQ]---操作:更新设备信息:" + equipment.getEquipmentNo() +",结果:失败,操作者:" + loginuser.getLoginAccount());
                break;
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    
    /********************************************************************************
     * 根据输入的设备信息生成二维码图片并将图片位置返回页面用于显示
     ********************************************************************************/
    public void createDoc()
    {
        String equipmentNo = request.getParameter("equipmentNo").trim();
        String imgPath = XMLUtil.CONFIG_MAP.get("equdoccodepath") + equipmentNo + ".png";
        
        try
        {
            response.setContentType("text/html");
            if (DeCode.encode(equipmentNo, imgPath, 200, 200))
            {
                response.getWriter().print(imgPath + ";" + equipmentNo);
            }
            else
            {
                response.getWriter().print("error");
            }
        }
        catch (IOException e)
        {
            logger.info(e);
        }
    }
    
    /********************************************************************************
     * 回显二维码图片
     ********************************************************************************/
    public void showImage()
    {
        String imageName = request.getParameter("imageName");
        File file = new File(imageName);
        try
        {
            InputStream is = new FileInputStream(file);
            Image image = ImageIO.read(is);
            String imageType = imageName.substring(imageName.lastIndexOf(".") + 1);
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
    
    public String open()
    {
        String modle = request.getParameter("modle").trim();
        int equipmentId = Integer.parseInt(request.getParameter("equipmentId").trim());
        request.setAttribute("equipment", equipmentService.select(equipmentId));
        return modle;
    }
    
    /********************************************************************************
     * 添加与文档的关联关系
     ********************************************************************************/
    public void datum()
    {
        try
        {
            long equipmentId = Long.parseLong(request.getParameter("equipmentId").trim());
            String datumIds = request.getParameter("datumIds").trim();
            String[] idArray = null;
            
            if(datumIds.length() > 0)
            {
                idArray = datumIds.split(";");
            }
            
            if(null == idArray)
            {
                response.getWriter().print("未选择文档,关联失败");
            }
            else
            {
                List<String> result = equipmentService.releDatum(equipmentId, idArray);
                if(result.size() == 0)
                {
                    response.getWriter().print("关联成功");
                }
                else
                {
                    String ids = "";
                    for(String id : result)
                    {
                        ids += id + "\t";
                    }
                    response.getWriter().print("以下备品关联失败" + ids);
                }
            }
            
        }
        catch(Exception e)
        {
            logger.info(e);
        }
        
    }
    
    /********************************************************************************
     * 启动扫描模式
     ********************************************************************************/
    public void scanning()
    {
        try
        {
            String equipmentNo = request.getParameter("equipmentNo").trim();
            String modle = request.getParameter("modle").trim();
            if(modle.equals("datum"))
            {
                Equipment equipment = equipmentService.select(equipmentNo);
                if(null != equipment)
                {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(equipment.getEquipmentId() + ";" + equipment.getEquipmentNo() + ";" + equipment.getEquipmentName() + ";" + equipment.getEquipmentModle() + ";" + equipment.getTypeName());
                    response.getWriter().print(stringBuffer.toString());
                }
                else
                {
                    response.getWriter().print("no-equ");
                }
            }
        }
        catch (Exception e)
        {
            logger.info(e);
        }
    }
    
    public String printCode()
    {
        return "print";
    }
    
    @SuppressWarnings({ "deprecation", "unchecked" })
    public void downLoad()
    {
        FileInputStream in = null;
        OutputStream os = null;
        File file = null;
        try
        {
            Map<String, Object> map = new HashMap<String, Object>();
            
            String sdate = request.getParameter("sdate").trim();
            String edate = request.getParameter("edate").trim();
            String eno = request.getParameter("eno").trim();
            if(sdate != null && sdate != "" && sdate.length() > 0)
            {
                map.put("sdate", DateUtil.stringToDate(sdate + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
            }
            
            if(eno != null && eno != "" && eno.length() > 0)
            {
                map.put("equNo", eno);
            }
            
            if(edate != null && edate != "" && edate.length() > 0)
            {
                map.put("edate", DateUtil.stringToDate(edate + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
            }
            
            map.put("desc", "equipmentStartDate");
            String sysPath = request.getRealPath("/report_template").replace("\\", "/");
            String tempPath = request.getRealPath("/temp_file").replace("\\", "/");
            File excelModle = new File(sysPath + "/equipment_report_template.xlsx");
            file = new File(tempPath + "/temp_file.xlsx");
            if(!file.exists())
            {
                file.createNewFile();
            }
            List<EquipmentDto> equs = equipmentService.getPage(1, 1000000, map).getRecordList();
            new ExcelUtil<EquipmentDto>().exportExcel(excelModle, equs, file, 5);
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            Cookie cookie = new Cookie("fileDownload", "true");
            response.addCookie(cookie);
            response.addHeader("Content-Disposition","attachment;filename=" + EncodeConvertor.encodeConvert("设备台账.xlsx", "gb2312", "iso8859-1"));
            in = new FileInputStream(file);
            os = response.getOutputStream();
            byte[] b = new byte[1024 * 1024 * 4];
            int count = in.read(b);
            while(count > 0)
            {
                os.write(b, 0, count);
                count = in.read(b);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
                e2.printStackTrace();
            }
            file.delete();
        }
    }

    private Equipment createEquipment(HttpServletRequest req) throws Exception
    {
        try
        {
            Equipment equ = new Equipment();
            equ.setEquipmentNo(req.getParameter("equipmentNo"));
            equ.setEquipmentName(req.getParameter("equipmentName"));
            equ.setEquipmentModle(req.getParameter("equipmentModle"));
            equ.setTypeName(req.getParameter("typeName"));
            equ.setEquipmentProducer(req.getParameter("equipmentProducer"));
            equ.setEquipmentSupplier(req.getParameter("equipmentSupplier"));
            equ.setEquipmentBuyTime(DateUtil.stringToDate(req.getParameter("equipmentBuyTime"), DateUtil.YYYY_MM_DD));
            equ.setEquipmentBuyType(req.getParameter("equipmentBuyType"));
            equ.setEquipmentRecipient(req.getParameter("equipmentRecipient"));
            equ.setEquipmentProvider(req.getParameter("equipmentProvider"));
            equ.setEquipmentDirector(req.getParameter("equipmentDirector"));
            equ.setUseState(req.getParameter("useState"));
            equ.setEquipmentStartDate(DateUtil.stringToDate(req.getParameter("equipmentStartDate"), DateUtil.YYYY_MM_DD));
            equ.setEquipmentUseYears(Integer.parseInt(req.getParameter("equipmentUseYears")));
            equ.setEquipmentMonetaryAmount(Double.parseDouble(req.getParameter("equipmentMonetaryAmount")));
            equ.setAmount( Integer.parseInt(req.getParameter("amount")));
            equ.setAreaCode(req.getParameter("areaCode"));
            equ.setRemark(req.getParameter("remark"));
            return equ;
        }
        catch (Exception e)
        {
            throw new Exception(e);
        }
    }
}