package com.moutum.equ.service;

import java.util.List;

import com.moutum.equ.domain.UseState;
import com.moutum.equ.dto.PageBean;

/************************************************************************************
 * @Title        : UseStateService.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月26日 下午2:29:56
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface UseStateService
{
    /********************************************************************************
     * 根据查询条件获取PageBean
     * @param pageNum
     * @param pageSize
     * @return
     ********************************************************************************/
    PageBean getPage(int pageNum, int pageSize);

    /********************************************************************************
     * 添加状态类型
     * @param useState
     * @return 1:成功，0:标识重复, -1:操作失败
     ********************************************************************************/
    int save(UseState useState);

    /********************************************************************************
     * 修改状态类型
     * @param useState
     * @return 1:成功，0:标识不存在, -1:操作失败
     ********************************************************************************/
    int modify(UseState useState);

    /********************************************************************************
     * 删除状态类型
     * @param id
     * @return 1:成功，0:标识不存在, -1:操作失败
     ********************************************************************************/
    int delete(int id);

    List<UseState> findAll();
}

