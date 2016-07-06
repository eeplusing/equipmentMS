package com.moutum.equ.service;

import com.moutum.equ.dto.PageBean;

/************************************************************************************
 * @Title        : SpacstockService.java
 * @Description : 
 * @Author       : HuangWei
 * @DateTime     : 2015年8月21日 下午17:43:30
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface SpacstockService 
{

	
	/********************************************************************************
     * 获取PageBean
     * @param pageNum
     * @param pageSize
     * @return
     ********************************************************************************/
	PageBean getPage(int pageNum, int pageSize);

	/********************************************************************************
     * 更新库存下限
     * @param spacstockId
     * @param minamount
     * @return 1:成功, 0:失败
     ********************************************************************************/
	int modify(long spacstockId, int minamount);
	
	/********************************************************************************
     *显示预警库存
     * @param typeId
     * @param stockMinamount
     * @param modle
     * @return 1:成功, 0:失败
     ********************************************************************************/
	PageBean getPage(int pageNum, int pageSize, String modle);

}
