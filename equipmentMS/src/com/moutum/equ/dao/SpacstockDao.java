package com.moutum.equ.dao;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.Spacstock;


/************************************************************************************
 * @Title        : SpacstockDao.java
 * @Description : 
 * @Author       : HuangWei
 * @DateTime     : 2015年8月21日 下午17:15:30
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface SpacstockDao extends DaoSupport<Spacstock>
{

	/********************************************************************************
     * 根据备品编号查询库存
     * @param spacstock
     * @return
     ********************************************************************************/
	Spacstock select(String spacstockNo);

	/********************************************************************************
     * 更新库存下限
     * @param spacstockId
     * @param minamount
     ********************************************************************************/
	void modify(long spacstockId, int minamount);

}
