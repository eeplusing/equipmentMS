package com.moutum.equ.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.dao.InstockandoutstockDao;
import com.moutum.equ.dao.OperLogDao;
import com.moutum.equ.dao.OutstockDao;
import com.moutum.equ.dao.SpacstockDao;
import com.moutum.equ.dao.TypeDao;
import com.moutum.equ.domain.Instockandoutstock;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.domain.Outstock;
import com.moutum.equ.domain.Spacstock;
import com.moutum.equ.domain.Type;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.OutstockDto;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.service.OutstockService;
import com.moutum.equ.util.DateUtil;
import com.moutum.equ.util.SystemConstants;
import com.opensymphony.xwork2.ActionContext;


/************************************************************************************
 * @Title : OutstockServiceImpl.java
 * @Description :
 * @Author : HuangWei
 * @DateTime : 2015年8月20日 上午10:11:12
 * @Copyright : 2015 Moutum All Rights Reserved
 * @version : V1.0
 ************************************************************************************/
@Service
public class OutstockServiceImpl implements OutstockService
{

	@Resource OutstockDao outstockDao;
	@Resource SpacstockDao spacstockDao;
	@Resource InstockandoutstockDao InstockandstockDao;
	@Resource TypeDao typeDao;
	@Resource OperLogDao operLogDao;
	
	@Override
	public PageBean getPage(int pageNum, int pageSize, Map<String, Object> map)
	{
		int recordCount = outstockDao.getRecordCount(map);
		List<Outstock> recordList = outstockDao.getRecordList(pageNum, pageSize, map);
        return new PageBean(pageNum, pageSize, recordCount, convert(recordList));
	}

	@Override
	public List<Outstock> outstock(Map<String, String> outstockmap) 
	{
	    try
        {
	        List<Outstock> faileList = new ArrayList<Outstock>();//执行出库操作失败的物品集合
	        Map< String ,Long > typeMap = new HashMap<String ,Long>();
	        for(Type t : typeDao.findAll())
	        {
	            typeMap.put(t.getTypeName() ,t.getTypeId());
	        }
	        
	        outstockmap.put("TypeId", typeMap.get(outstockmap.get("TypeName")).toString());
	        
	        Outstock outstock = new Outstock();
	        outstock.setSparepartNo(outstockmap.get("SparepartNo"));
	        outstock.setSparepartName(outstockmap.get("SparepartName"));
	        outstock.setSparepartModle(outstockmap.get("SparepartModle"));
	        outstock.setTypeId(Long.parseLong(outstockmap.get("TypeId")));
	        outstock.setSparepartUnit(outstockmap.get("SparepartUnit"));
	        outstock.setOutstockSparepartReceiver(outstockmap.get("OutstockSparepartReceiver"));
	        outstock.setOutstockSparepartProvider(outstockmap.get("OutstockSparepartProvider"));
	        outstock.setInstockTime(DateUtil.stringToDate(outstockmap.get("InstockTime"),DateUtil.YYYY_MM_DD_HH_MM_SS));
	        outstock.setOutstockTime(DateUtil.stringToDate(outstockmap.get("OutstockTime"),DateUtil.YYYY_MM_DD_HH_MM_SS));
	        outstock.setSparepartUseplace(outstockmap.get("SparepartUseplace"));
	        outstock.setOutstockAmount(Integer.parseInt(outstockmap.get("OutstockAmount")));
	        outstock.setRemainAmount(Integer.parseInt(outstockmap.get("Remainnum")));
	        outstock.setInStockComment(outstockmap.get("InstockComment"));
	        outstock.setOutStockComment(outstockmap.get("OutStockComment"));
	        
	        if(null == spacstockDao.select(outstock.getSparepartNo()))
	        {
	            faileList.add(outstock);
	        }
	        else
	        {
	            try 
	            {
	                Spacstock spacstock = spacstockDao.select(outstock.getSparepartNo());
	                spacstock.setSpacstockAmount(spacstock.getSpacstockAmount() - outstock.getOutstockAmount());
	                spacstockDao.update(spacstock);
	                outstockDao.save(outstock);
	                Outstock outstock1 = outstockDao.select(outstock);
	                releSpart(Long.parseLong(outstockmap.get("InstockId")), outstock1.getOutstockId());
	                
	            } 
	            catch (Exception e) 
	            {
	                faileList.add(outstock);
	            }
	        }
	        return faileList;
        }
        catch (Exception e)
        {
            return null;
        }
		
	}

	@Override
	public int releSpart(long InstockId, long OutstockId) 
	{
		try 
		{
			Instockandoutstock instockandstock = new Instockandoutstock();
			instockandstock.setInstockId(InstockId);
			instockandstock.setOutstockId(OutstockId);
			InstockandstockDao.save(instockandstock);
			
			User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            OperLog operLog = new OperLog();
            operLog.setOperator(loginuser.getLoginAccount());
            operLog.setOperLogTime(new Date());
            operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
            operLog.setOperLogContent("添加关联备品：" + InstockId + "|" + OutstockId);
            operLogDao.save(operLog);
		} 
		catch (Exception e) 
		{
             e.printStackTrace();
		}
		return 1;
	}

    @Override
    public List<OutstockDto> expData(Map<String, Object> map)
    {
        return convert(outstockDao.expData(map));
    }
    
    private List<OutstockDto> convert(List<Outstock> outDatas)
    {
        List<OutstockDto> list = new ArrayList<OutstockDto>();
        //定义类型信息的Map
       
       Map<Long, String> typeMap = new HashMap<Long, String>();
       for(Type t : typeDao.findAll())
       {
           typeMap.put(t.getTypeId(), t.getTypeName());
       }
       for(Outstock i : outDatas)
       {
           OutstockDto outstockDto = new OutstockDto();
           outstockDto.setOutstockId(i.getOutstockId());
           outstockDto.setSparepartNo(i.getSparepartNo());
           outstockDto.setSparepartName(i.getSparepartName());
           outstockDto.setSparepartModle(i.getSparepartModle());
           outstockDto.setTypeName(typeMap.get(i.getTypeId()));
           outstockDto.setSparepartUnit(i.getSparepartUnit());
           outstockDto.setOutstockSparepartReceiver(i.getOutstockSparepartReceiver());
           outstockDto.setOutstockSparepartProvider(i.getOutstockSparepartProvider());
           outstockDto.setInstockTime(DateUtil.dateToString(i.getInstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
           outstockDto.setOutstockTime(DateUtil.dateToString(i.getOutstockTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
           outstockDto.setSparepartUseplace(i.getSparepartUseplace());
           outstockDto.setOutstockAmount(i.getOutstockAmount());
           outstockDto.setRemainAmount(i.getRemainAmount());
           outstockDto.setOutStockComment(i.getOutStockComment());
           list.add(outstockDto);
       }
       return list;
    }
}
