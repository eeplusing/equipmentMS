package com.moutum.equ.dto;


/************************************************************************************
 * @Title        : InstockDto.java
 * @Description : 备品入库（登记）信息
 * @Author       : HuangWei
 * @DateTime     : 2015年8月19日 下午16:50:17
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/

public class InstockDto 
{
	private long instockId;//入库表ID
    
	private String sparepartNo;//备品编号
	
	private String sparepartName;//备品名称
	
	private String sparepartModle;//规格型号
	
	private String typeName;//备品名称

	private String sparepartUnit;//计量单位
	
	private String sparepartSetplace;//安放地点
	
	private String instockSparepartReceiver;//入库备品接收人
	
	private String instockSparepartProvider;//入库备品发放人
	
	private String instockSparepartSupervisor;//入库备品监督人
	
	private String instockTime;//入库时间
	
	private int instockAmount;//入库数量
	
	private String instockComment;//入库备注
	
	private int remainAmount;//剩余数量

	public int getRemainAmount() {
		return remainAmount;
	}

	public void setRemainAmount(int remainAmount) {
		this.remainAmount = remainAmount;
	}

	public long getInstockId() {
		return instockId;
	}

	public void setInstockId(long instockId) {
		this.instockId = instockId;
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

	public String getSparepartUnit() {
		return sparepartUnit;
	}

	public void setSparepartUnit(String sparepartUnit) {
		this.sparepartUnit = sparepartUnit;
	}

	public String getSparepartSetplace() {
		return sparepartSetplace;
	}

	public void setSparepartSetplace(String sparepartSetplace) {
		this.sparepartSetplace = sparepartSetplace;
	}

	public String getInstockSparepartReceiver() {
		return instockSparepartReceiver;
	}

	public void setInstockSparepartReceiver(String instockSparepartReceiver) {
		this.instockSparepartReceiver = instockSparepartReceiver;
	}

	public String getInstockSparepartProvider() {
		return instockSparepartProvider;
	}

	public void setInstockSparepartProvider(String instockSparepartProvider) {
		this.instockSparepartProvider = instockSparepartProvider;
	}

	public String getInstockSparepartSupervisor() {
		return instockSparepartSupervisor;
	}

	public void setInstockSparepartSupervisor(String instockSparepartSupervisor) {
		this.instockSparepartSupervisor = instockSparepartSupervisor;
	}

	public String getTypeName() {
		return typeName;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getInstockTime() {
		return instockTime;
	}

	public void setInstockTime(String instockTime) {
		this.instockTime = instockTime;
	}

	public int getInstockAmount() {
		return instockAmount;
	}

	public void setInstockAmount(int instockAmount) {
		this.instockAmount = instockAmount;
	}

	public String getInstockComment() {
		return instockComment;
	}

	public void setInstockComment(String instockComment) {
		this.instockComment = instockComment;
	}
	
}
