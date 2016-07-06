package com.moutum.equ.dao;

import java.util.List;
import java.util.Map;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.Equipment;

/************************************************************************************
 * @Title        : EquipmentDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午4:46:21
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface EquipmentDao extends DaoSupport<Equipment>
{
    /********************************************************************************
     * 查询符合条件的设备数量
     * @param map
     * @return
     ********************************************************************************/
    int getRecordCount(Map<String, Object> map);

    /********************************************************************************
     * 分页查询符合条件的设备信息列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     ********************************************************************************/
    List<Equipment> getRecordList(int pageNum, int pageSize, Map<String, Object> map);

    /********************************************************************************
     * 根据设备编号查询设备
     * @param equipment
     * @return
     ********************************************************************************/
    Equipment select(Equipment equipment);
}