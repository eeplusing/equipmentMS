package com.moutum.equ.dao;

import java.util.List;
import java.util.Map;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.Instock;

/************************************************************************************
 * @Title        : InstockDao.java
 * @Description : 
 * @Author       : HuangWei
 * @DateTime     : 2015年8月20日 上午10:15:30
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface InstockDao extends DaoSupport<Instock>
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
	List<Instock> getRecordList(int pageNum, int pageSize, Map<String, Object> map);

	/********************************************************************************
     * 根据备品编号查询备品
     * @param createInstock
     * @return
     ********************************************************************************/
	Instock select(Instock instock);

	/********************************************************************************
     * 根据条件Map集合查询符合条件的入库记录信息
     * @param map
     * @return
     ********************************************************************************/
    List<Instock> expData(Map<String, Object> map);

}
