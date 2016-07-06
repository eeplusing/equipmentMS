package com.moutum.equ.dao;

import java.util.List;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.DatumFormat;

/************************************************************************************
 * @Title        : DatumFormatDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午4:44:36
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface DatumFormatDao extends DaoSupport<DatumFormat>
{

    /********************************************************************************
     * 根据文档格式名查询文档格式是否存在
     * @param datumFormatName
     * @return
     ********************************************************************************/
    DatumFormat select(String datumFormatName);

    /********************************************************************************
     * 查询符合条件的文档格式数量
     * @return
     ********************************************************************************/
    int getRecordCount();
    
    /********************************************************************************
     * 分页查询符合条件的文档格式信息列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     ********************************************************************************/
    List<DatumFormat> getRecordList(int pageNum, int pageSize);
}