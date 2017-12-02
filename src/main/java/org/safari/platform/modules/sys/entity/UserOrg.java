package org.safari.platform.modules.sys.entity;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.safari.platform.common.persistence.DataEntity;

/**
 * 用户机构entity
 * @author Alitalk
 * @date 2017-02-15
 */
public class UserOrg extends DataEntity<UserOrg> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户
	 */
	private User user;
	
	/**
	 * 机构
	 */
	private Org org;

	public UserOrg() {
		super();
	}
	
	public UserOrg(String id) {
		super(id);
	}

	public UserOrg(User user, Org org) {
		super();
		this.user = user;
		this.org = org;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

}