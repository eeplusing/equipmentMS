package com.moutum.equ.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moutum.equ.dao.EquipmentDao;
import com.moutum.equ.dao.ProvideBillDao;
import com.moutum.equ.domain.Equipment;
import com.moutum.equ.domain.ProvideBill;
import com.moutum.equ.domain.ReceiveBill;
import com.moutum.equ.dto.PageBean;
import com.moutum.equ.dto.ProvideBillDt1o;
import com.moutum.equ.dto.ProvideBillDto;
import com.moutum.equ.service.ProvideBillService;
import com.moutum.equ.util.DateUtil;
import com.moutum.equ.util.QueryHelper;

/************************************************************************************
 * @Title        : ProvideBillServiceImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年8月19日 上午10:46:57
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Service
public class ProvideBillServiceImpl implements ProvideBillService
{

    @Resource ProvideBillDao provideBillDao;
    @Resource EquipmentDao equipmentDao;
    
    @SuppressWarnings("unchecked")
    @Override
    public PageBean getPage(int pageNum, int pageSize, Map<String, Object> map)
    {
        QueryHelper queryHelper = new QueryHelper(ProvideBill.class, "pb");
        if(null != map.get("equNo"))
        {
            queryHelper.addCondition("pb.equipmentNo = ? ", map.get("equNo"));
        }
        ProvideBill provideBill = provideBillDao.getByNo((String)map.get("equNo"));
        List<ProvideBill> pbs = new ArrayList<ProvideBill>();
        if(provideBill != null)
        {
            pbs.add(provideBill);
        }
        System.out.println(pbs.size());
        List<ProvideBillDto> dtos =new ArrayList<ProvideBillDto>(); 
        for(int i = (pageNum - 1) * pageSize ; i< pageNum * pageSize ; i++ )
        {
            if(i < getPBDList(pbs).size())
            {
                dtos.add(getPBDList(pbs).get(i));
            }
        }
        int count = getPBDList(pbs).size();
        return new PageBean(pageNum , pageSize ,count , dtos);
    }

    @Override
    public void save(ProvideBill provideBill)
    {
        provideBillDao.save(provideBill);
    }

    @Override
    public void modify(ProvideBill provideBill)
    {
        provideBillDao.update(provideBill);
    }

    @Override
    public List<ProvideBillDto> getAll(Map<String, Object> map)
    {
        return getPBDList(provideBillDao.getAll(map));
    }

    private List<ProvideBillDto> getPBDList(List<ProvideBill> pbs)
    {
        List<ProvideBillDto> pbds = new ArrayList<ProvideBillDto>();
        for(ProvideBill pb : pbs)
        {
            for(ReceiveBill rb : pb.getReceiveBills())
            {
                if(rb.getStationName().equals("车站领导"))
                {
                    ProvideBillDto pbd1 = new ProvideBillDto();
                    pbd1.setId(pb.getId());
                    pbd1.setEquipmentNo(pb.getEquipmentNo());
                    pbd1.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd1.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd1.setEquipmentModle(pb.getEquipmentModle());
                    pbd1.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd1.setPutplace("车站领导");
                    pbd1.setFlag(1);
                    pbd1.setAmount(rb.getAmount());
                    pbd1.setReceiver(rb.getReceiver());
                    pbd1.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd1.setRemark(rb.getRemark());
                    pbds.add(pbd1);
                }
                if(rb.getStationName().equals("党办室"))
                {
                    ProvideBillDto pbd2 = new ProvideBillDto();
                    pbd2.setId(pb.getId());
                    pbd2.setEquipmentNo(pb.getEquipmentNo());
                    pbd2.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd2.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd2.setEquipmentModle(pb.getEquipmentModle());
                    pbd2.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd2.setPutplace("党办室");
                    pbd2.setFlag(2);
                    pbd2.setAmount(rb.getAmount());
                    pbd2.setReceiver(rb.getReceiver());
                    pbd2.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd2.setRemark(rb.getRemark());
                    pbds.add(pbd2);
                }
                if(rb.getStationName().equals("办公室"))
                {
                    ProvideBillDto pbd3 = new ProvideBillDto();
                    pbd3.setId(pb.getId());
                    pbd3.setEquipmentNo(pb.getEquipmentNo());
                    pbd3.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd3.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd3.setEquipmentModle(pb.getEquipmentModle());
                    pbd3.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd3.setPutplace("办公室");
                    pbd3.setFlag(3);
                    pbd3.setAmount(rb.getAmount());
                    pbd3.setReceiver(rb.getReceiver());
                    pbd3.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd3.setRemark(rb.getRemark());
                    pbds.add(pbd3);
                }
                if(rb.getStationName().equals("人劳科"))
                {
                    ProvideBillDto pbd4 = new ProvideBillDto();
                    pbd4.setId(pb.getId());
                    pbd4.setEquipmentNo(pb.getEquipmentNo());
                    pbd4.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd4.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd4.setEquipmentModle(pb.getEquipmentModle());
                    pbd4.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd4.setPutplace("人劳科");
                    pbd4.setFlag(4);
                    pbd4.setAmount(rb.getAmount());
                    pbd4.setReceiver(rb.getReceiver());
                    pbd4.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd4.setRemark(rb.getRemark());
                    pbds.add(pbd4);
                }
                if(rb.getStationName().equals("财务科"))
                {
                    ProvideBillDto pbd5 = new ProvideBillDto();
                    pbd5.setId(pb.getId());
                    pbd5.setEquipmentNo(pb.getEquipmentNo());
                    pbd5.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd5.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd5.setEquipmentModle(pb.getEquipmentModle());
                    pbd5.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd5.setPutplace("财务科");
                    pbd5.setFlag(5);
                    pbd5.setAmount(rb.getAmount());
                    pbd5.setReceiver(rb.getReceiver());
                    pbd5.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd5.setRemark(rb.getRemark());
                    pbds.add(pbd5);
                }
                if(rb.getStationName().equals("技术科"))
                {
                    ProvideBillDto pbd6 = new ProvideBillDto();
                    pbd6.setId(pb.getId());
                    pbd6.setEquipmentNo(pb.getEquipmentNo());
                    pbd6.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd6.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd6.setEquipmentModle(pb.getEquipmentModle());
                    pbd6.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd6.setPutplace("技术科");
                    pbd6.setFlag(6);
                    pbd6.setAmount(rb.getAmount());
                    pbd6.setReceiver(rb.getReceiver());
                    pbd6.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd6.setRemark(rb.getRemark());
                    pbds.add(pbd6);
                }
                if(rb.getStationName().equals("教育科"))
                {
                    ProvideBillDto pbd7 = new ProvideBillDto();
                    pbd7.setId(pb.getId());
                    pbd7.setEquipmentNo(pb.getEquipmentNo());
                    pbd7.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd7.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd7.setEquipmentModle(pb.getEquipmentModle());
                    pbd7.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd7.setPutplace("教育科");
                    pbd7.setFlag(7);
                    pbd7.setAmount(rb.getAmount());
                    pbd7.setReceiver(rb.getReceiver());
                    pbd7.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd7.setRemark(rb.getRemark());
                    pbds.add(pbd7);
                }
                if(rb.getStationName().equals("安全科"))
                {
                    ProvideBillDto pbd8 = new ProvideBillDto();
                    pbd8.setId(pb.getId());
                    pbd8.setEquipmentNo(pb.getEquipmentNo());
                    pbd8.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd8.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd8.setEquipmentModle(pb.getEquipmentModle());
                    pbd8.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd8.setPutplace("安全科");
                    pbd8.setFlag(8);
                    pbd8.setAmount(rb.getAmount());
                    pbd8.setReceiver(rb.getReceiver());
                    pbd8.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd8.setRemark(rb.getRemark());
                    pbds.add(pbd8);
                }
               if(rb.getStationName().equals("信技科"))
                {
                    ProvideBillDto pbd9 = new ProvideBillDto();
                    pbd9.setId(pb.getId());
                    pbd9.setEquipmentNo(pb.getEquipmentNo());
                    pbd9.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd9.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd9.setEquipmentModle(pb.getEquipmentModle());
                    pbd9.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd9.setPutplace("信技科");
                    pbd9.setFlag(9);
                    pbd9.setAmount(rb.getAmount());
                    pbd9.setReceiver(rb.getReceiver());
                    pbd9.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd9.setRemark(rb.getRemark());
                    pbds.add(pbd9);
                }
                if(rb.getStationName().equals("货运科"))
                {
                    ProvideBillDto pbd10 = new ProvideBillDto();
                    pbd10.setId(pb.getId());
                    pbd10.setEquipmentNo(pb.getEquipmentNo());
                    pbd10.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd10.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd10.setEquipmentModle(pb.getEquipmentModle());
                    pbd10.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd10.setPutplace("货运科");
                    pbd10.setFlag(10);
                    pbd10.setAmount(rb.getAmount());
                    pbd10.setReceiver(rb.getReceiver());
                    pbd10.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd10.setRemark(rb.getRemark());
                    pbds.add(pbd10);
                }
                if(rb.getStationName().equals("调度车间"))
                {
                    ProvideBillDto pbd11 = new ProvideBillDto();
                    pbd11.setId(pb.getId());
                    pbd11.setEquipmentNo(pb.getEquipmentNo());
                    pbd11.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd11.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd11.setEquipmentModle(pb.getEquipmentModle());
                    pbd11.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd11.setPutplace("调度车间");
                    pbd11.setFlag(11);
                    pbd11.setAmount(rb.getAmount());
                    pbd11.setReceiver(rb.getReceiver());
                    pbd11.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd11.setRemark(rb.getRemark());
                    pbds.add(pbd11);
                }
                if(rb.getStationName().equals("机务车间"))
                {
                    ProvideBillDto pbd12 = new ProvideBillDto();
                    pbd12.setId(pb.getId());
                    pbd12.setEquipmentNo(pb.getEquipmentNo());
                    pbd12.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd12.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd12.setEquipmentModle(pb.getEquipmentModle());
                    pbd12.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd12.setPutplace("机务车间");
                    pbd12.setFlag(12);
                    pbd12.setAmount(rb.getAmount());
                    pbd12.setReceiver(rb.getReceiver());
                    pbd12.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd12.setRemark(rb.getRemark());
                    pbds.add(pbd12);
                }
                if(rb.getStationName().equals("运转一车间"))
                {
                    ProvideBillDto pbd13 = new ProvideBillDto();
                    pbd13.setId(pb.getId());
                    pbd13.setEquipmentNo(pb.getEquipmentNo());
                    pbd13.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd13.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd13.setEquipmentModle(pb.getEquipmentModle());
                    pbd13.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd13.setPutplace("运转一车间");
                    pbd13.setFlag(13);
                    pbd13.setAmount(rb.getAmount());
                    pbd13.setReceiver(rb.getReceiver());
                    pbd13.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd13.setRemark(rb.getRemark());
                    pbds.add(pbd13);
                }
                if(rb.getStationName().equals("运转二车间"))
                {
                    ProvideBillDto pbd14 = new ProvideBillDto();
                    pbd14.setId(pb.getId());
                    pbd14.setEquipmentNo(pb.getEquipmentNo());
                    pbd14.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd14.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd14.setEquipmentModle(pb.getEquipmentModle());
                    pbd14.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd14.setPutplace("运转二车间");
                    pbd14.setFlag(14);
                    pbd14.setAmount(rb.getAmount());
                    pbd14.setReceiver(rb.getReceiver());
                    pbd14.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd14.setRemark(rb.getRemark());
                    pbds.add(pbd14);
                }
                if(rb.getStationName().equals("综合设备车间"))
                {
                    ProvideBillDto pbd15 = new ProvideBillDto();
                    pbd15.setId(pb.getId());
                    pbd15.setEquipmentNo(pb.getEquipmentNo());
                    pbd15.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd15.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd15.setEquipmentModle(pb.getEquipmentModle());
                    pbd15.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd15.setPutplace("综合设备车间");
                    pbd15.setFlag(15);
                    pbd15.setAmount(rb.getAmount());
                    pbd15.setReceiver(rb.getReceiver());
                    pbd15.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd15.setRemark(rb.getRemark());
                    pbds.add(pbd15);
                }
                if(rb.getStationName().equals("货检车间"))
                {
                    ProvideBillDto pbd16 = new ProvideBillDto();
                    pbd16.setId(pb.getId());
                    pbd16.setEquipmentNo(pb.getEquipmentNo());
                    pbd16.setEquipmentName(pb.getEquipmentName());
                    Equipment equipment = new Equipment();
                    equipment.setEquipmentNo(pb.getEquipmentNo());
                    equipmentDao.select(equipment);
                    pbd16.setEquipresource(equipmentDao.select(equipment).getEquipmentBuyType());
                    pbd16.setEquipmentModle(pb.getEquipmentModle());
                    pbd16.setEquipmentUseYears(pb.getEquipmentUseYears());
                    pbd16.setPutplace("货检车间");
                    pbd16.setFlag(16);
                    pbd16.setAmount(rb.getAmount());
                    pbd16.setReceiver(rb.getReceiver());
                    pbd16.setProvideDate(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd16.setRemark(rb.getRemark());
                    pbds.add(pbd16);
                }
            }
        }
        List <ProvideBillDto> pbdss = new ArrayList<ProvideBillDto>(); 
        for(int i = 1 ; i<=16 ; i++)
        {
            for(ProvideBillDto pro  : pbds)
            {
                if(pro.getFlag() == i)
                {
                    pbdss.add(pro);
                }
            }
        }
        return pbdss;
    }

    @Override
    public ProvideBillDt1o getDtoById(long id)
    {
        List<ProvideBill> pbs = new ArrayList<ProvideBill>();
        pbs.add(provideBillDao.getById(id));
        
        List<ProvideBillDt1o> pbds = new ArrayList<ProvideBillDt1o>();
        for(ProvideBill pb : pbs)
        {
            ProvideBillDt1o pbd = new ProvideBillDt1o();
            pbd.setId(pb.getId());
            pbd.setEquipmentNo(pb.getEquipmentNo());
            pbd.setEquipmentName(pb.getEquipmentName());
            pbd.setEquipmentModle(pb.getEquipmentModle());
            pbd.setEquipmentStartDate(DateUtil.dateToString(pb.getEquipmentStartDate(), "yyyy-MM-dd"));
            pbd.setEquipmentUseYears(pb.getEquipmentUseYears());
            pbd.setAmount(pb.getAmount());
            pbd.setAreaCode(pb.getAreaCode());
            
            for(ReceiveBill rb : pb.getReceiveBills())
            {
                if(rb.getStationName().equals("车站领导"))
                {
                    pbd.setIdA(rb.getId());
                    pbd.setAmountA(rb.getAmount());
                    pbd.setReceiverA(rb.getReceiver());
                    pbd.setProvideDateA(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkA(rb.getRemark());
                }
                else if(rb.getStationName().equals("党办室"))
                {
                    pbd.setIdB(rb.getId());
                    pbd.setAmountB(rb.getAmount());
                    pbd.setReceiverB(rb.getReceiver());
                    pbd.setProvideDateB(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkB(rb.getRemark());
                }
                else if(rb.getStationName().equals("办公室"))
                {
                    pbd.setIdC(rb.getId());
                    pbd.setAmountC(rb.getAmount());
                    pbd.setReceiverC(rb.getReceiver());
                    pbd.setProvideDateC(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkC(rb.getRemark());
                }
                else if(rb.getStationName().equals("人劳科"))
                {
                    pbd.setIdD(rb.getId());
                    pbd.setAmountD(rb.getAmount());
                    pbd.setReceiverD(rb.getReceiver());
                    pbd.setProvideDateD(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkD(rb.getRemark());
                }
                else if(rb.getStationName().equals("财务科"))
                {
                    pbd.setIdE(rb.getId());
                    pbd.setAmountE(rb.getAmount());
                    pbd.setReceiverE(rb.getReceiver());
                    pbd.setProvideDateE(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkE(rb.getRemark());
                }
                else if(rb.getStationName().equals("技术科"))
                {
                    pbd.setIdF(rb.getId());
                    pbd.setAmountF(rb.getAmount());
                    pbd.setReceiverF(rb.getReceiver());
                    pbd.setProvideDateF(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkF(rb.getRemark());
                }
                else if(rb.getStationName().equals("教育科"))
                {
                    pbd.setIdG(rb.getId());
                    pbd.setAmountG(rb.getAmount());
                    pbd.setReceiverG(rb.getReceiver());
                    pbd.setProvideDateG(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkG(rb.getRemark());
                }
                else if(rb.getStationName().equals("安全科"))
                {
                    pbd.setIdH(rb.getId());
                    pbd.setAmountH(rb.getAmount());
                    pbd.setReceiverH(rb.getReceiver());
                    pbd.setProvideDateH(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkH(rb.getRemark());
                }
                else if(rb.getStationName().equals("信技科"))
                {
                    pbd.setIdI(rb.getId());
                    pbd.setAmountI(rb.getAmount());
                    pbd.setReceiverI(rb.getReceiver());
                    pbd.setProvideDateI(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkI(rb.getRemark());
                }
                else if(rb.getStationName().equals("货运科"))
                {
                    pbd.setIdJ(rb.getId());
                    pbd.setAmountJ(rb.getAmount());
                    pbd.setReceiverJ(rb.getReceiver());
                    pbd.setProvideDateJ(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkJ(rb.getRemark());
                }
                else if(rb.getStationName().equals("调度车间"))
                {
                    pbd.setIdK(rb.getId());
                    pbd.setAmountK(rb.getAmount());
                    pbd.setReceiverK(rb.getReceiver());
                    pbd.setProvideDateK(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkK(rb.getRemark());
                }
                else if(rb.getStationName().equals("机务车间"))
                {
                    pbd.setIdL(rb.getId());
                    pbd.setAmountL(rb.getAmount());
                    pbd.setReceiverL(rb.getReceiver());
                    pbd.setProvideDateL(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkL(rb.getRemark());
                }
                else if(rb.getStationName().equals("运转一车间"))
                {
                    pbd.setIdM(rb.getId());
                    pbd.setAmountM(rb.getAmount());
                    pbd.setReceiverM(rb.getReceiver());
                    pbd.setProvideDateM(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkM(rb.getRemark());
                }
                else if(rb.getStationName().equals("运转二车间"))
                {
                    pbd.setIdN(rb.getId());
                    pbd.setAmountN(rb.getAmount());
                    pbd.setReceiverN(rb.getReceiver());
                    pbd.setProvideDateN(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkN(rb.getRemark());
                }
                else if(rb.getStationName().equals("综合设备车间"))
                {
                    pbd.setIdO(rb.getId());
                    pbd.setAmountO(rb.getAmount());
                    pbd.setReceiverO(rb.getReceiver());
                    pbd.setProvideDateO(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkO(rb.getRemark());
                }
                else if(rb.getStationName().equals("货检车间"))
                {
                    pbd.setIdP(rb.getId());
                    pbd.setAmountP(rb.getAmount());
                    pbd.setReceiverP(rb.getReceiver());
                    pbd.setProvideDateP(DateUtil.dateToString(rb.getProvideDate(), "yyyy-MM-dd"));
                    pbd.setRemarkP(rb.getRemark());
                }
            }
            pbds.add(pbd);
        }
        
        return pbds.get(0);
    }

    @Override
    public ProvideBill getByNo(String equipmentNo)
    {
        return provideBillDao.getByNo(equipmentNo);
    }
}