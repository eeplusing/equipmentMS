package com.moutum.equ.dao;

import java.util.List;

import com.moutum.equ.base.DaoSupport;
import com.moutum.equ.domain.Right;
import com.moutum.equ.domain.User;

/************************************************************************************
 * @Title        : RightDao.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月9日 上午10:49:19
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface RightDao extends DaoSupport<Right>
{
    /********************************************************************************
     * 权限查询：1.function为空时查询user的全部访问权限集合
     *           2.function不为空时则查询user是否有访问function的权限，有则返回长度为1的集合，没有则返回null
     * @param user
     * @param function
     * @return
     ********************************************************************************/
    List<Right> getRightsByUser(User user, String function);
    
    /********************************************************************************
     * 权限查询：根据角色ID查询该角色拥有的所有权限集合
     * @param roleId
     * @return
     ********************************************************************************/
    List<Right> getRightsByRole(long roleId);

    /********************************************************************************
     * 分页查询符合条件的权限信息列表
     * @param pageNum
     * @param pageSize
     * @return
     ********************************************************************************/
    List<Right> getRecordList(int pageNum, int pageSize);
    
    /********************************************************************************
     * 根据模块ID查询所属该模块的所有权限
     * @param modleId
     * @return
     ********************************************************************************/
    List<Right> selectRight(long modleId);
}