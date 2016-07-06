package com.moutum.equ.domain;

import java.util.Date;

/************************************************************************************
 * @Title        : Outstock.java
 * @Description : 备品出库信息
 * @Author       : HuangWei
 * @DateTime     : 2015年8月19日 下午17:10:16
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class Outstock 
{
	private long outstockId;//出库表ID
    
	private String sparepartNo;//备品编号
	
	private String sparepartName;//备品名称
	
	private String sparepartModle;//规格型号
	
	private long typeId;//备品类别
	
	private String sparepartUnit;//计量单位
	
	private String outstockSparepartReceiver;//出库备品接收人
	
	private String outstockSparepartProvider;//出库备品发放人
	
	private Date instockTime;//入库时间
	
	private Date outstockTime;//出库时间
	
	private String sparepartUseplace;//使用地点
	
	private int outstockAmount;//出库数量
	
	private int remainAmount;//剩余数量
	
	private String inStockComment;//入库备注

	private String outStockComment;//出库备注

	public long getOutstockId() {
		return outstockId;
	}

	public void setOutstockId(long outstockId) {
		this.outstockId = outstockId;
	}

	public String getSparepartNo() {
		return sparepartNo;
	}

	public void setSparepartNo(String sparepartNo) {
		this.sparepartNo = sparepartNo;
	}

	public String getSparepartName() {
		return sparepartName;
	}

	public void setSparepartName(String sparepartName) {
		this.sparepartName = sparepartName;
	}

	public String getSparepartModle() {
		return sparepartModle;
	}

	public void setSparepartModle(String sparepartModle) {
		this.sparepartModle = sparepartModle;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getSparepartUnit() {
		return sparepartUnit;
	}

	public void setSparepartUnit(String sparepartUnit) {
		this.sparepartUnit = sparepartUnit;
	}

	public Date getInstockTime() {
		return instockTime;
	}

	public void setInstockTime(Date instockTime) {
		this.instockTime = instockTime;
	}

	public String getSparepartUseplace() {
		return sparepartUseplace;
	}

	public void setSparepartUseplace(String sparepartUseplace) {
		this.sparepartUseplace = sparepartUseplace;
	}

	public int getRemainAmount() {
		return remainAmount;
	}

	public void setRemainAmount(int remainAmount) {
		this.remainAmount = remainAmount;
	}

	public String getOutStockComment() {
		return outStockComment;
	}

	public void setOutStockComment(String outStockComment) {
		this.outStockComment = outStockComment;
	}

	public String getOutstockSparepartReceiver() {
		return outstockSparepartReceiver;
	}

	public void setOutstockSparepartReceiver(String outstockSparepartReceiver) {
		this.outstockSparepartReceiver = outstockSparepartReceiver;
	}

	public String getOutstockSparepartProvider() {
		return outstockSparepartProvider;
	}

	public void setOutstockSparepartProvider(String outstockSparepartProvider) {
		this.outstockSparepartProvider = outstockSparepartProvider;
	}

	public Date getOutstockTime() {
		return outstockTime;
	}

	public void setOutstockTime(Date outstockTime) {
		this.outstockTime = outstockTime;
	}

	public int getOutstockAmount() {
		return outstockAmount;
	}

	public void setOutstockAmount(int outstockAmount) {
		this.outstockAmount = outstockAmount;
	}

	public String getInStockComment() {
		return inStockComment;
	}

	public void setInStockComment(String inStockComment) {
		this.inStockComment = inStockComment;
	}
	
	
}
