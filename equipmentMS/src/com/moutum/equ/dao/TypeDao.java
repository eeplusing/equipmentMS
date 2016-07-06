package com.moutum.equ.dao;

import java.util.List;
import java.util.Map;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.Type;

/************************************************************************************
 * @Title        : TypeDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月17日 下午4:54:03
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface TypeDao extends DaoSupport<Type>
{

    /********************************************************************************
     * 根据名称和父类ID查询类型
     * @param type
     * @return
     ********************************************************************************/
    Type select(Type type);

    /********************************************************************************
     * 查询符合条件的类型数量
     * @param map
     * @return
     ********************************************************************************/
    int getRecordCount(Map<String, Object> map);

    /********************************************************************************
     * 分页查询符合条件的类型信息列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     ********************************************************************************/
    List<Type> getRecordList(int pageNum, int pageSize, Map<String, Object> map);

}