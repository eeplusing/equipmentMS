package com.moutum.equ.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.service.EquipmentService;
import com.moutum.equ.util.DateUtil;
import com.moutum.equ.util.SystemConstants;
import com.moutum.equ.dao.EquipmentDao;
import com.moutum.equ.dao.EquipmentDatumDao;
import com.moutum.equ.dao.OperLogDao;
import com.moutum.equ.dao.TypeDao;
import com.moutum.equ.dao.UseStateDao;
import com.moutum.equ.domain.Equipment;
import com.moutum.equ.domain.EquipmentDatum;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.domain.Type;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.EquipmentDto;
import com.moutum.equ.dto.PageBean;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title        : EquipmentServiceImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月26日 下午3:41:37
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Service
public class EquipmentServiceImpl implements EquipmentService
{
    @Resource EquipmentDao equipmentDao;
    
    @Resource TypeDao typeDao;
    
    @Resource UseStateDao useStateDao;
    
    @Resource EquipmentDatumDao equipmentDatumDao;
    
    @Resource OperLogDao operLogDao;
    
    @Override
    public PageBean getPage(int pageNum, int pageSize, Map<String, Object> map)
    {
        
        int recordCount = equipmentDao.getRecordCount(map);
        List<Equipment> recordList = equipmentDao.getRecordList(pageNum, pageSize, map);
        List<EquipmentDto> list = new ArrayList<EquipmentDto>();
        //定义类型信息的Map
        Map<Long, String> typeMap = new HashMap<Long, String>();
        for(Type t : typeDao.findAll())
        {
            typeMap.put(t.getTypeId(), t.getTypeName());
        }
        
        for(Equipment equ : recordList)
        {
            EquipmentDto ed = new EquipmentDto();
            ed.setEquipmentId(equ.getEquipmentId());
            ed.setEquipmentNo(equ.getEquipmentNo());
            ed.setEquipmentName(equ.getEquipmentName());
            ed.setEquipmentModle(equ.getEquipmentModle());
            ed.setTypeName(equ.getTypeName());
            ed.setEquipmentProducer(equ.getEquipmentProducer());
            ed.setEquipmentSupplier(equ.getEquipmentSupplier());
            ed.setEquipmentBuyTime(DateUtil.dateToString(equ.getEquipmentBuyTime(),DateUtil.YYYY_MM_DD));
            ed.setEquipmentBuyType(equ.getEquipmentBuyType());
            ed.setEquipmentRecipient(equ.getEquipmentRecipient());
            ed.setEquipmentProvider(equ.getEquipmentProvider());
            ed.setEquipmentDirector(equ.getEquipmentDirector());
            ed.setUseState(equ.getUseState());
            ed.setStartDate(DateUtil.dateToString(equ.getEquipmentStartDate(),DateUtil.YYYY_MM_DD));
            ed.setEquipmentUseYears(equ.getEquipmentUseYears());
            ed.setEquipmentMonetaryAmount(equ.getEquipmentMonetaryAmount());
            ed.setAmount(equ.getAmount());
            ed.setAreaCode(equ.getAreaCode());
            ed.setRemark(equ.getRemark());
            list.add(ed);
        }
        return new PageBean(pageNum, pageSize, recordCount, list);
    }

    @Override
    public Map<String, List<?>> getSelectLists()
    {
        Map<String, List<?>> map = new HashMap<String, List<?>>();
        map.put("types", typeDao.findAll());
        map.put("useStates", useStateDao.findAll());
        return map;
    }

    @Override
    public Equipment select(int id)
    {
        return equipmentDao.getById((long)id);
    }

    @Override
    public int save(Equipment equipment)
    {
        try
        {
            if(equipmentDao.select(equipment) == null)
            {
                equipmentDao.save(equipment);
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_EQU_SAVE);
                operLog.setOperLogContent("添加设备：" + equipment.getEquipmentNo() + "|" + equipment.getEquipmentName());
                operLogDao.save(operLog);
                return 1;
            }
            else
            {
                return 0;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public int modify(Equipment equipment)
    {
        try
        {
            if(equipmentDao.getById(equipment.getEquipmentId()) != null)
            {
                equipmentDao.update(equipment);
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_EQU_UPDATE);
                operLog.setOperLogContent("修改设备信息：" + equipment.getEquipmentNo() + "|" + equipment.getEquipmentName());
                operLogDao.save(operLog);
                return 1;
            }
            else
            {
                return 0;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public List<String> releDatum(long equipmentId, String[] idArray)
    {
        List<String> result = new ArrayList<String>();
        for(String s : idArray)
        {
            try
            {
                EquipmentDatum equipmentDatum = new EquipmentDatum();
                equipmentDatum.setEquipmentId(equipmentId);
                equipmentDatum.setDatumId(Long.parseLong(s));
                equipmentDatumDao.save(equipmentDatum);
                
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
                operLog.setOperLogContent("添加关联文档：" + equipmentId + "|" + s);
                operLogDao.save(operLog);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public Equipment select(String equipmentNo)
    {
        Equipment equipment = new Equipment();
        equipment.setEquipmentNo(equipmentNo);
        return equipmentDao.select(equipment);
    }

    @Override
    public EquipmentDto toEqudto(Equipment equ)
    {
      //定义类型信息的Map
        Map<Long, String> typeMap = new HashMap<Long, String>();
        for(Type t : typeDao.findAll())
        {
            typeMap.put(t.getTypeId(), t.getTypeName());
        }
        
        EquipmentDto ed = new EquipmentDto();
        ed.setEquipmentId(equ.getEquipmentId());
        ed.setEquipmentNo(equ.getEquipmentNo());
        ed.setEquipmentName(equ.getEquipmentName());
        ed.setEquipmentModle(equ.getEquipmentModle());
        ed.setTypeName(equ.getTypeName());
        ed.setEquipmentProducer(equ.getEquipmentProducer());
        ed.setEquipmentSupplier(equ.getEquipmentSupplier());
        ed.setEquipmentBuyTime(DateUtil.dateToString(equ.getEquipmentBuyTime(),DateUtil.YYYY_MM_DD));
        ed.setEquipmentBuyType(equ.getEquipmentBuyType());
        ed.setEquipmentRecipient(equ.getEquipmentRecipient());
        ed.setEquipmentProvider(equ.getEquipmentProvider());
        ed.setEquipmentDirector(equ.getEquipmentDirector());
        ed.setUseState(equ.getUseState());
        ed.setStartDate(DateUtil.dateToString(equ.getEquipmentStartDate(),DateUtil.YYYY_MM_DD));
        ed.setEquipmentUseYears(equ.getEquipmentUseYears());
        ed.setEquipmentMonetaryAmount(equ.getEquipmentMonetaryAmount());
        ed.setAmount(equ.getAmount());
        ed.setAreaCode(equ.getAreaCode());
        ed.setRemark(equ.getRemark());
        return ed;
    }

    @Override
    public List<Equipment> getAll()
    {
        return equipmentDao.findAll();
    }
}