package org.safari.platform.modules.rept.entity;

import org.safari.platform.common.persistence.DataEntity;

public class UserData extends DataEntity<UserData>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int month;
	private int count;
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
