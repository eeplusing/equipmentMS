package com.moutum.equ.service;

import java.util.List;

import com.moutum.equ.domain.Role;
import com.moutum.equ.dto.PageBean;

/************************************************************************************
 * @Title        : RoleService.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 下午3:51:25
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface RoleService
{
    
    PageBean getPage(int pageNum, int pageSize);
    
    int modify(Role role);
    
    int save(Role role);
    
    int delete(long roleId);

    List<Role> select();
}