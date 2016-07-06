package com.moutum.equ.dao;

import java.util.List;
import java.util.Map;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.Datum;

/************************************************************************************
 * @Title        : DatumDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午4:44:01
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface DatumDao extends DaoSupport<Datum>
{
    /********************************************************************************
     * 根据文档路径,文档名查询文档
     * @param datumName
     * @param datumPath
     * @return
     ********************************************************************************/
    Datum select(String datumName, String datumPath);
    /********************************************************************************
     * 查询符合条件的文档数量
     * @param map
     * @param datumIds
     * @return
     ********************************************************************************/
    int getRecordCount(Map<String, Object> map, Long[] datumIds);
    
    /********************************************************************************
     * 分页查询符合条件的文档信息列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @param datumIds
     * @return
     ********************************************************************************/
    List<Datum> getRecordList(int pageNum, int pageSize, Map<String, Object> map, Long[] datumIds);
    
    /********************************************************************************
     * 查询符合条件的文档数量
     * @param equipmentId
     * @return
     ********************************************************************************/
    int getRecordCount(long equipmentId);
    
    /********************************************************************************
     * 分页查询符合条件的文档信息列表
     * @param pageNum
     * @param pageSize
     * @param equipmentId
     * @return
     ********************************************************************************/
    List<Datum> getRecordList(int pageNum, int pageSize, long equipmentId);
    
    /********************************************************************************
     * 根据设备ID查询设备的关联图片
     * @param equipmentId
     * @return
     ********************************************************************************/
    List<Datum> selectImage(long equipmentId);
}