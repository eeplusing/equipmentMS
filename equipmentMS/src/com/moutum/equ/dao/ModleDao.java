package com.moutum.equ.dao;

import java.util.List;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.Modle;
import com.moutum.equ.domain.User;

/************************************************************************************
 * @Title        : ModleDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午10:49:19
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface ModleDao extends DaoSupport<Modle>
{
    List<Modle> selectFirstModleByUser(User user, long parentId);
    
    List<Modle> selectModle(long parentId);
}