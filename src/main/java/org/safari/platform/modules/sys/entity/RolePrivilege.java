package org.safari.platform.modules.sys.entity;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.safari.platform.common.persistence.DataEntity;

/**
 * 角色权限entity
 * @author Alitalk
 * @date 2017-02-15
 */
public class RolePrivilege extends DataEntity<RolePrivilege>  {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色
	 */
	private Role role;
	
	/**
	 * 菜单
	 */
	private Privilege privilege;
	
	public RolePrivilege() {
		super();
	}
	
	public RolePrivilege(String id) {
		super(id);
	}
	
	public RolePrivilege(Role role, Privilege privilege) {
		super();
		this.role = role;
		this.privilege = privilege;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

}
