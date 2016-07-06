package com.moutum.equ.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/************************************************************************************
 * @Title        : DateUtil.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月27日 下午1:54:15
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class DateUtil
{
    public static String YYYY_MM_DD = "yyyy-MM-dd";
    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    public static Date stringToDate(String source, String format) throws Exception 
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        
        try
        {
            return sdf.parse(source);
        }
        catch (ParseException e)
        {
            throw new Exception("字符串转换日期异常：字符串[" + source + "]");
        }
    }
    
    public static Date formatNewDate(String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try
        {
            return sdf.parse(dateToString(new Date(), format));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String dateToString(Date date, String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try
        {
            return sdf.format(date);
        }
        catch (Exception e)
        {
            return null;
        }
        
    }
    
    public static void main(String[] args)
    {
        System.out.println(formatNewDate(YYYY_MM_DD_HH_MM_SS));
    }
}

