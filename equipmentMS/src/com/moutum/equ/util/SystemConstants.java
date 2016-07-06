package com.moutum.equ.util;
/**
 * @Title        : SystemConstants.java
 * @Description : 系统常量
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月10日 下午4:34:21
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 */
public class SystemConstants
{
    private static final String _PATH = SystemConstants.class.getClassLoader().getResource("").getPath().replace("%20", " ");
    
    public static final String PATH_HEAD = _PATH.substring(0, _PATH.indexOf("WEB-INF")) + "WEB-INF/";
    
    public static final int OPERLOG_TYPE_LOGIN = 1;//系统日志类型：登陆系统
    
    public static final int OPERLOG_TYPE_LOGOUT = 2;//系统日志类型：退出系统
    
    public static final int OPERLOG_TYPE_DEPOT_IN = 3;//系统日志类型：备品入库
    
    public static final int OPERLOG_TYPE_DEPOT_OUT = 4;//系统日志类型：备品出库
    
    public static final int OPERLOG_TYPE_DATUM_UPLOAD = 5;//系统日志类型：文档上传
    
    public static final int OPERLOG_TYPE_DATUM_DOWNLOAD = 6;//系统日志类型：文档下载
    
    public static final int OPERLOG_TYPE_DATUM_DELETE = 7;//系统日志类型：文档删除
    
    public static final int OPERLOG_TYPE_EQU_DELETE = 8;//系统日志类型：删除设备
    
    public static final int OPERLOG_TYPE_EQU_UPDATE = 9;//系统日志类型：更新设备
    
    public static final int OPERLOG_TYPE_EQU_SAVE = 0;//系统日志类型：增加设备
    
    public static final int OPERLOG_TYPE_SPART_DELETE = 18;//系统日志类型：删除备品
    
    public static final int OPERLOG_TYPE_SPART_UPDATE = 19;//系统日志类型：更新备品
    
    public static final int OPERLOG_TYPE_SPART_SAVE = 10;//系统日志类型：增加备品
    
    public static final int OPERLOG_TYPE_OTHER = 11;//系统日志类型：其他
}