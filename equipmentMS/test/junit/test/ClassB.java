package junit.test;
/**
 * @Title        : ClassB.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月10日 上午9:10:40
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 */
public class ClassB extends ClassA
{
    static
    {
        System.out.println("a");
    }
    
    public ClassB()
    {
        System.out.println("b");
    }
    
    public static void main(String[] args)
    {
        ClassA ab = new ClassB();
        ab = new ClassB();
    }
}