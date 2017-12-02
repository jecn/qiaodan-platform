package org.safari.platform.modules.shoes.entity;

import org.hibernate.validator.constraints.Length;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.persistence.DataEntity;

/**
 *<p>Title:球鞋推荐</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-05-04
 */
public class ShoesRecm extends DataEntity<ShoesRecm> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 缩略图
     */
    private String thumb;

    /**
     * 类别
     */
    private String intro;

    /**
     * 链接
     */
    private String link;

    /**
     * 是否可用(1 是 0 否  默认为1)
     */
    private String useable;
    
    /**
     * 是否可用
     */
    private String useableValue;
    
    public ShoesRecm() {
		super();
		this.useable = Global.USE_FLAG_YES;
	}
	
	public ShoesRecm(String id) {
		super(id);
		this.useable = Global.USE_FLAG_YES;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name ;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb ;
    }

    @Length(max=128)
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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