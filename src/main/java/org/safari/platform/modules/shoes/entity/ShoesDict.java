package org.safari.platform.modules.shoes.entity;

import org.hibernate.validator.constraints.Length;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.persistence.DataEntity;

/**
 *<p>Title:球鞋字典</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-10
 */
public class ShoesDict extends DataEntity<ShoesDict> {

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
     * 类别
     */
    private String type;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String inst;

    /**
     * 是否可用(1 是 0 否  默认为1)
     */
    private String useable;
    
    /**
     * 是否可用
     */
    private String useableValue;
    
    public ShoesDict() {
		super();
		this.useable = Global.USE_FLAG_YES;
	}
	
	public ShoesDict(String id) {
		super(id);
		this.useable = Global.USE_FLAG_YES;
	}

	@Length(max=32)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Length(max=80)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Length(max=48)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Length(max=128)
    public String getInst() {
        return inst;
    }

    public void setInst(String inst) {
        this.inst = inst;
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
    
}