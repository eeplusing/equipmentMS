package com.moutum.equ.dao;

import java.util.List;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.UseState;

/************************************************************************************
 * @Title        : UseStateDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午4:54:42
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface UseStateDao extends DaoSupport<UseState>
{
    /********************************************************************************
     * 根据状态名查询状态是否存在
     * @param name
     * @return
     ********************************************************************************/
    UseState select(String name);
    /********************************************************************************
     * 查询符合条件的状态数量
     * @param map
     * @return
     ********************************************************************************/
    int getRecordCount();

    /********************************************************************************
     * 分页查询符合条件的状态信息列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     ********************************************************************************/
    List<UseState> getRecordList(int pageNum, int pageSize);

}