package org.safari.platform.modules.shoes.entity;

import org.hibernate.validator.constraints.Length;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.persistence.DataEntity;

/**
 *<p>Title:球鞋</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-10
 */
public class Shoes extends DataEntity<Shoes> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String code;

    /**
     * 款号
     */
    private String styleNumber;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private String price;

    /**
     * 上市时间(e_shoes_dict: shoes_market)
     */
    private String marketTime;

    /**
     * 颜色(e_shoes_dict: shoes_color)
     */
    private String color;

    /**
     * 尺码(e_shoes_dict: shoes_size)
     */
    private String size;

    /**
     * 适用人群(e_shoes_dict: shoes_people)
     */
    private String forPeople;

    /**
     * 适用场地(e_shoes: shoes_space)
     */
    private String forSpace;
    
    /**
     * 适用位置(e_shoes: shoes_position)
     */
    private String forPosition;

    /**
     * 款式(e_shoes_dict: shoes_style)
     */
    private String style;

    /**
     * 功能(e_shoes_dict: shoes_function)
     */
    private String function;

    /**
     * 状态(e_shoes_dict: shoes_stat)
     */
    private String stat;
    
    /**
     * 简介
     */
    private String intro;

    /**
     * 缩略图
     */
    private String thumb;

    /**
     * 图文说明
     */
    private String picDesc;

    /**
     * 文本说明
     */
    private String textDesc;

    /**
     * 是否可用(1 是 0 否  默认为1)
     */
    private String useable;
    
    /**
     * 是否可用
     */
    private String useableValue;
    
    public Shoes() {
		super();
		this.useable = Global.USE_FLAG_YES;
	}
	
	public Shoes(String id) {
		super(id);
		this.useable = Global.USE_FLAG_YES;
	}

	@Length(min=1,max=20)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Length(min=1,max=20)
    public String getStyleNumber() {
        return styleNumber;
    }

    public void setStyleNumber(String styleNumber) {
        this.styleNumber = styleNumber;
    }

    @Length(min=1,max=128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarketTime() {
        return marketTime;
    }

    public void setMarketTime(String marketTime) {
        this.marketTime = marketTime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getForPeople() {
        return forPeople;
    }

    public void setForPeople(String forPeople) {
        this.forPeople = forPeople;
    }

    public String getForSpace() {
        return forSpace;
    }

    public void setForSpace(String forSpace) {
        this.forSpace = forSpace;
    }
    
    public String getForPosition() {
		return forPosition;
	}

	public void setForPosition(String forPosition) {
		this.forPosition = forPosition;
	}

	public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Length(max=128)
    public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPicDesc() {
        return picDesc;
    }

    public void setPicDesc(String picDesc) {
        this.picDesc = picDesc;
    }

    public String getTextDesc() {
        return textDesc;
    }

    public void setTextDesc(String textDesc) {
        this.textDesc = textDesc;
    }

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