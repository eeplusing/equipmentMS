package com.moutum.equ.service;

import java.util.List;
import java.util.Map;

import com.moutum.equ.domain.Right;
import com.moutum.equ.dto.PageBean;

/************************************************************************************
 * @Title        : RightService.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 下午4:01:21
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface RightService
{
    /********************************************************************************
     * 根据角色ID获取该角色所拥有的权限列表
     * @param roleId
     * @return
     ********************************************************************************/
    Map<String, String> getFunctionByRole(long roleId);
    
    /********************************************************************************
     * 根据查询条件获取PageBean
     * @param pageNum
     * @param pageSize
     * @return
     ********************************************************************************/
    PageBean getPage(int pageNum, int pageSize);

    /********************************************************************************
     * 获取所有权限的结构信息
     * @return
     ********************************************************************************/
    Map<String, Map<String, List<Right>>> rightMap();
    
    /********************************************************************************
     * 根据角色ID查询该角色的所有权限并拼接成String
     * @param roleId
     * @return
     ********************************************************************************/
    String getRightIdByRole(long roleId);

    /********************************************************************************
     * 授权并分配模块
     * @param roleId
     * @return 1:授权成功 0:操作失败
     ********************************************************************************/
    int giveRight(String[] rids, long roleId);
}