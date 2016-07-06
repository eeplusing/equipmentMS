package junit.test;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.moutum.equ.dao.DatumDao;
import com.moutum.equ.dao.DatumFormatDao;
import com.moutum.equ.dao.DatumTypeDao;
import com.moutum.equ.dao.EquipmentDao;
import com.moutum.equ.dao.IodepotDao;
import com.moutum.equ.dao.ModleDao;
import com.moutum.equ.dao.OperLogDao;
import com.moutum.equ.dao.RightDao;
import com.moutum.equ.dao.RoleDao;
import com.moutum.equ.dao.RoleModleDao;
import com.moutum.equ.dao.RoleRightDao;
import com.moutum.equ.dao.TypeDao;
import com.moutum.equ.dao.UseStateDao;
import com.moutum.equ.dao.UserDao;
import com.moutum.equ.domain.Modle;
import com.moutum.equ.domain.ProvideBill;
import com.moutum.equ.domain.ReceiveBill;
import com.moutum.equ.domain.Right;
import com.moutum.equ.domain.Role;
import com.moutum.equ.domain.RoleModle;
import com.moutum.equ.domain.Sparepart;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.Bill;
import com.moutum.equ.service.ProvideBillService;
import com.moutum.equ.service.RightService;

/**
 * @Title        : DaoTest.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午11:45:57
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 */
public class DaoTest
{
    static ApplicationContext act = null;
    static UserDao userDao = null;
    static ModleDao modleDao = null;
    static RightDao rightDao = null;
    static RoleDao roleDao = null;
    static RoleModleDao roleModleDao = null;
    static RoleRightDao roleRightDao = null;
    
    static DatumDao datumDao = null;
    static DatumFormatDao datumFormatDao = null;
    static DatumTypeDao datumTypeDao = null;
    static EquipmentDao equipmentDao = null;
    
    static IodepotDao iodepotDao = null;
    static OperLogDao operLogDao = null;
    static TypeDao typeDao = null;
    static UseStateDao useStateDao = null;
    static RightService rightService = null;
    static ProvideBillService provideBillService = null;
    
    @BeforeClass public static void setUpBeforeClass() throws Exception
    {
        act = new ClassPathXmlApplicationContext("spring-beans.xml");
        userDao = (UserDao)act.getBean("userDaoImpl");
        modleDao = (ModleDao)act.getBean("modleDaoImpl");
        rightDao = (RightDao)act.getBean("rightDaoImpl");
        roleDao = (RoleDao)act.getBean("roleDaoImpl");
        roleModleDao = (RoleModleDao)act.getBean("roleModleDaoImpl");
        roleRightDao = (RoleRightDao)act.getBean("roleRightDaoImpl");
        datumDao = (DatumDao)act.getBean("datumDaoImpl");
        datumFormatDao = (DatumFormatDao)act.getBean("datumFormatDaoImpl");
        datumTypeDao = (DatumTypeDao)act.getBean("datumTypeDaoImpl");
        equipmentDao = (EquipmentDao)act.getBean("equipmentDaoImpl");
        iodepotDao = (IodepotDao)act.getBean("iodepotDaoImpl");
        operLogDao = (OperLogDao)act.getBean("operLogDaoImpl");
        typeDao = (TypeDao)act.getBean("typeDaoImpl");
        useStateDao = (UseStateDao)act.getBean("useStateDaoImpl");
        rightService = (RightService)act.getBean("rightServiceImpl");
        provideBillService = (ProvideBillService)act.getBean("provideBillServiceImpl");
    }
    
    @Test public void addUser()
    {
        for(int i = 0; i < 50; i++)
        {
            User user = new User();
            user.setIsActivated(0);
            user.setLoginAccount("test" + i);
            user.setLoginPassword("te.st" + i);
            user.setUserName("test_" + i);
            user.setUserPhone("00000000000");
            userDao.save(user);
        }
    }
    

    @Test public void addRight()
    {
        Right right = new Right();
        right.setName("查询账户信息列表");
        right.setFunctionName("user_list");
        right.setModleId(6);
        right.setRemark("查询账户信息列表的权限");
        rightDao.save(right);
    }
    
    @Test public void addModle()
    {
        String[] name = {"文档类型","文档格式","文档信息","二维码管理"};
        String[] url ={"/datum_type","/datum_format","/datum_info","/decode_manager"};
        
        for(int i = 0; i <3; i++)
        {
            Modle modle = new Modle();
            modle.setParentId(5);
            modle.setName(name[i]);
            modle.setUrl(url[i]);
            modleDao.save(modle);
        }
    }
    
    @Test public void addRole()
    {
        Role role = new Role();
        role.setName("超级管理员");
        roleDao.save(role);
    }
    @Test public void addRoleModle()
    {
        for(int i = 7; i<23; i++)
        {
            RoleModle rm = new RoleModle();
            rm.setRoleId(1);
            rm.setModleId(i);
            roleModleDao.save(rm);
        }
    }
    
    @Test public void modleTest()
    {
        User user = userDao.getById(1l);
        List<Modle> list = modleDao.selectFirstModleByUser(user, 0);
        for(Modle m : list)
        {
            System.out.println(m.getModleId() + "----" + m.getName());
        }
    }
    
    @Test public void userTest()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("loginAccount", "%te%");
        map.put("userName", "%t__%");
        map.put("roleId", null);
        System.out.println(userDao.getRecordCount(map));
        for(User u : userDao.getRecordList(6, 10, map))
        {
            System.out.println(u.toString());
        }
    }
    
    @Test public void rightServiceTest()
    {
    }
    
    @Test public void userTest2()
    {
       List<User> list = userDao.findAll();
       for(User u : list)
       {
           u.setLoginPassword(DigestUtils.md5Hex(DigestUtils.md5Hex(u.getLoginPassword()) + "[" + u.getLoginAccount() + "]"));
           userDao.update(u);
       }
    }
    
    @Test public void operTest()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("operType", 0);
        System.out.println(operLogDao.getRecordCount(map));
    }
    
    @Test public void provideTest()
    {
        String[] idens = {"A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P"};
        
        ProvideBill pb = new ProvideBill();
        Set<ReceiveBill> receiveBills = new HashSet<ReceiveBill>();
        
        pb.setAmount(16);
        pb.setAreaCode("213");
        pb.setEquipmentModle("XH-15-819-001");
        pb.setEquipmentName("测试设备");
        pb.setEquipmentNo("120001");
        pb.setEquipmentStartDate(new Date());
        pb.setEquipmentUseYears(50);
        
        for(String is : idens)
        {
            ReceiveBill rb = new ReceiveBill();
            if(is.equals("A"))
            {
                rb.setStationName("车站领导");
            }
            else if(is.equals("B"))
            {
                rb.setStationName("党办室");
            }
            else if(is.equals("C"))
            {
                rb.setStationName("办公室");
            }
            else if(is.equals("D"))
            {
                rb.setStationName("人劳科");
            }
            else if(is.equals("E"))
            {
                rb.setStationName("财务科");
            }
            else if(is.equals("F"))
            {
                rb.setStationName("技术科");
            }
            else if(is.equals("G"))
            {
                rb.setStationName("教育科");
            }
            else if(is.equals("H"))
            {
                rb.setStationName("安全科");
            }
            else if(is.equals("I"))
            {
                rb.setStationName("信技科");
            }
            else if(is.equals("J"))
            {
                rb.setStationName("货运科");
            }
            else if(is.equals("K"))
            {
                rb.setStationName("调度车间");
            }
            else if(is.equals("L"))
            {
                rb.setStationName("机务车间");
            }
            else if(is.equals("M"))
            {
                rb.setStationName("运转一车间");
            }
            else if(is.equals("N"))
            {
                rb.setStationName("运转二车间");
            }
            else if(is.equals("O"))
            {
                rb.setStationName("综合设备车间");
            }
            else if(is.equals("P"))
            {
                rb.setStationName("货检车间");
            }
            rb.setAmount(1);
            rb.setProvideDate(new Date());
            rb.setReceiver("receiver" + is);
            rb.setRemark("描述" + is);
            receiveBills.add(rb);
        }
        pb.setReceiveBills(receiveBills);
        provideBillService.save(pb);
    }
}