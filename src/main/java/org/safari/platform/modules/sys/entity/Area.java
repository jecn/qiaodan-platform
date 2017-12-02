package org.safari.platform.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import org.safari.platform.common.persistence.TreeEntity;

/**
 * 区域Entity
 * @author Alitalk
 * @date 2017-02-10
 */
public class Area extends TreeEntity<Area> {

	private static final long serialVersionUID = 1L;
	
	/**
	 *  区域编码
	 */
	private String code; 	
	
	/**
	 * 类型（1：国家；2：省份/直辖市；3：市；4：县级市/县/区）
	 */
	private String type; 
	
	/**
	 * 根据用户ID查询角色列表
	 */
	private User user;
	
	public Area(){
		super();
	}

	public Area(String id){
		super(id);
	}
	
	public Area(	User user){
		super();
		this.user = user;
	}
	
	public Area(String id,String useable){
		super(id);
		this.useable = useable;
	}
	
	public Area getParent() {
		return parent;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
	
	@Length(min=0, max=10)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUserId(User user) {
		this.user = user;
	}
	
}