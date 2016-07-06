package com.moutum.equ.dao;

import java.util.List;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.EquipmentDatum;

/**
 * @Title        : EquipmentDatumDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015-3-28 下午08:43:41
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 */
public interface EquipmentDatumDao extends DaoSupport<EquipmentDatum>
{
    
    /********************************************************************************
     * 根据设备ID文档ID查询设备文档关联关系
     * @param equipmentId
     * @param datumId
     * @return
     ********************************************************************************/
    List<EquipmentDatum> select(Long equipmentId, Long datumId);
}