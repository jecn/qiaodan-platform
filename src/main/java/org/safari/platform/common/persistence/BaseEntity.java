package org.safari.platform.common.persistence;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.PropertiesUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.modules.sys.entity.User;
import org.safari.platform.modules.sys.utils.UserUtil;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;

/**
 * Entity支持类
 * @author Alitalk
 * @date 2017-02-10
 */
public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 实体编号（唯一标识）
	 */
	protected String id;
	
	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	protected boolean isNewRecord = false;
	
	/**
	 * 当前页码
	 */
	protected int pageNo = 1;	
	
	/**
	 * 每页行数
	 */
	protected int pageSize = PropertiesUtil.getInt("page.pageSize");
	
	/**
	 * 起始页标
	 */
	protected int pageIndex = 0;
	
	/**
	 * 排序字段
	 */
	protected String orderBy = "create_time";
	
	/**
	 * 排序顺序
	 */
	protected String order = "desc";	
	
	/**
	 * 下载路径
	 */
	protected String download = PropertiesUtil.getValue("download.path");
	
	/**
	 * 当前用户
	 */
	protected User currentUser;
	
	/**
	 * 自定义SQL（SQL标识，SQL内容）
	 */
	protected Map<String, String> sqlMap;
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	public abstract void preInsert();
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	public abstract void preUpdate();
	
	public BaseEntity() {
		
	}
	
	public BaseEntity(String id) {
		this();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
    /**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     * @return
     */
	public boolean getIsNewRecord() {
        return isNewRecord || StringUtil.isBlank(getId());
    }

	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	public void setIsNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@JsonIgnore
	@XmlTransient
	public User getCurrentUser() {
		if(currentUser == null){
			currentUser = UserUtil.getUser();
		}
		return currentUser;
	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@JsonIgnore
	@XmlTransient
	public Map<String, String> getSqlMap() {
		if (sqlMap == null){
			sqlMap = Maps.newHashMap();
		}
		return sqlMap;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}
	
	/**
	 * 全局变量对象
	 */
	@JsonIgnore
	public Global getGlobal() {
		return Global.getInstance();
	}
	
	/**
	 * 获取数据库名称
	 */
	@JsonIgnore
	public String getDbName(){
		return Global.getConfig("jdbc.type");
	}
	
    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
    
    public void page(HttpServletRequest request){
		if(StringUtil.isNotEmpty(request.getParameter("page"))){
			this.pageNo = Integer.valueOf(request.getParameter("page"));
		}
		if(StringUtil.isNotEmpty(request.getParameter("rows"))){
			this.pageSize = Integer.valueOf(request.getParameter("rows"));
		}
		if(StringUtil.isNotEmpty(request.getParameter("sort"))){
			this.orderBy = request.getParameter("sort").toString();
		}
		if(StringUtil.isNotEmpty(request.getParameter("order"))){
			this.order = request.getParameter("order").toString();
		}
		this.pageIndex = (pageNo -1) * pageSize;
	}
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
}
