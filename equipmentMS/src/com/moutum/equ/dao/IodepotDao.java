package com.moutum.equ.dao;

import java.util.List;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.Iodepot;

/************************************************************************************
 * @Title        : IodepotDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午4:51:33
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface IodepotDao extends DaoSupport<Iodepot>
{

    /********************************************************************************
     * 分页查询符合条件的出入库记录列表
     * @param pageNum
     * @param pageSize
     * @param sparepartNo
     * @return
     ********************************************************************************/
    List<Iodepot> getRecordList(int pageNum, int pageSize, String sparepartNo);
    
    /********************************************************************************
     * 查询符合条件的出入库记录条数
     * @param sparepartNo
     * @return
     ********************************************************************************/
    int getRecordCount(String sparepartNo);
    
}