package com.moutum.equ.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.moutum.equ.domain.Equipment;
import com.moutum.equ.domain.ProvideBill;
import com.moutum.equ.domain.ReceiveBill;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.dto.ProvideBillDto;
import com.moutum.equ.util.DateUtil;
import com.moutum.equ.util.EncodeConvertor;
import com.moutum.equ.util.ExcelUtil;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title : ProvideBillAction.java
 * @Description :
 * @Author : BianWeiqing
 * @DateTime : 2015年8月19日 上午10:53:18
 * @Copyright : 2015 Moutum All Rights Reserved
 * @version : V1.0
 ************************************************************************************/
@Controller
@Scope("prototype")
public class ProvideBillAction extends BaseAction<ProvideBill>
{

    private static final long serialVersionUID = 8943072458134260874L;

    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    Logger logger = Logger.getLogger(ProvideBillAction.class);

    public String list()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        if(request.getParameter("equipmentId") != null)
        {
            String equId = request.getParameter("equipmentId").trim();
            if(equId != "")
            {
                Equipment equ = equipmentService.select(Integer.parseInt(equId));
                System.out.println(equ.getEquipmentId());
                map.put("equNo", equ.getEquipmentNo());
            }
        }
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("page"));
        
        PageBean page = provideBillService.getPage(pageNum, pageSize, map);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        // 设置总数据量
        jsonMap.put("total", page.getRecordCount());
        // 当前显示数据
        jsonMap.put("rows", page.getRecordList());
        result = JSONObject.fromObject(jsonMap);
        return "list";
    }

    public String openSaveWin()
    {
        request.setAttribute("equs", equipmentService.getAll());
        return "open_save_win";
    }
    
    public String openModifyWin()
    {
        request.setAttribute("pbd", provideBillService.getDtoById(Long.parseLong(request.getParameter("id"))));
        request.setAttribute("equs", equipmentService.getAll());
        return "open_modify_win";
    }

    public void loadEquipment()
    {
        try
        {
            Equipment equ = equipmentService.select(request.getParameter("equ_no").trim());
            if (null != equ)
            {
                StringBuffer json = new StringBuffer();
                json.append("{name:\"" + equ.getEquipmentName() + "\",");
                json.append("modle:\"" + equ.getEquipmentModle() + "\",");
                json.append("amount:\"" + equ.getAmount() + "\",");
                json.append("startDate:\"" + DateUtil.dateToString(equ.getEquipmentStartDate(), "yyyy-MM-dd") + "\",");
                json.append("useYears:\"" + equ.getEquipmentUseYears() + "\",");
                json.append("areaCode:\"" + equ.getAreaCode() + "\"}");
                response.getWriter().print(json.toString());
            }
            else
            {
                response.getWriter().print("no-equ");
            }
        }
        catch (Exception e)
        {
        }
    }

    public void add()
    {
        User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
        try
        {
            ProvideBill pb = getProvideBill();
            if(null != provideBillService.getByNo(pb.getEquipmentNo()))
            {
                response.getWriter().print("2");
            }
            else
            {
                provideBillService.save(getProvideBill());
                response.getWriter().print("1");
            }
            
        }
        catch (Exception e)
        {
            logger.info("[BWQ]---操作:设备发放,结果:操作失败,操作者:" + loginuser.getLoginAccount() + "\r\t原因：" + e.getMessage());
            try
            {
                response.getWriter().print("0");
            }
            catch (Exception e2)
            {
            }
        }
    }

    public void modify()
    {
        User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
        try
        {
            long id = Long.parseLong(request.getParameter("pbid"));
            ProvideBill pb = getProvideBill();
            ProvideBill pb2 = provideBillService.getByNo(pb.getEquipmentNo());
            pb.setId(id);
            if(null != pb2 && pb.getId() != pb2.getId())
            {
                response.getWriter().print("2");
            }
            else
            {
                provideBillService.modify(pb);
                response.getWriter().print("1");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.info("[BWQ]---操作:修改设备发放明细,结果:操作失败,操作者:" + loginuser.getLoginAccount() + "\r\t原因：" + e.getMessage());
            try
            {
                response.getWriter().print("0");
            }
            catch (Exception e2)
            {
            }
        }
    }

    @SuppressWarnings("deprecation")
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
            
            if(edate != null && edate != "" && edate.length() > 0)
            {
                map.put("edate", DateUtil.stringToDate(edate + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
            }
            if(eno != null && eno != "" && eno.length() > 0)
            {
                map.put("equNo", eno);
            }
            
            String sysPath = request.getRealPath("/report_template").replace("\\", "/");
            String tempPath = request.getRealPath("/temp_file").replace("\\", "/");
            File excelModle = new File(sysPath + "/provide_bill_report_template.xlsx");
            file = new File(tempPath + "/temp_file.xlsx");
            if (!file.exists())
            {
                file.createNewFile();
            }
            List<ProvideBillDto> pbds = provideBillService.getAll(map);
            new ExcelUtil<ProvideBillDto>().exportExcel(excelModle, pbds, file, 6);
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            Cookie cookie = new Cookie("fileDownload", "true");
            response.addCookie(cookie);
            response.addHeader("Content-Disposition", "attachment;filename=" + EncodeConvertor.encodeConvert("设备发放明细.xlsx", "gb2312", "iso8859-1"));
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

    private ProvideBill getProvideBill() throws Exception
    {
        User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
        ProvideBill pb = new ProvideBill();
        try
        {
            String[] idens = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
                               "J", "K", "L", "M", "N", "O", "P" };
            String no = request.getParameter("equno");
            int amount = Integer.parseInt(request.getParameter("amount"));
            Equipment equ = equipmentService.select(no);
            Set<ReceiveBill> receiveBills = new HashSet<ReceiveBill>();

            pb.setAmount(amount);
            pb.setAreaCode(equ.getAreaCode());
            pb.setEquipmentModle(equ.getEquipmentModle());
            pb.setEquipmentName(equ.getEquipmentName());
            pb.setEquipmentNo(no);
            pb.setEquipmentStartDate(equ.getEquipmentStartDate());
            pb.setEquipmentUseYears(equ.getEquipmentUseYears());
            int sum = 0;
            for (String is : idens)
            {
                ReceiveBill rb = new ReceiveBill();
                if (is.equals("A"))
                {
                    rb.setStationName("车站领导");
                }
                else if (is.equals("B"))
                {
                    rb.setStationName("党办室");
                }
                else if (is.equals("C"))
                {
                    rb.setStationName("办公室");
                }
                else if (is.equals("D"))
                {
                    rb.setStationName("人劳科");
                }
                else if (is.equals("E"))
                {
                    rb.setStationName("财务科");
                }
                else if (is.equals("F"))
                {
                    rb.setStationName("技术科");
                }
                else if (is.equals("G"))
                {
                    rb.setStationName("教育科");
                }
                else if (is.equals("H"))
                {
                    rb.setStationName("安全科");
                }
                else if (is.equals("I"))
                {
                    rb.setStationName("信技科");
                }
                else if (is.equals("J"))
                {
                    rb.setStationName("货运科");
                }
                else if (is.equals("K"))
                {
                    rb.setStationName("调度车间");
                }
                else if (is.equals("L"))
                {
                    rb.setStationName("机务车间");
                }
                else if (is.equals("M"))
                {
                    rb.setStationName("运转一车间");
                }
                else if (is.equals("N"))
                {
                    rb.setStationName("运转二车间");
                }
                else if (is.equals("O"))
                {
                    rb.setStationName("综合设备车间");
                }
                else if (is.equals("P"))
                {
                    rb.setStationName("货检车间");
                }
                if(null != request.getParameter("id" + is) && !"".equals(request.getParameter("id" + is).trim()))
                {
                    rb.setId(Long.parseLong(request.getParameter("id" + is)));
                }
                
                if(null != request.getParameter("amount" + is) && !"".equals(request.getParameter("amount" + is).trim()))
                {
                    int amountX = Integer.parseInt(request.getParameter("amount" + is).trim());
                    sum += amountX;
                    if(sum > amount)
                    {
                        throw new Exception("设备发放总数大于设备所有数");
                    }
                    rb.setAmount(amountX);
                }
                else
                {
                    rb.setAmount(0);
                }
                if(null != request.getParameter("provideDate" + is) && request.getParameter("provideDate" + is).trim() != "")
                {
                    rb.setProvideDate(DateUtil.stringToDate(request.getParameter("provideDate" + is), "yyyy-MM-dd"));
                }
                if(null != request.getParameter("receiver" + is) && !"".equals(request.getParameter("receiver" + is).trim()))
                {
                    rb.setReceiver(request.getParameter("receiver" + is));
                }
                if(null != request.getParameter("remark" + is) && !"".equals(request.getParameter("remark" + is).trim()))
                {
                    rb.setRemark(request.getParameter("remark" + is));
                }
                if(null != request.getParameter("amount" + is) && !"".equals(request.getParameter("amount" + is).trim()) && !"0".equals(request.getParameter("amount" + is).trim()))
                {
                    receiveBills.add(rb);
                }
            }
            pb.setReceiveBills(receiveBills);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.info("[BWQ]---操作:获取设备发放信息,结果:异常,操作者:" + loginuser.getLoginAccount() + "\r\t原因：" + e.getMessage());
            throw new Exception(e.getMessage());
        }
        return pb;
    }
}