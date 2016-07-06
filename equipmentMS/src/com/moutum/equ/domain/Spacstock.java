package com.moutum.equ.domain;

public class Spacstock 
{
	private long spacstockId;//库存ID
    
	private String sparepartNo;//备品编号
    
    private String sparepartName;//备品名称
    
    private int spacstockAmount;//库存量
    
    private int spacstockMinamount;//安全库存量

	public long getSpacstockId() {
		return spacstockId;
	}

	public void setSpacstockId(long spacstockId) 
	{
		this.spacstockId = spacstockId;
	}

	public String getSparepartNo() {
		return sparepartNo;
	}

	public void setSparepartNo(String sparepartNo) 
	{
		this.sparepartNo = sparepartNo;
	}

	public String getSparepartName() 
	{
		return sparepartName;
	}

	public void setSparepartName(String sparepartName) 
	{
		this.sparepartName = sparepartName;
	}

	public int getSpacstockAmount() 
	{
		return spacstockAmount;
	}

	public void setSpacstockAmount(int spacstockAmount) 
	{
		this.spacstockAmount = spacstockAmount;
	}

	public int getSpacstockMinamount() 
	{
		return spacstockMinamount;
	}

	public void setSpacstockMinamount(int spacstockMinamount) 
	{
		this.spacstockMinamount = spacstockMinamount;
	}

}
