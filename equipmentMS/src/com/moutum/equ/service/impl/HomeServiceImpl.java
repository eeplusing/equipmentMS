package com.moutum.equ.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.dao.ModleDao;
import com.moutum.equ.dao.UserDao;
import com.moutum.equ.domain.Modle;
import com.moutum.equ.domain.User;
import com.moutum.equ.dto.Tree;
import com.moutum.equ.service.HomeService;

/************************************************************************************
 * @Title        : SystemServiceImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月18日 下午1:59:48
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Service
public class HomeServiceImpl implements HomeService
{

    @Resource UserDao userDao;
    
    @Resource ModleDao modleDao;
    
    @Override
    public User getByLoginAccount(String loginAccount)
    {
        return userDao.getByLoginAccount(loginAccount);
    }

    @Override
    public List<Tree> getModles(User user)
    {
        try
        {
            if(null == (Long)user.getRoleId())
            {
                return null;
            }
            List<Tree> trees = new ArrayList<Tree>();
            List<Modle> firstModle = modleDao.selectFirstModleByUser(user, 0);//获取拥有访问权限的所有一级模块
            for(Modle m : firstModle)
            {
                //根据一级模块获取所属拥有访问权限的二级模块
                List<Modle> secondModle = modleDao.selectFirstModleByUser(user, m.getModleId());
                Tree tree = new Tree();
                tree.setFirstName(m.getName());
                tree.setModles(secondModle);
                trees.add(tree);
            }
            return trees;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}