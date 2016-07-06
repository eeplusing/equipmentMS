package com.moutum.equ.util;

import java.io.UnsupportedEncodingException;

public class EncodeConvertor
{
    
    public static final String UTF = "utf-8";
    
    public static final String ISO = "iso8859-1";
    
    public static final String GBK = "GBK";
    
    public static final String GB_2312 = "gb2312";
    
	public static String encodeConvert(String source, String oldCharSet, String newCharSet) throws UnsupportedEncodingException
	{
	    try
        {
            return new String(source.getBytes(oldCharSet), newCharSet);
        } 
        catch (UnsupportedEncodingException e)
        {
            throw new UnsupportedEncodingException();
        }
	}
}