package com.moutum.equ.util;
/************************************************************************************
 * @Title        : ExcelException.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年6月9日 上午11:07:50
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class ExcelException extends Exception
{

    /********************************************************************************
     * 
     ********************************************************************************/
    private static final long serialVersionUID = 7411848486071663705L;

    public ExcelException()
    {
    }

    public ExcelException(String s)
    {
        super(s);
    }

    public ExcelException(String s, Throwable t)
    {
        super(s, t);
    }
}

