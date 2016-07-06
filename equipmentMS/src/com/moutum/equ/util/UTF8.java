package com.moutum.equ.util;

import java.io.UnsupportedEncodingException;

public class UTF8
{
	public static String toUTF8(String source)
	{
		try
		{
			return new String(source.getBytes("iso8859-1"), "utf-8");
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String toGBK(String source)
    {
        try
        {
            return new String(source.getBytes("iso8859-1"), "GBK");
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
