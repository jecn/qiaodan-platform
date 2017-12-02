package org.safari.platform.modules.shoes.entity;

import org.safari.platform.common.persistence.DataEntity;

/**
 *<p>Title:球鞋图片</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-10
 */
public class ShoesPicture extends DataEntity<ShoesPicture> {

    private static final long serialVersionUID = 1L;

    /**
     * 球鞋ID
     */
    private String shoesId;

    /**
     * 图片
     */
    private String picture;
    
    public ShoesPicture(){
    	super();
    }
    
    public ShoesPicture(String id) {
		super(id);
	}

    public String getShoesId() {
        return shoesId;
    }

    public void setShoesId(String shoesId) {
        this.shoesId = shoesId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}