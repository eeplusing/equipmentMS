package com.moutum.equ.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.moutum.equ.domain.Instock;
import com.moutum.equ.dto.InstockDto;
import com.moutum.equ.dto.OutstockDto;
import com.moutum.equ.util.DateUtil;
import com.moutum.equ.util.EncodeConvertor;
import com.moutum.equ.util.ExcelUtil;

/********************************************************************************
 * @Title : IostockAction.java
 * @Description :
 * @Author : HuangWei
 * @DateTime : 2015-8-19 18:19:09
 * @Copyright : 2015 Moutum All Rights Reserved
 * @version : V1.0
 ********************************************************************************/
@Controller
@Scope("prototype")
public class IostockAction extends BaseAction<Instock>
{
    private static final long serialVersionUID = 2662039050262056787L;
    
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    Logger logger = Logger.getLogger(IostockAction.class);

    public String manage()
    {
        return "manage";
    }

    @SuppressWarnings("deprecation")
    public void downLoad()
    {
        FileInputStream in = null;
        OutputStream os = null;
        File file = null;
        String expName = "";
        try
        {
            Map<String, Object> map = new HashMap<String, Object>();

            String sdate = request.getParameter("sdate").trim();
            String edate = request.getParameter("edate").trim();
            String type = request.getParameter("type").trim();
            String sno = request.getParameter("sno").trim();
            if (sdate != null && sdate != "" && sdate.length() > 0)
            {
                map.put("sdate", DateUtil.stringToDate(sdate + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
            }

            if (edate != null && edate != "" && edate.length() > 0)
            {
                map.put("edate", DateUtil.stringToDate(edate + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
            }
            if (sno != null && sno != "" && sno.length() > 0)
            {
                map.put("sparepartNo", sno);
            }

            String sysPath = request.getRealPath("/report_template").replace("\\", "/");
            String tempPath = request.getRealPath("/temp_file").replace("\\", "/");
            
            file = new File(tempPath + "/temp_file.xlsx");
            if (!file.exists())
            {
                file.createNewFile();
            }
            
            File excelModle = null;
            if(type.equals("in"))
            {
                excelModle = new File(sysPath + "/instock_report_template.xlsx");
                List<InstockDto> inDatas = instockService.expData(map);
                new ExcelUtil<InstockDto>().exportExcel(excelModle, inDatas, file, 5);
                expName = "入库台账.xlsx";
            }
            else
            {
                excelModle = new File(sysPath + "/outstock_report_template.xlsx");
                List<OutstockDto> outDatas = outstockService.expData(map);
                new ExcelUtil<OutstockDto>().exportExcel(excelModle, outDatas, file, 5);
                expName = "出库台账.xlsx";
            }
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            Cookie cookie = new Cookie("fileDownload", "true");
            response.addCookie(cookie);
            response.addHeader("Content-Disposition", "attachment;filename=" + EncodeConvertor.encodeConvert(expName, "gb2312", "iso8859-1"));
            in = new FileInputStream(file);
            os = response.getOutputStream();
            byte[] b = new byte[1024 * 1024 * 4];
            int count = in.read(b);
            while (count > 0)
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
}
