package com.moutum.equ.util;

import java.util.Map;

/************************************************************************************
 * @Title        : HQLUtil.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月18日 上午10:25:16
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class HQLUtil
{
    private String hql = "";
    
    private String alias = "";

    public HQLUtil(Class<?> clazz)
    {
        alias = clazz.getSimpleName().substring(0, 1).toLowerCase();
        hql = "FROM " + clazz.getSimpleName() + " " + alias;
    }
    
    public String mapToHql(Map<String, String> map)
    {
        boolean append = true;
        for(String s : map.keySet())
        {
            if(append)
            {
                hql += " WHERE " + alias + ".";
                whereClause(s, map.get(s));
                append = false;
            }
            else
            {
                hql += " AND " + alias + ".";
                whereClause(s, map.get(s));
            }
        }
        return hql;
    }

    private void whereClause(String field, String value)
    {
        String[] factor = value.split("|");
        switch (Integer.parseInt(factor[0]))
        {
        case 0://代表"="
            
            break;
        
        case 1://代表"<"
            
            break;
        case 2://代表">"
            
            break;
        
        case 3://代表"<="
            
            break;
        case 4://代表">="
            
            break;
        
        case 5://代表"!="
            
            break;
        case 6://代表"in()"
            
            break;
        case 7://代表"in[)"
            
            break;
        case 8://代表"in[]"
                 
            break;
        case 9://代表"in(]"
            
            break;
        case 10://代表"like"
            
            break;
        default:
            break;
        }
    }
}