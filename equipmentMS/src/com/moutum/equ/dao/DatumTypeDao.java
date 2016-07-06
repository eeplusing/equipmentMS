package com.moutum.equ.dao;

import java.util.List;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.DatumType;

/************************************************************************************
 * @Title        : DatumTypeDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午4:45:03
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface DatumTypeDao extends DaoSupport<DatumType>
{

    /********************************************************************************
     * 根据文档类型名查询文档类型是否存在
     * @param datumtypeName
     * @return
     ********************************************************************************/
    DatumType select(String datumtypeName);

    /********************************************************************************
     * 查询符合条件的文档类型数量
     * @return
     ********************************************************************************/
    int getRecordCount();

    /********************************************************************************
     * 分页查询符合条件的文档类型信息列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     ********************************************************************************/
    List<DatumType> getRecordList(int pageNum, int pageSize);
    
}