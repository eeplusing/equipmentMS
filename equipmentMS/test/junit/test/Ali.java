package junit.test;

/**
 * @Title : Ali.java
 * @Description :
 * @Author : BianWeiqing
 * @DateTime : 2015年3月10日 上午9:23:49
 * @Copyright : 2015 Moutum All Rights Reserved
 * @version : V1.0
 */
public class Ali
{

    private static int k = 0;
    private static Ali t3;
    private static Ali t1 = new Ali("t1");
    private static Ali t2 = new Ali("t2");
    private static int i = print("i");
    private static int n = 99;
    
    {
        print("构造块");
    }
    
    static
    {
        print("静态块");
    }

    public Ali(String s)
    {
        System.out.println(++k + ":" + s + "   i:" + i + "   n:" + n);
    }

    private static int print(String s)
    {
        System.out.println(++k + ":" + s + "   i:" + i + "   n:" + n);
        n++;
        return ++i;
    }

    public static void main(String[] args)
    {
        Ali t = new Ali("init");
    }

}