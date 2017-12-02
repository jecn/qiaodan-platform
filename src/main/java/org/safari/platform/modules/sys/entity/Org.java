package org.safari.platform.modules.sys.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.safari.platform.common.persistence.TreeEntity;

/**
 * 机构Entity
 * @author Alitalk
 * @date 2017-02-10
 */
public class Org extends TreeEntity<Org> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 所属单位
	 */
	private Corp corp;
	
	/**
	 * 中文名称
	 */
	private String cname;
	
	/**
	 * 英文名称
	 */
	private String ename;
	
	/**
	 * 机构类型(1:公司 2:部门 3:小组)
	 */
	private String type;
	
	/**
	 * 机构等级(1:一级 2:二级 3:三级 4:四级)
	 */
	private String grade; 
	
	/**
	 * 电话
	 */
	private String tel;
	
	/**
	 * 传真
	 */
	private String fax;
	
	/**
	 * 电子邮件
	 */
	private String email;
	
	/**
	 *  联系地址
	 */
	private String address;
	
	/**
	 * 根据用户ID查询角色列表
	 */
	private User user;
	
	/**
	 * 快速添加子部门
	 */
	private List<String> childDeptList;
	
	public Org(){
		super();
	}

	public Org(String id){
		super(id);
	}
	
	public Org(User user){
		super();
		this.user = user;
	}
	
	public Org(String id,String useable){
		super(id);
		this.useable = useable;
	}

	public Org getParent() {
		return parent;
	}

	public void setParent(Org parent) {
		this.parent = parent;
	}
	
	public Corp getCorp() {
		return corp;
	}

	public void setCorp(Corp corp) {
		this.corp = corp;
	}

	@Length(max=32)
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Length(max=48)
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Length(max=1)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(max=1)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Length(max=20)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Length(max=48)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Length(max=80)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Length(max=160)
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getChildDeptList() {
		return childDeptList;
	}

	public void setChildDeptList(List<String> childDeptList) {
		this.childDeptList = childDeptList;
	}


}