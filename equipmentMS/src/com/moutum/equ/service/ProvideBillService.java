package com.moutum.equ.service;

import java.util.List;
import java.util.Map;

import com.moutum.equ.domain.ProvideBill;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.dto.ProvideBillDt1o;
import com.moutum.equ.dto.ProvideBillDto;

/************************************************************************************
 * @Title        : ProvideBillService.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年8月19日 上午10:37:09
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public interface ProvideBillService
{
    /********************************************************************************
     * 根据查询条件获取PageBean
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     ********************************************************************************/
    PageBean getPage(int pageNum, int pageSize, Map<String, Object> map);
    
    /********************************************************************************
     * 添加设备发放明细
     * @param provideBill
     ********************************************************************************/
    void save(ProvideBill provideBill);
    
    
    /********************************************************************************
     * 修改设备发放明细
     * @param provideBill
     ********************************************************************************/
    void modify(ProvideBill provideBill);

    /********************************************************************************
     * 获取设备发放明细展示信息
     * @return
     ********************************************************************************/
    List<ProvideBillDto> getAll(Map<String, Object> map);

    /********************************************************************************
     * 根据设备发放明细ID获取明细信息
     * @param id
     ********************************************************************************/
    ProvideBillDt1o getDtoById(long id);

    /********************************************************************************
     * 根据批次编号查询明细信息
     * @param equipmentNo
     ********************************************************************************/
    ProvideBill getByNo(String equipmentNo);
}