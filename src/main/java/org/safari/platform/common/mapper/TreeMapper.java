package org.safari.platform.common.mapper;

import java.util.List;

import org.safari.platform.common.persistence.TreeEntity;

/**
 * DAO支持类实现
 * @author Alitalk
 * @date 2017-02-10
 * @param <T>
 */
public interface TreeMapper<T extends TreeEntity<T>> extends BaseMapper<T> {

	/**
	 * 找到所有父节点
	 * @param entity
	 * @return List<T>
	 */
	public List<T> findSupByParentId(T entity);
	
	/**
	 * 找到所有子节点
	 * @param parentId
	 * @return
	 */
	public List<T> findSubByParentId(T entity);
	
	/**
	 * 查找子节点
	 * @param entity
	 * @return
	 */
	public List<T> findByParentId(T entity);
	
	/**
	 * 更新所有父节点字段
	 * @param entity
	 * @return
	 */
	public int updateSupParentId(T entity);
	
	/**
	 * 更新所有子节点字段
	 * @param entity
	 * @return
	 */
	public int updateSubParentId(T entity);
	
	/**
	 * 删除所有子节点
	 * @param entity
	 * @return
	 */
	public int deleteSubParentId(T entity);
	
	/**
	 * 查找所有树形数据
	 * @param entity
	 * @return
	 */
	public List<T> findAllTree(T entity);
	
}