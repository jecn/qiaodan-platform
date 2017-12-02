package org.safari.platform.common.persistence;

import org.hibernate.validator.constraints.Length;
import org.safari.platform.common.config.Global;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 数据Entity类
 * @author Alitalk
 * @date 2017-02-10
 */
public abstract class TreeEntity<T> extends DataEntity<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * 父级编号
	 */
	protected T parent;	
	
	/**
	 * 父级ID
	 */
	protected String pid ;
	
	/**
	 * 名称
	 */
	protected String name; 	
	
	/**
	 * 排序
	 */
	protected Integer sort;	
	
	/**
	 * 是否可用
	 */
	protected String useable;
	
	/**
	 * 是否可用
	 */
	protected String useableValue;
	
	/**
	 * 描述
	 */
	protected String inst;
	
	public TreeEntity() {
		super();
		this.sort = 10;
		this.useable = Global.USE_FLAG_YES;
	}
	
	public TreeEntity(String id) {
		super(id);
		this.sort = 10;
		this.useable = Global.USE_FLAG_YES;
	}
	
	/**
	 * 父对象，只能通过子类实现，父类实现mybatis无法读取
	 * @return
	 */
	@JsonBackReference
	public abstract T getParent();

	/**
	 * 父对象，只能通过子类实现，父类实现mybatis无法读取
	 * @return
	 */
	public abstract void setParent(T parent);

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public String getPid() {
		return pid;
	}
	
	@Length(min=1, max=100)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Length(min=1, max=1)
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
	
}
