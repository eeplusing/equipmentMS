package com.moutum.equ.dao;

import java.util.List;
import java.util.Map;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.Outstock;

/************************************************************************************
 * @Title        : OutstockDao.java
 * @Description : 
 * @Author       : HuangWei
 * @DateTime     : 2015年8月20日 上午10:15:30
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface OutstockDao extends DaoSupport<Outstock>
{

	/********************************************************************************
     * 查询符合条件的备品数量
     * @param map
     * @return
     ********************************************************************************/
	int getRecordCount(Map<String, Object> map);

	/********************************************************************************
	 * 查询符合条件的备品列表
	 * @param pageSize 
	 * @param pageNum 
	 * @param map
	 * @return
	 ********************************************************************************/
	List<Outstock> getRecordList(int pageNum, int pageSize,Map<String, Object> map);

	/********************************************************************************
     * 根据备品编号和备品入库时间查询备品
     * @param outstock
     * @return
     ********************************************************************************/
	Outstock select(Outstock outstock);

	/********************************************************************************
	 * 查询符合条件的备品列表
	 * @param map
	 * @return
	 ********************************************************************************/
	List<Outstock> getRecordList(Map<String, Object> map);

	/********************************************************************************
     * 根据条件Map集合查询符合条件的出库记录信息
     * @param map
     * @return
     ********************************************************************************/
    List<Outstock> expData(Map<String, Object> map);

}
