package com.dj.cn.common;

import java.util.Date;

public class BaseDomain {
	
	private Integer id ;
	
	private Integer userCreateId ;
	
	private Integer userUpdateId ;
	
	private Date createTime ;
	
	private Date updateTime ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserCreateId() {
		return userCreateId;
	}

	public void setUserCreateId(Integer userCreateId) {
		this.userCreateId = userCreateId;
	}

	public Integer getUserUpdateId() {
		return userUpdateId;
	}

	public void setUserUpdateId(Integer userUpdateId) {
		this.userUpdateId = userUpdateId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	

}
