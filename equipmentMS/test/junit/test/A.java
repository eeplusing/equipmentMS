package junit.test;
/**
 * @Title        : A.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月10日 上午9:29:25
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 */
public class A
{
    static A a1;
    static A a2 = new A("a2");
    static A a3 = new A("a3");
    
    public String name;
    
    static int i = print("i");
    
    {
        print("构造块");
    }
    static
    {
        print("静态块");
    }
    
    public A(String s)
    {
        System.out.println("构造函数" + s);
        this.name = s;
    }
    
    public static A getA()
    {
        return a2;
    }
    
    public static int print(String s)
    {
        System.out.println("s:" + s);
        return ++i;
    }
    
    public static void main(String[] args)
    {
        getA();
    }

}

