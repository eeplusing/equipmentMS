package com.moutum.equ.domain;

import java.io.Serializable;

public class Instockandoutstock implements Serializable 
{

	private static final long serialVersionUID = -1058371148323573112L;
	
	private long instockId;//入库ID
    
    private long outstockId;//出库ID

	public long getInstockId() {
		return instockId;
	}

	public void setInstockId(long instockId) {
		this.instockId = instockId;
	}

	public long getOutstockId() {
		return outstockId;
	}

	public void setOutstockId(long outstockId) {
		this.outstockId = outstockId;
	}

	@Override
    public int hashCode()
    {
        int instockCode = (getInstockId() + "").hashCode();
        int outstockCode = (getOutstockId() + "").hashCode();
        return instockCode*12 + outstockCode*31;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Instockandoutstock))
        {
            return false;
        }
        Instockandoutstock es = (Instockandoutstock)obj;
        return (this.instockId == es.getInstockId() && this.outstockId == es.getOutstockId());
    }
    
}
