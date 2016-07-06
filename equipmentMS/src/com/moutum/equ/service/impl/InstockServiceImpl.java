package com.moutum.equ.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.dao.InstockDao;
import com.moutum.equ.dao.OperLogDao;
import com.moutum.equ.dao.OutstockDao;
import com.moutum.equ.dao.SpacstockDao;
import com.moutum.equ.dao.TypeDao;
import com.moutum.equ.domain.Instock;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.domain.Outstock;
import com.moutum.equ.domain.Spacstock;
import com.moutum.equ.domain.Type;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.InstockDto;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.service.InstockService;
import com.moutum.equ.util.DateUtil;
import com.moutum.equ.util.SystemConstants;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title : InstockServiceImpl.java
 * @Description :
 * @Author : HuangWei
 * @DateTime : 2015年8月20日 上午10:11:12
 * @Copyright : 2015 Moutum All Rights Reserved
 * @version : V1.0
 ************************************************************************************/
@Service
public class InstockServiceImpl implements InstockService 
{
	@Resource InstockDao instockDao;
	@Resource OutstockDao outstockDao;
	@Resource SpacstockDao spacstockDao;
	@Resource TypeDao typeDao;
	@Resource OperLogDao operLogDao;
	
	@Override
	public PageBean getPage(int pageNum, int pageSize, Map<String,Object> map)
	{
		if(map.get("requestType").equals("out"))
		{
			int instockCount = instockDao.getRecordCount(map);
			List<Instock> instockList = instockDao.getRecordList(pageNum, pageSize, map);
			List<InstockDto> list = new ArrayList<InstockDto>();
			List<Instock> instockListShow = new ArrayList<Instock>();
			List<Outstock> outstockList = outstockDao.getRecordList(map);
			Map<String, Map<String,List<Outstock>>> outstockMap = new HashMap<String, Map<String,List<Outstock>>>(); //Map<sparepartNo,Map<instockTime(String),Outstock>>
			Map<String, Map<String,Integer>> outstockRemainAmount = new HashMap<String, Map<String,Integer>>(); //Map<sparepartNo,Map<instockTime(String),Outstock>>
			
			for(Outstock outstock : outstockList)
			{
				if(null != outstockMap.get(outstock.getSparepartNo()))
				{
					if(null != outstockMap.get(outstock.getSparepartNo()).get(DateUtil.dateToString(outstock.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS)))
					{
						outstockMap.get(outstock.getSparepartNo()).get(DateUtil.dateToString(outstock.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS)).add(outstock);
					}
					else
					{
						List<Outstock> outstockListByInstockTime = new ArrayList<Outstock> ();
						outstockListByInstockTime.add(outstock);
						outstockMap.get(outstock.getSparepartNo()).put(DateUtil.dateToString(outstock.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS), outstockListByInstockTime);
					}
				}
				else
				{
					List<Outstock> outstockListByInstockTime = new ArrayList<Outstock> ();
					outstockListByInstockTime.add(outstock);
					Map<String,List<Outstock>> outstockBySparepartNo = new HashMap<String,List<Outstock>>();
					outstockBySparepartNo.put(DateUtil.dateToString(outstock.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS), outstockListByInstockTime);
					outstockMap.put(outstock.getSparepartNo(), outstockBySparepartNo);
				} 
			}
			
			for(String sparepartNo : outstockMap.keySet())
			{
				for(String intstockTime : outstockMap.get(sparepartNo).keySet())
				{
					int outstockAmount = 0;
					for(Outstock outstock : outstockMap.get(sparepartNo).get(intstockTime))
					{
						outstockAmount += outstock.getOutstockAmount();
					}
					if(null != outstockRemainAmount.get(sparepartNo))
					{
						outstockRemainAmount.get(sparepartNo).put(intstockTime, outstockAmount);
					}
					else
					{
						Map<String,Integer> outstockRemainAmountByInstockTime = new HashMap<String,Integer> ();
						outstockRemainAmountByInstockTime.put(intstockTime, outstockAmount);
						outstockRemainAmount.put(sparepartNo, outstockRemainAmountByInstockTime);
					}
				}
			}
			
			for(Instock instock : instockList )
			{
				if(null != outstockRemainAmount.get(instock.getSparepartNo()))
				{
					if(null != outstockRemainAmount.get(instock.getSparepartNo()).get(DateUtil.dateToString(instock.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS)))
					{
						if(instock.getInstockAmount()>outstockRemainAmount.get(instock.getSparepartNo()).get(DateUtil.dateToString(instock.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS)))
						{
							instockListShow.add(instock);
						}
					}
					else
					{
						instockListShow.add(instock);
					}
				}
				else
				{
					instockListShow.add(instock);
				}
				
			}
			Map<Long, String> typeMap = new HashMap<Long, String>();
			for(Type t : typeDao.findAll())
			{
				typeMap.put(t.getTypeId(), t.getTypeName());
			}
			for(Instock i : instockListShow)
			{
				InstockDto instockDto = new InstockDto();
				instockDto.setInstockId(i.getInstockId());
				instockDto.setSparepartNo(i.getSparepartNo());
				instockDto.setSparepartName(i.getSparepartName());
				instockDto.setSparepartModle(i.getSparepartModle());
				instockDto.setTypeName(typeMap.get(i.getTypeId()));
				instockDto.setSparepartUnit(i.getSparepartUnit());
				instockDto.setSparepartSetplace(i.getSparepartSetplace());
				instockDto.setInstockSparepartReceiver(i.getInstockSparepartReceiver());
				instockDto.setInstockSparepartProvider(i.getInstockSparepartProvider());
				instockDto.setInstockSparepartSupervisor(i.getInstockSparepartSupervisor());
				instockDto.setInstockTime(DateUtil.dateToString(i.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
				instockDto.setInstockAmount(i.getInstockAmount());
				instockDto.setInstockComment(i.getInstockComment());
				if(null != outstockRemainAmount.get(i.getSparepartNo()))
				{
					if(null != outstockRemainAmount.get(i.getSparepartNo()).get(DateUtil.dateToString(i.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS)))
					{
						instockDto.setRemainAmount(i.getInstockAmount() - outstockRemainAmount.get(i.getSparepartNo()).get(DateUtil.dateToString(i.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS)));
					}
					else
					{
						instockDto.setRemainAmount(i.getInstockAmount());
					}
				}
				else
				{
					instockDto.setRemainAmount(i.getInstockAmount());
				}
				list.add(instockDto);
			}
			return new PageBean(pageNum, pageSize, instockCount, list);
		}
		else if(map.get("requestType").equals("zero"))
		{
			int instockCount = instockDao.getRecordCount(map);
			List<Instock> instockList = instockDao.getRecordList(pageNum, pageSize, map);
			List<Instock> instockListShow = new ArrayList<Instock>();
			List<Outstock> outstockList = outstockDao.getRecordList(map);
			Map<String, Map<String,List<Outstock>>> outstockMap = new HashMap<String, Map<String,List<Outstock>>>(); //Map<sparepartNo,Map<instockTime(String),Outstock>>
			Map<String, Map<String,Integer>> outstockRemainAmount = new HashMap<String, Map<String,Integer>>(); //Map<sparepartNo,Map<instockTime(String),Outstock>>
			
			for(Outstock outstock : outstockList)
			{
				if(null != outstockMap.get(outstock.getSparepartNo()))
				{
					if(null != outstockMap.get(outstock.getSparepartNo()).get(DateUtil.dateToString(outstock.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS)))
					{
						outstockMap.get(outstock.getSparepartNo()).get(DateUtil.dateToString(outstock.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS)).add(outstock);
					}
					else
					{
						List<Outstock> outstockListByInstockTime = new ArrayList<Outstock> ();
						outstockListByInstockTime.add(outstock);
						outstockMap.get(outstock.getSparepartNo()).put(DateUtil.dateToString(outstock.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS), outstockListByInstockTime);
					}
				}
				else
				{
					List<Outstock> outstockListByInstockTime = new ArrayList<Outstock> ();
					outstockListByInstockTime.add(outstock);
					Map<String,List<Outstock>> outstockBySparepartNo = new HashMap<String,List<Outstock>>();
					outstockBySparepartNo.put(DateUtil.dateToString(outstock.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS), outstockListByInstockTime);
					outstockMap.put(outstock.getSparepartNo(), outstockBySparepartNo);
				} 
			}
			
			
			for(String sparepartNo : outstockMap.keySet())
			{
				for(String intstockTime : outstockMap.get(sparepartNo).keySet())
				{
					int outstockAmount = 0;
					for(Outstock outstock : outstockMap.get(sparepartNo).get(intstockTime))
					{
						outstockAmount += outstock.getOutstockAmount();
					}
					if(null != outstockRemainAmount.get(sparepartNo))
					{
						outstockRemainAmount.get(sparepartNo).put(intstockTime, outstockAmount);
					}
					else
					{
						Map<String,Integer> outstockRemainAmountByInstockTime = new HashMap<String,Integer> ();
						outstockRemainAmountByInstockTime.put(intstockTime, outstockAmount);
						outstockRemainAmount.put(sparepartNo, outstockRemainAmountByInstockTime);
					}
				}
			}
			
			for(Instock instock : instockList )
			{
				if(null != outstockRemainAmount.get(instock.getSparepartNo()))
				{
					if(null != outstockRemainAmount.get(instock.getSparepartNo()).get(DateUtil.dateToString(instock.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS)))
					{
						if(instock.getInstockAmount()<=outstockRemainAmount.get(instock.getSparepartNo()).get(DateUtil.dateToString(instock.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS)))
						{
							instockListShow.add(instock);
						}
					}
				}
			}
			return new PageBean(pageNum, pageSize, instockCount, econvert(instockListShow));
		}
		else
		{
			int instockCount = instockDao.getRecordCount(map);
			List<Instock> recordList = instockDao.getRecordList(pageNum, pageSize, map);
			return new PageBean(pageNum, pageSize, instockCount, econvert(recordList));
		}
	}

	@Override
    public List<Type> getSelectLists()
    {
        return typeDao.findAll();
    }

	@Override
	public int instock(Instock instock)
	{
		try
        {
            if(null != instockDao.select(instock))
            {
                return 0;
            }
            else
            {
                instockDao.save(instock);
                if(null != spacstockDao.select(instock.getSparepartNo()))
                {
                	Spacstock spacstock = spacstockDao.select(instock.getSparepartNo());
                	spacstock.setSpacstockAmount(spacstock.getSpacstockAmount()+instock.getInstockAmount());
                	spacstockDao.update(spacstock);
                }
                else
                {
                	Spacstock spacstock = new Spacstock();
                	spacstock.setSparepartNo(instock.getSparepartNo());
                	spacstock.setSparepartName(instock.getSparepartName());
                	spacstock.setSpacstockAmount(instock.getInstockAmount());
                	spacstock.setSpacstockMinamount(0);
                	spacstockDao.save(spacstock);
                }
                User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
                
                OperLog operLog = new OperLog();
                operLog.setOperator(loginuser.getLoginAccount());
                operLog.setOperLogTime(new Date());
                operLog.setOperType(SystemConstants.OPERLOG_TYPE_SPART_DELETE);
                operLog.setOperLogContent("添加备品:" + instock.getSparepartNo()+"--"+ DateUtil.dateToString(instock.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
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
    public List<InstockDto> expData(Map<String, Object> map)
    {
        return econvert(instockDao.expData(map));
    }
    
    private List<InstockDto> econvert(List<Instock> inDatas)
    {
        List<InstockDto> list = new ArrayList<InstockDto>();
        //定义类型信息的Map
        
        Map<Long, String> typeMap = new HashMap<Long, String>();
        for(Type t : typeDao.findAll())
        {
            typeMap.put(t.getTypeId(), t.getTypeName());
        }
        for(Instock i : inDatas)
        {
            InstockDto instockDto = new InstockDto();
            instockDto.setInstockId(i.getInstockId());
            instockDto.setSparepartNo(i.getSparepartNo());
            instockDto.setSparepartName(i.getSparepartName());
            instockDto.setSparepartModle(i.getSparepartModle());
            instockDto.setTypeName(typeMap.get(i.getTypeId()));
            instockDto.setSparepartUnit(i.getSparepartUnit());
            instockDto.setSparepartSetplace(i.getSparepartSetplace());
            instockDto.setInstockSparepartReceiver(i.getInstockSparepartReceiver());
            instockDto.setInstockSparepartProvider(i.getInstockSparepartProvider());
            instockDto.setInstockSparepartSupervisor(i.getInstockSparepartSupervisor());
            instockDto.setInstockTime(DateUtil.dateToString(i.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
            instockDto.setInstockAmount(i.getInstockAmount());
            instockDto.setInstockComment(i.getInstockComment());
            instockDto.setRemainAmount(-999);
            list.add(instockDto);
        }
        return list;
    }
}
