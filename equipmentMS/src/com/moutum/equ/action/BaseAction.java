package com.moutum.equ.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.moutum.equ.service.DatumFormatService;
import com.moutum.equ.service.DatumService;
import com.moutum.equ.service.DatumTypeService;
import com.moutum.equ.service.EquipmentService;
import com.moutum.equ.service.HomeService;
import com.moutum.equ.service.InstockService;
import com.moutum.equ.service.IodepotService;
import com.moutum.equ.service.ModleService;
import com.moutum.equ.service.NoticeService;
import com.moutum.equ.service.OperLogService;
import com.moutum.equ.service.ProvideBillService;
import com.moutum.equ.service.OutstockService;
import com.moutum.equ.service.RightService;
import com.moutum.equ.service.RoleModleService;
import com.moutum.equ.service.RoleRightService;
import com.moutum.equ.service.RoleService;
import com.moutum.equ.service.SpacstockService;
import com.moutum.equ.service.TypeService;
import com.moutum.equ.service.UseStateService;
import com.moutum.equ.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/************************************************************************************
 * @Title        : BaseAction.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 下午3:14:39
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>
{
    private static final long serialVersionUID = 833612228869245847L;
    
    protected T model;
    
    protected int pageNum = 1; // 当前页
    
    protected int pageSize = 15; // 每页显示多少条记录
    
    protected int pageCount = 0;//总页数
    
    protected int recordCount = 0;//总记录数
    
    protected JSONObject result;
    
    protected List<?> recordList = Collections.EMPTY_LIST;
    
    @Resource protected HomeService homeService;
    
    @Resource protected UserService userService;
    
    @Resource protected RoleService roleService;
    
    @Resource protected RightService rightService;
    
    @Resource protected ModleService modleService;
    
    @Resource protected RoleRightService roleRightService;
    
    @Resource protected RoleModleService roleModleService;
    
    @Resource protected TypeService typeService;
    
    
    @Resource protected UseStateService useStateService;
    
    @Resource protected EquipmentService equipmentService;
    
    @Resource protected DatumTypeService datumTypeService;
    
    @Resource protected DatumFormatService datumFormatService;
    
    @Resource protected DatumService datumService;
    
    @Resource protected NoticeService noticeService;
    
    @Resource protected OperLogService operLogService;
    
    @Resource protected IodepotService iodepotService;

   @Resource protected ProvideBillService provideBillService;
    
    @Resource protected InstockService instockService;
    
    @Resource protected OutstockService outstockService;
    
    @Resource protected SpacstockService spacstockService;
    
    
    @SuppressWarnings("unchecked")
    public BaseAction()
    {
        try
        {
            Class<T> clazz = null;
            Class<?> c = getClass();
            Type t = c.getGenericSuperclass();
            if(t instanceof ParameterizedType)
            {
                Type[] p = ((ParameterizedType) t).getActualTypeArguments();
                clazz = (Class<T>) p[0];
            }
            model = clazz.newInstance();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T getModel()
    {
        return model;
    }

    public int getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(int pageNum)
    {
        this.pageNum = pageNum;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public int getRecordCount()
    {
        return recordCount;
    }

    public void setRecordCount(int recordCount)
    {
        this.recordCount = recordCount;
    }

    public List<?> getRecordList()
    {
        return recordList;
    }

    public void setRecordList(List<?> recordList)
    {
        this.recordList = recordList;
    }

    public JSONObject getResult()
    {
        return result;
    }

    public void setResult(JSONObject result)
    {
        this.result = result;
    }
}