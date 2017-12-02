package org.safari.platform.modules.sys.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.persistence.DataEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 角色entity
 * @author Alitalk
 * @date 2015年1月13日
 */
public class Role extends DataEntity<Role> {

	private static final long serialVersionUID = 1L;

	/**
	 * 中文名称
	 */
	private String cname;
	
	/**
	 * 英文名称
	 */
	private String ename;
	
	/**
	 * 英文名称
	 */
	private String code;
	
	/**
	 * 是否可用 (1: 可用 0: 不可用)
	 */
	private String useable;
	
	/**
	 * 是否可用 
	 */
	private String useableValue;
	
	/**
	 * 描述
	 */
	private String inst;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 根据用户ID查询角色列表
	 */
	private User user;
	
	@JsonIgnore
	private Set<RolePrivilege> rolePrivileges = new HashSet<RolePrivilege>();

	public Role() {
		super();
		this.useable = Global.USE_FLAG_YES;
	}
	
	public Role(String id) {
		super(id);
		this.useable = Global.USE_FLAG_YES;
	}

	public Role(User user) {
		this();
		this.user = user;
	}
	
	public Role(String id,String useable) {
		this();
		this.useable = useable;
	}

	@Length(max=48)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	@Length(max=32)
	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Length(max=32)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Length(min=1,max=1)
	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}
	
	public String getUseableValue() {
		return useableValue;
	}

	public void setUseableValue(String useableValue) {
		this.useableValue = useableValue;
	}

	@Length(max=128)
	public String getInst() {
		return inst;
	}

	public void setInst(String inst) {
		this.inst = inst;
	}
	
	@Length(max=48)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	public Set<RolePrivilege> getRolePrivileges() {
		return this.rolePrivileges;
	}

	public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}

}