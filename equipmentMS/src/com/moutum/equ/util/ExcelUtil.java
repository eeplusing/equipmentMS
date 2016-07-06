package com.moutum.equ.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.moutum.equ.dto.EquipmentDto;

/************************************************************************************
 * @Title        : ExcelUtil.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年8月18日 上午10:09:17
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class ExcelUtil<T>
{
    /********************************************************************************
     * excel文件按模板导出方法
     * @param excelModle: 模板文件位置
     * @param datas: 数据列表
     * @param file: 导出文件位置
     ********************************************************************************/
    public void exportExcel(File excelModle, List<T> datas, File file, int minRows)
    {
        String[] getMethodsName = null;
        
        //将工作簿指向模板文件  
        Workbook workbook = null;
        try
        {
            workbook = new XSSFWorkbook(new FileInputStream(excelModle));
        }
        catch (Exception e)
        {
            try
            {
                workbook = new HSSFWorkbook(new FileInputStream(excelModle));
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
        // 生成一个表格
        Sheet sheet = workbook.getSheetAt(0);
        for(int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++)
        {
            Row row = sheet.getRow(rowNum);
            //获取第一列数据内容
            
            String startDelim  = "";
            if(null != row.getCell(0))
            {
                startDelim = row.getCell(0).getStringCellValue();
            }
            
            if(startDelim.startsWith("#foreach"))
            {
                getMethodsName = getHeadTitle(sheet.getRow(rowNum + 1));
                for(T t : datas)
                {
                    Row newRow = sheet.createRow(rowNum);
                    for(int index = 0; index < getMethodsName.length; index++)
                    {
                        Cell cell = newRow.createCell(index);
                        cell.setCellValue(getCellValue(t, getMethodsName[index]));
                    }
                    rowNum++;
                }
                for(int i = rowNum; i < minRows; i++)
                {
                    sheet.createRow(i);
                }
                try
                {
                    workbook.write(new FileOutputStream(file));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    
    private String getCellValue(T t, String methodName)
    {
        StringBuffer stringBuffer = new StringBuffer("");
        try
        {
            Method method = t.getClass().getMethod(methodName, new Class[]{});//利用反射获取属性对应的get方法;
            Object value = method.invoke(t, null);//执行get方法获取返回值(get方法没有参数，所以第二个参数为null);
            
            if(value instanceof String)
            {
                stringBuffer.append(value);
            }
            else if(value instanceof Date)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                stringBuffer.append(sdf.format((Date)value));
            }
            else if(value instanceof Double)
            {
                stringBuffer.append((Double)value + "");
            }
            else if(value instanceof Integer)
            {
                stringBuffer.append((Integer)value + "");
            }
            else if(value instanceof Long)
            {
                stringBuffer.append((Long)value + "");
            }
            else if(value instanceof Boolean)
            {
                boolean bvalue = (Boolean)value;
                if(bvalue)
                {
                    stringBuffer.append("Y");
                }
                else
                {
                    stringBuffer.append("N");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    private String[] getHeadTitle(Row row)
    {
        String[] methodsName = new String[row.getLastCellNum()];
        for(int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++)
        {
            String cellValue  = row.getCell(cellNum).getStringCellValue();
            if(cellValue.startsWith("${entity."))
            {
                cellValue = cellValue.substring(9, cellValue.length() - 1);
                String[] stageNames = cellValue.split("_");
                String methodName = "get";
                for(String str : stageNames)
                {
                    methodName += str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
                }
                methodsName[cellNum] = methodName;
            }
            else
            {
                try
                {
                    throw new ExcelException("数据导出模板表体信息占位字段错误");
                }
                catch (ExcelException e)
                {
                    e.printStackTrace();
                    return null;
                }
            }
            
        }
        return methodsName;
    }

    public static void main(String[] args)
    {
        ExcelUtil<EquipmentDto> eebm = new ExcelUtil<EquipmentDto>();
        File excelModle = new File("E:\\equipment_report_template.xlsx");
        File file = new File("D:\\test2.xlsx");
        List<EquipmentDto> equs = new ArrayList<EquipmentDto>();
        EquipmentDto ed = new EquipmentDto();
        ed.setEquipmentId(1);
        ed.setEquipmentNo("100001");
        ed.setEquipmentName("测试设备");
        ed.setEquipmentModle("EQU_TEST_001");
        ed.setTypeName("动力设备");
        ed.setEquipmentProducer("测试生产商");
        ed.setEquipmentSupplier("测试供应商");
        ed.setEquipmentBuyTime("2015-05-18");
        ed.setEquipmentBuyType("直购");
        ed.setEquipmentRecipient("测试接收人");
        ed.setEquipmentProvider("测试发放人");
        ed.setEquipmentDirector("测试监督人");
        ed.setUseState("启用");
        ed.setStartDate("2015-08-01");
        ed.setEquipmentUseYears(50);
        ed.setEquipmentMonetaryAmount(58.6);
        ed.setAmount(1);
        ed.setAreaCode("113628");
        ed.setRemark("无");
        equs.add(ed);
        eebm.exportExcel(excelModle, equs, file, 5);
    }
}

