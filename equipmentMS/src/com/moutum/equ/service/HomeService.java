package com.moutum.equ.service;

import java.util.List;

import com.moutum.equ.domain.User;
import com.moutum.equ.dto.Tree;

/************************************************************************************
 * @Title        : SystemService.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月18日 下午1:58:39
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface HomeService
{
    /********************************************************************************
     * 根据用户登陆名查询用户
     * @param loginAccount
     * @return
     ********************************************************************************/
    User getByLoginAccount(String loginAccount);

    /********************************************************************************
     * 根据用户信息获取该用户所拥有访问权限的所有模块信息
     * @param user
     * @return
     ********************************************************************************/
    List<Tree> getModles(User user);
}