package org.safari.platform.modules.train.entity;

import org.safari.platform.common.persistence.DataEntity;

/**
 *<p>Title:训练字典</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-08
 */
public class TrainDict extends DataEntity<TrainDict> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;
    
    /**
     * 状态(1 推荐训练 2 其他训练)
     */
    private String type;
    
    public TrainDict(){
    	super();
    }
    
    public TrainDict(String id){
    	super(id);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}