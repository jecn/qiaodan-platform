package org.safari.platform.common.service;

import java.util.List;

import org.safari.platform.common.exception.ServiceException;
import org.safari.platform.common.mapper.TreeMapper;
import org.safari.platform.common.persistence.TreeEntity;
import org.safari.platform.common.utils.ReflectionUtil;
import org.safari.platform.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 树形Service基类
 * @author Alitalk
 * @date 2016-12-09
 */
@Transactional(readOnly = true)
public abstract class TreeService<D extends TreeMapper<T>, T extends TreeEntity<T>> extends BaseMybatisService<D, T> {
	
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	@Transactional(readOnly = false)
	public void save(T entity) {
		
		@SuppressWarnings("unchecked")
		Class<T> entityClass = ReflectionUtil.getClassGenricType(getClass(), 1);
		
		// 如果没有设置父节点，则代表为跟节点，有则获取父节点实体
		if (entity.getParent() == null || StringUtil.isBlank(entity.getPid()) 
				|| "0".equals(entity.getPid())){
			entity.setParent(null);
		}else{
			entity.setParent(super.get(entity.getPid()));
		}
		if (entity.getParent() == null){
			T parentEntity = null;
			try {
				parentEntity = entityClass.getConstructor(String.class).newInstance("0");
			} catch (Exception e) {
				throw new ServiceException(e);
			}
			entity.setParent(parentEntity);
		}
		
		// 保存或更新实体
		super.save(entity);
	}
	
	/**
	 * 预留接口，用户更新子节前调用
	 * @param childEntity
	 */
	@Transactional(readOnly = false)
	public void preUpdateChild(T entity, T childEntity) {
		
	}

	/**
	 * 找到所有父节点
	 * @param entity
	 * @return List<T>
	 */
	public List<T> findSupByParentId(T entity){
		return dao.findSupByParentId(entity);
	}
	
	/**
	 * 找到所有子节点
	 * @param entity
	 * @return
	 */
	public List<T> findSubByParentId(T entity){
		return dao.findSubByParentId(entity);
	}
	
	/**
	 * 查找子节点
	 * @param entity
	 * @return
	 */
	public List<T> findByParentId(T entity){
		return dao.findByParentId(entity);
	}
	
	/**
	 * 更新所有父节点字段
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateSupParentId(T entity){
		entity.preUpdate();
		return dao.updateSupParentId(entity);
	}
	
	/**
	 * 更新所有子节点字段
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateSubParentId(T entity){
		entity.preUpdate();
		return dao.updateSubParentId(entity);
	}
	
	/**
	 * 删除所有子节点
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public int deleteSubParentId(T entity){
		return dao.deleteSubParentId(entity);
	}
	
	/**
	 * 查询所有数据
	 * @param entity T
	 * @return List<T>
	 */
	public List<T> findAllTree(T entity) {
		return dao.findAllTree(entity);
	}
}
