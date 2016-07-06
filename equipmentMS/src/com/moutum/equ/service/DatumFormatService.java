package com.moutum.equ.service;

import com.moutum.equ.domain.DatumFormat;
import com.moutum.equ.dto.PageBean;

/********************************************************************************
 * @Title        : DatumFormatService.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015-3-28 下午05:03:23
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ********************************************************************************/
public interface DatumFormatService
{

    /********************************************************************************
     * 根据查询条件获取PageBean
     * @param pageNum
     * @param pageSize
     * @return
     ********************************************************************************/
    PageBean getPage(int pageNum, int pageSize);

    /********************************************************************************
     * 添加文档格式
     * @param datumFormat
     * @return 1:成功，0:标识重复, -1:操作失败
     ********************************************************************************/
    int save(DatumFormat datumFormat);

    /********************************************************************************
     * 修改文档格式
     * @param datumFormat
     * @return 1:成功，0:标识不存在, -1:操作失败
     ********************************************************************************/
    int modify(DatumFormat datumFormat);

    /********************************************************************************
     * 删除文档格式
     * @param datumFormatId
     * @return 1:成功，0:标识不存在, -1:操作失败
     ********************************************************************************/
    int delete(int datumFormatId);
}