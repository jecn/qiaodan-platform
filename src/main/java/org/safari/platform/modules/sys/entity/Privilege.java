package org.safari.platform.modules.sys.entity;

import java.util.List;


import org.hibernate.validator.constraints.Length;
import org.safari.platform.common.persistence.TreeEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 菜单Entity
 * @author Alitalk
 * @date 2017-02-15
 */
public class Privilege extends TreeEntity<Privilege> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 名称
	 */
	private String name; 
	
	/**
	 * 编码
	 */
	private String code;
	
	/**
	 * 类型(1: 菜单 2: 功能 3: 按钮  4:属性)
	 */
	private String type;
	
	/**
	 * 图标
	 */
	private String icon;
	
	/**
	 * 链接
	 */
	private String url; 
	
	/**
	 * 根据用户ID查询角色列表
	 */
	private User user;
	
	public Privilege(){
		super();
	}
	
	public Privilege(String id){
		super(id);
	}
	
	public Privilege(String id,String useable){
		super(id);
		this.useable = useable;
	}
	
	public Privilege(User user){
		super();
		this.user = user;
	}
	
	public Privilege(String id,String useable,User user){
		super(id);
		this.user = user;
		this.useable = useable;
	}
	
	
	@Length(min=1, max=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(max=32)
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

	@Length(max=128)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Length(max=128)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JsonIgnore
	public static void sortList(List<Privilege> list, List<Privilege> sourcelist, String pid, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			Privilege e = sourcelist.get(i);
			if (e.getParent()!=null && e.getParent().getId()!=null
					&& e.getParent().getId().equals(pid)){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						Privilege child = sourcelist.get(j);
						if (child.getParent()!=null && child.getParent().getId()!=null
								&& child.getParent().getId().equals(e.getId())){
							sortList(list, sourcelist, e.getId(), true);
							break;
						}
					}
				}
			}
		}
	}

	@JsonIgnore
	public static String getRootId(){
		return "1";
	}

	public Privilege getParent() {
		return parent;
	}

	@Override
	public void setParent(Privilege parent) {
		this.parent = parent;
	}
	
}