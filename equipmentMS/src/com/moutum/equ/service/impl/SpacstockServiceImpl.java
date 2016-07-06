package com.moutum.equ.service.impl;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.dao.OperLogDao;
import com.moutum.equ.dao.SpacstockDao;
import com.moutum.equ.domain.OperLog;
import com.moutum.equ.domain.Spacstock;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.service.SpacstockService;
import com.moutum.equ.util.QueryHelper;
import com.moutum.equ.util.SystemConstants;
import com.opensymphony.xwork2.ActionContext;

/************************************************************************************
 * @Title : SpacstockServiceImpl.java
 * @Description :
 * @Author : HuangWei
 * @DateTime : 2015年8月21日 下午17:45:12
 * @Copyright : 2015 Moutum All Rights Reserved
 * @version : V1.0
 ************************************************************************************/
@Service
public class SpacstockServiceImpl implements SpacstockService 
{
	
	@Resource SpacstockDao spacstockDao;
    @Resource OperLogDao operLogDao;

	@Override
	public PageBean getPage(int pageNum, int pageSize) 
	{
		QueryHelper queryHelper = new QueryHelper(Spacstock.class,"s");
		PageBean pageBean = spacstockDao.getPageBean(pageNum, pageSize, queryHelper);
        return pageBean;
	}

	@Override
	public int modify(long spacstockId, int minamount) 
	{
		try
        {
            spacstockDao.modify(spacstockId, minamount);
            
            User loginuser = (User)ActionContext.getContext().getSession().get("login_user");
            
            OperLog operLog = new OperLog();
            operLog.setOperator(loginuser.getLoginAccount());
            operLog.setOperLogTime(new Date());
            operLog.setOperType(SystemConstants.OPERLOG_TYPE_OTHER);
            operLog.setOperLogContent("设置备品库存的上下限-备品:" + spacstockId +  "|下限:" + minamount);
            operLogDao.save(operLog);
            
            return 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
	}
	@Override
	public PageBean getPage(int pageNum, int pageSize, String modle) 
	{
		QueryHelper queryHelper = new QueryHelper(Spacstock.class,"s");
		queryHelper.addCondition("s.spacstockMinamount != 0 ");
		queryHelper.addCondition("s.spacstockMinamount >= s.spacstockAmount");
		PageBean pageBean = spacstockDao.getPageBean(pageNum, pageSize, queryHelper);
		return pageBean;
	}

}
