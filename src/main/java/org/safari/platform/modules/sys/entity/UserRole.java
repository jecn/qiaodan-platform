package org.safari.platform.modules.sys.entity;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.safari.platform.common.persistence.DataEntity;

/**
 * 用户角色entity
 * @author Alitalk
 * @date 2017-02-15
 */
public class UserRole  extends DataEntity<UserRole>{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户
	 */
	private User user;
	
	/**
	 * 角色
	 */
	private Role role;

	public UserRole() {
		super();
	}
	
	public UserRole(String id) {
		super(id);
	}

	public UserRole(User user, Role role) {
		super();
		this.user = user;
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}