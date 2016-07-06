package com.moutum.equ.dao;

import java.util.List;
import java.util.Map;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.User;

/************************************************************************************
 * @Title        : UserDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月6日 下午4:17:38
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface UserDao extends DaoSupport<User>
{
    /********************************************************************************
     * 根据用户名查询用户信息
     * @param loginAccount
     * @return
     ********************************************************************************/
    User getByLoginAccount(String loginAccount);

    /********************************************************************************
     * 查询符合条件的用户数量
     * @param map
     * @return
     ********************************************************************************/
    int getRecordCount(Map<String, Object> map);

    /********************************************************************************
     * 分页查询符合条件的用户信息列表
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     ********************************************************************************/
    List<User> getRecordList(int pageNum, int pageSize, Map<String, Object> map);
}