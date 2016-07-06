package com.moutum.equ.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/************************************************************************************
 * @Title : XMLUtil.java
 * @Description :
 * @Author : BianWeiqing
 * @DateTime : 2015年7月17日 下午4:01:05
 * @Copyright : 2015 Moutum All Rights Reserved
 * @version : V1.0
 ************************************************************************************/
public class XMLUtil
{
    public static Map<String, String> CONFIG_MAP = new HashMap<String, String>();
    
    @SuppressWarnings("rawtypes")
    public static void parserXml(String fileName)
    {
        File inputXml = new File(fileName);
        SAXReader saxReader = new SAXReader();
        FileInputStream in = null;
        Reader read = null;
        try
        {
            in = new FileInputStream(inputXml);
            read = new InputStreamReader(in, "UTF-8");
        }
        catch (FileNotFoundException e1)
        {
            e1.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        try
        {
            Document document = saxReader.read(read);
            Element fields = document.getRootElement();
            for (Iterator i = fields.elementIterator(); i.hasNext();)
            {
                Element node = (Element)i.next();
                CONFIG_MAP.put(node.getName(), node.getText());
            }
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
    }
}