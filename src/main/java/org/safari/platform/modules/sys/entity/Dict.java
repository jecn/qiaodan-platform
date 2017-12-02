package org.safari.platform.modules.sys.entity;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;

import org.hibernate.validator.constraints.Length;
import org.safari.platform.common.persistence.DataEntity;

/**
 * 字典Entity
 * @author Alitalk
 * @date 2017-02-15
 */
public class Dict extends DataEntity<Dict> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 标签名
	 */
	private String label;	
	
	/**
	 * 数据值
	 */
	private String value;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 所属模块
	 */
	private String module;
	
	/**
	 * 所属模块
	 */
	private String moduleValue;
	
	/**
	 *  排序
	 */
	private Integer sort;
	
	/**
	 * 是否可用
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


	public Dict() {
		super();
	}
	
	public Dict(String id){
		super(id);
	}
	
	public Dict(String label,String value){
		this.label = label;
		this.value = value;
	}
	
	@XmlAttribute
	@Length(min=1, max=48)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@XmlAttribute
	@Length(min=1, max=48)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Length(min=1, max=80)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@XmlAttribute
	@Length(min=0, max=64)
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	public String getModuleValue() {
		return moduleValue;
	}

	public void setModuleValue(String moduleValue) {
		this.moduleValue = moduleValue;
	}

	@NotNull
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@XmlAttribute
	@Length(min=0, max=1)
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

	@XmlAttribute
	@Length(min=0, max=128)
	public String getInst() {
		return inst;
	}

	public void setInst(String inst) {
		this.inst = inst;
	}

}