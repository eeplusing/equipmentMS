package com.moutum.equ.service;

import com.moutum.equ.domain.DatumType;
import com.moutum.equ.dto.PageBean;

/**
 * @Title        : DatumTypeService.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015-3-28 下午04:39:26
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 */
public interface DatumTypeService
{

    /********************************************************************************
     * 根据查询条件获取PageBean
     * @param pageNum
     * @param pageSize
     * @return
     ********************************************************************************/
    PageBean getPage(int pageNum, int pageSize);

    /********************************************************************************
     * 添加文档类型
     * @param datumType
     * @return 1:成功，0:标识重复, -1:操作失败
     ********************************************************************************/
    int save(DatumType datumType);

    /********************************************************************************
     * 修改文档类型
     * @param datumType
     * @return 1:成功，0:标识不存在, -1:操作失败
     ********************************************************************************/
    int modify(DatumType datumType);

    /********************************************************************************
     * 删除文档类型
     * @param datumTypeId
     * @return 1:成功，0:标识不存在, -1:操作失败
     ********************************************************************************/
    int delete(int datumTypeId);
}

