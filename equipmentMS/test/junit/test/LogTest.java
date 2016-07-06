package junit.test;

import org.apache.log4j.Logger;

/**
 * @Title        : LogTest.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 下午12:42:45
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 */
public class LogTest
{
    private static Logger log = Logger.getLogger(LogTest.class);
    
    public static void main(String[] args)
    {
        log.info("-------------[BWQ]日志测试-------------");
    }
}