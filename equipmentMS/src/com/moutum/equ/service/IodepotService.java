package com.moutum.equ.service;

import com.moutum.equ.dto.PageBean;

/************************************************************************************
 * @Title        : IodepotService.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年4月22日 上午11:02:15
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface IodepotService
{

    /********************************************************************************
     * 根据备品编号查询备品的出入库记录的PageBean
     * @param pageNum
     * @param pageSize
     * @param sparepartNo
     * @return
     ********************************************************************************/
    PageBean getPage(int pageNum, int pageSize, String sparepartNo);
}

