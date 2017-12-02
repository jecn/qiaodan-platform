package org.safari.platform.modules.sys.entity;

import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.safari.platform.common.persistence.DataEntity;
import org.safari.platform.common.validator.my.MobileValid;
import org.safari.platform.common.validator.my.TelValid;

import com.google.common.collect.Lists;

/**
 * 用户entity
 * @author Alitalk
 * @date 2017-02-15
 */
public class User extends DataEntity<User> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 登录密码
	 */
	private String password;
	
	/**
	 * 
	 */
	private String salt;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 性别(1:男 2:女)
	 */
	private String sex;
	
	/**
	 * 性别(1:男 2:女)
	 */
	private String gender;
	
	/**
	 * 生日
	 */
	private String birthday;
	
	/**
	 * 电话号码
	 */
	private String tel;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 电子邮箱
	 */
	private String email;
	
	/**
	 * 头像
	 */
	private String icon;
	
	/**
	 * 状态
	 */
	private String stat;
	
	/**
	 * 所属单位
	 */
	private Corp corp;
	
	/**
	 * 角色列表
	 */
	private List<Role> roleList = Lists.newArrayList();
	
	/**
	 * 机构列表
	 */
	private List<Org> orgList = Lists.newArrayList();

	public User() {
		super();
	}
	
	public User(String id) {
		super(id);
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String salt) {
		super();
		this.username = username;
		this.password = password;
		this.salt = salt;
	}

	public User(String username, String password, String salt, String name,
			String gender, String birthday, String tel, String mobile,
			String email, String icon, String stat, List<Role> roleList,
			List<Org> orgList) {
		this(username, password, salt);
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.tel = tel;
		this.mobile = mobile;
		this.email = email;
		this.icon = icon;
		this.stat = stat;
		this.roleList = roleList;
		this.orgList = orgList;
	}

	@Length(min=6,max=20)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Length(max=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(max=1)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Length(max=1)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@TelValid
	@Length(max=20)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@MobileValid
	@Length(max=20)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Email
	@Length(max=80)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Length(max=128)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Length(max=1)
	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}
	
	public Corp getCorp() {
		return corp;
	}

	public void setCorp(Corp corp) {
		this.corp = corp;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Org> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Org> orgList) {
		this.orgList = orgList;
	}

	public boolean isAdmin(){
		return isAdmin(this.id);
	}
	
	public static boolean isAdmin(String id){
		return id != null && "1".equals(id);
	}
	
	
}