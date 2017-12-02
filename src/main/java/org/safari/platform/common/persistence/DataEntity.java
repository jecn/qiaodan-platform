package org.safari.platform.common.persistence;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.utils.UUIDUtil;
import org.safari.platform.modules.sys.entity.User;
import org.safari.platform.modules.sys.utils.UserUtil;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 数据Entity类
 * @author Alitalk
 * @date 2017-02-10
 */
public abstract class DataEntity<T> extends BaseEntity<T>{

	private static final long serialVersionUID = 1L;
	
	/**
	 *  创建者
	 */
	protected User createBy;
	
	/**
	 * 创建日期
	 */
	protected Date createTime;
	
	/**
	 * 更新者
	 */
	protected User modifyBy;	
	
	/**
	 * 更新日期
	 */
	protected Date modifyTime;
	
	/**
	 * 删除标记（0：正常；1：删除；2：审核）
	 */
	protected String delFlag; 
	
	/**
	 * 备注
	 */
	protected String remarks;
	
	public DataEntity() {
		super();
		this.delFlag = Global.DEL_FLAG_NORMAL;
	}
	
	public DataEntity(String id) {
		super(id);
		this.delFlag = Global.DEL_FLAG_NORMAL;
	}
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord){
			setId(UUIDUtil.generate());
		}
		User user = UserUtil.getUser();
		if (StringUtil.isNotEmpty(user.getId())){
			this.createBy = user;
		}
		this.createBy = user;
		this.createTime = new Date();
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate(){
		User user = UserUtil.getUser();
		if (StringUtil.isNotEmpty(user.getId())){
			this.modifyBy = user;
		}
		this.modifyBy = user;
		this.modifyTime = new Date();
	}
	
	@JsonIgnore
	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonIgnore
	public User getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(User modifyBy) {
		this.modifyBy = modifyBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@JsonIgnore
	@Length(min=1, max=1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	@Length(min=0, max=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
