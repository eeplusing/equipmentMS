package com.moutum.equ.service;

import java.util.List;
import java.util.Map;

import com.moutum.equ.domain.Outstock;
import com.moutum.equ.dto.OutstockDto;
import com.moutum.equ.dto.PageBean;

/************************************************************************************
 * @Title        : OutstockService.java
 * @Description : 
 * @Author       : HuangWei
 * @DateTime     : 2015年8月20日 上午9:54:30
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface OutstockService 
{

	/********************************************************************************
     * 根据查询条件获取PageBean
     * @param pageNum
     * @param pageSize
     * @param sparepartNo
     * @param instockTimeStart
     * @param instockTimeEnd
     * @return
     ********************************************************************************/
	PageBean getPage(int pageNum, int pageSize, Map<String, Object> map);

	/********************************************************************************
     * 出库操作
     * @param outstockmap
     * @return
     ********************************************************************************/
	List<Outstock> outstock(Map<String, String> outstockmap);

	/********************************************************************************
     * 出库入库关联
     * @param InstockId
     * @param OutstockId
     * @return
     ********************************************************************************/
	public int releSpart(long InstockId, long OutstockId);

	/********************************************************************************
     * 根据条件Map集合查询符合条件的出库记录信息
     * @param map
     * @return
     ********************************************************************************/
    List<OutstockDto> expData(Map<String, Object> map);
}
