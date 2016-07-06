package com.moutum.equ.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.dao.DatumDao;
import com.moutum.equ.dao.DatumFormatDao;
import com.moutum.equ.dao.DatumTypeDao;
import com.moutum.equ.dao.EquipmentDatumDao;
import com.moutum.equ.dao.OperLogDao;
import com.moutum.equ.domain.Datum;
import com.moutum.equ.domain.DatumFormat;
import com.moutum.equ.domain.DatumType;
import com.moutum.equ.domain.EquipmentDatum;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.DatumDto;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.service.DatumService;
import com.moutum.equ.util.SystemConstants;
import com.opensymphony.xwork2.ActionContext;

/********************************************************************************
 * @Title        : DatumServiceImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015-3-28 下午09:52:36
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ********************************************************************************/
@Service
public class DatumServiceImpl implements DatumService
{
    @Resource DatumDao datumDao;
    
    @Resource EquipmentDatumDao equipmentDatumDao;
    
    @Resource DatumFormatDao datumFormatDao;
    
    @Resource DatumTypeDao datumTypeDao;
    
    @Resource OperLogDao operLogDao;
    
    
    
    @Override
    public int delete(int datumId)
    {
        try
        {
            Datum datum = datumDao.getById((long)datumId);
            if(datum != null)
            {
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                File file = new File(datum.getDatumPath());
                file.delete();
                datumDao.delete((long)datumId);
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_DATUM_DELETE);
                operLog.setOperLogContent("删除文档：" + datum.getDatumPath());
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
    public PageBean getPage(int pageNum, int pageSize, Map<String, Object> map, String requestType)
    {
        Long[] datumIds = null;
        if(requestType.equals("rele"))
        {
            List<EquipmentDatum> eds = equipmentDatumDao.select((Long)map.get("equipmentId"), null);
            if(eds.size() > 0){
                datumIds = new Long[eds.size()];
                for(int i = 0; i < eds.size(); i++)
                {
                    datumIds[i] = eds.get(i).getDatumId();
                }
            }
        }
        int recordCount = datumDao.getRecordCount(map, datumIds);
        List<Datum> recordList = datumDao.getRecordList(pageNum, pageSize, map, datumIds);
        
        List<DatumDto> list = new ArrayList<DatumDto>();
        Map<Long, String> typeMap = new HashMap<Long, String>();
        for(DatumType dt : datumTypeDao.findAll())
        {
            typeMap.put(dt.getDatumTypeId(), dt.getDatumTypeName());
        }
        
        Map<Long, String> formatMap = new HashMap<Long, String>();
        for(DatumFormat df : datumFormatDao.findAll())
        {
            formatMap.put(df.getDatumFormatId(), df.getDatumFormatName());
        }
        
        for(Datum datum : recordList)
        {
            DatumDto datumDto = new DatumDto();
            datumDto.setDatumId(datum.getDatumId());
            datumDto.setDatumName(datum.getDatumName());
            datumDto.setDatumPath(datum.getDatumPath());
            datumDto.setDatumTypeName(typeMap.get(datum.getDatumTypeId()));
            datumDto.setDatumFormatName(formatMap.get(datum.getDatumFormatId()));
            list.add(datumDto);
        }
        return new PageBean(pageNum, pageSize, recordCount, list);
    }

    @Override
    public int modify(Datum datum)
    {
        try
        {
            if(datumDao.getById(datum.getDatumId()) != null)
            {
                datumDao.update(datum);
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
    public int save(Datum datum, String[] es)
    {
        try
        {
            if(null != datumDao.select(datum.getDatumName(), datum.getDatumPath()))
            {
                return 0;
            }
            else
            {
                datumDao.save(datum);
                Datum d = datumDao.select(datum.getDatumName(), datum.getDatumPath());
                if(null != es && es.length > 0)
                {
                    for(String eid : es)
                    {
                        EquipmentDatum equipmentDatum = new EquipmentDatum();
                        equipmentDatum.setEquipmentId(Long.parseLong(eid));
                        equipmentDatum.setDatumId(d.getDatumId());
                        equipmentDatumDao.save(equipmentDatum);
                    }
                }
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_DATUM_UPLOAD);
                operLog.setOperLogContent("上传文档：" + datum.getDatumPath());
                operLogDao.save(operLog);
                return 1;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Map<String, List<?>> getSelectLists()
    {
        Map<String, List<?>> map = new HashMap<String, List<?>>();
        map.put("types", datumTypeDao.findAll());
        map.put("formats", datumFormatDao.findAll());
        return map;
    }

    @Override
    public boolean select(String datumName, String datumPath)
    {
        if(null != datumDao.select(datumName, datumPath))
        {
            return true;
        }
        return false;
    }

    @Override
    public PageBean getPage(int pageNum, int pageSize, long equipmentId)
    {
        int recordCount = datumDao.getRecordCount(equipmentId);
        List<Datum> recordList = datumDao.getRecordList(pageNum, pageSize, equipmentId);
        List<DatumDto> list = new ArrayList<DatumDto>();
        
        Map<Long, String> typeMap = new HashMap<Long, String>();
        for(DatumType dt : datumTypeDao.findAll())
        {
            typeMap.put(dt.getDatumTypeId(), dt.getDatumTypeName());
        }
        
        Map<Long, String> formatMap = new HashMap<Long, String>();
        for(DatumFormat df : datumFormatDao.findAll())
        {
            formatMap.put(df.getDatumFormatId(), df.getDatumFormatName());
        }
        
        for(Datum datum : recordList)
        {
            DatumDto datumDto = new DatumDto();
            datumDto.setDatumId(datum.getDatumId());
            datumDto.setDatumName(datum.getDatumName());
            datumDto.setDatumPath(datum.getDatumPath());
            datumDto.setDatumTypeName(typeMap.get(datum.getDatumTypeId()));
            datumDto.setDatumFormatName(formatMap.get(datum.getDatumFormatId()));
            list.add(datumDto);
        }
        return new PageBean(pageNum, pageSize, recordCount, list);
    }

    @Override
    public Datum select(long datumId)
    {
        return datumDao.getById(datumId);
    }

    @Override
    public List<Datum> selectImage(long equipmentId)
    {
        return datumDao.selectImage(equipmentId);
    }
}