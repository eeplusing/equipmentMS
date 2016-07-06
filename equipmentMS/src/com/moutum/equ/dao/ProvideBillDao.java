package com.moutum.equ.dao;

import java.util.List;
import java.util.Map;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.ProvideBill;

/************************************************************************************
 * @Title        : ProvideBillDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年8月19日 上午10:36:23
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface ProvideBillDao extends DaoSupport<ProvideBill>
{

    List<ProvideBill> getAll(Map<String, Object> map);

    /********************************************************************************
     * 根据批次编号查询明细信息
     * @param equipmentNo
     ********************************************************************************/
    ProvideBill getByNo(String equipmentNo);

}