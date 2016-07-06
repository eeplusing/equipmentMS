package com.moutum.equ.service;

import java.util.List;
import java.util.Map;

import com.moutum.equ.domain.Instock;
import com.moutum.equ.domain.Type;
import com.moutum.equ.dto.InstockDto;
import com.moutum.equ.dto.PageBean;

/************************************************************************************
 * @Title        : InstockService.java
 * @Description : 
 * @Author       : HuangWei
 * @DateTime     : 2015年8月20日 上午9:54:30
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface InstockService 
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
	PageBean getPage(int pageNum, int pageSize, Map<String,Object> map);

	/********************************************************************************
     * 获取所有类型的信息列表
     * @return
     ********************************************************************************/
    List<Type> getSelectLists();

    /********************************************************************************
     * 备品入库
     * @param createInstock
     * @return 1:成功, 0:备品编号重复, -1:操作失败
     ********************************************************************************/
	int instock(Instock createInstock);

	/********************************************************************************
	 * 根据条件Map集合查询符合条件的入库记录信息
	 * @param map
	 * @return
	 ********************************************************************************/
    List<InstockDto> expData(Map<String, Object> map);
}
