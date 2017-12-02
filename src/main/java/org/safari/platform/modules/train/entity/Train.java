package org.safari.platform.modules.train.entity;

import org.hibernate.validator.constraints.Length;
import org.safari.platform.common.persistence.DataEntity;

/**
 *<p>Title:训练</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-08
 */
public class Train extends DataEntity<Train> {

    private static final long serialVersionUID = 1L;

    /**
     * 训练字典ID
     */
    private String tdId;
    
    /**
     * 训练字典
     */
    private TrainDict trainDict;
    
    /**
     * 训练字典名称
     */
    private String dictName;
    
    /**
     * 训练字典类型
     */
    private String dictType;
    
    /**
     * 标题
     */
    private String title;
    
    /**
     * 类型
     */
    private String type;
    
    /**
     * 位置(得分后卫:SG， 控球后卫:PG， 小前锋:SF， 大前锋:PF，中锋:C)
     */
    private String position;
    
    /**
     * 简介
     */
    private String intro;

    /**
     * 缩略图
     */
    private String thumb;
    
    /**
     * 链接
     */
    private String link;

    /**
     * 内容
     */
    private String content;
    
    /**
     * 阅读次数
     */
    private Integer count;
    
    public Train(){
    	super();
    }
    
    public Train(String id){
    	super(id);
    }

	public String getTdId() {
		return tdId;
	}

	public void setTdId(String tdId) {
		this.tdId = tdId;
	}
	
	public TrainDict getTrainDict() {
		return trainDict;
	}

	public void setTrainDict(TrainDict trainDict) {
		this.trainDict = trainDict;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
    

}