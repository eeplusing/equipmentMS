package com.moutum.equ.dto;

import java.util.List;

import com.moutum.equ.domain.Modle;

/************************************************************************************
 * @Title        : Tree.java
 * @Description : 导航树
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月19日 下午1:19:23
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class Tree
{
    private String firstName;//一级模块名
    
    private List<Modle> modles;//子模块集合

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public List<Modle> getModles()
    {
        return modles;
    }

    public void setModles(List<Modle> modles)
    {
        this.modles = modles;
    }
}