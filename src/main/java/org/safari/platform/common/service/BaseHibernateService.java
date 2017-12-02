package org.safari.platform.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.common.filter.PropertyFilter;
import org.safari.platform.common.utils.PageUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 基础service 所有service继承该类
 * 提供基础的实体操作方法
 * 使用HibernateDao<T,PK>进行业务对象的CRUD操作,子类需重载getEntityDao()函数提供该DAO.
 * @author Alitalk
 * @date 2017-02-10
 * @param <T>
 * @param <PK>
 */
@Transactional(readOnly = true)
public abstract class BaseHibernateService<T,PK extends Serializable > {
	
	/**
	 * 子类需要实现该方法，提供注入的dao
	 * @return
	 */
	public abstract HibernateDao<T, PK> getEntityDao();
	
	public T get(final PK id) {
		return getEntityDao().find(id);
	}

	@Transactional(readOnly = false)
	public void save(final T entity) {
		getEntityDao().save(entity);
	}
	
	@Transactional(readOnly = false)
	public void update(final T entity){
		getEntityDao().save(entity);
	}
	
	@Transactional(readOnly = false)
	public void delete(final T entity){
		getEntityDao().save(entity);
	}
	
	@Transactional(readOnly = false)
	public void delete(final PK id){
		getEntityDao().delete(id);
	}
	
	@Transactional(readOnly = false)
	public void delete(final List<String> idList){
		// TODO
	}
	
	public List<T> findAll(){
		return getEntityDao().findAll();
	}
	
	public List<T> findAll(T entity){
		// TODO
		return null;
	}
	
	public List<T> findAll(Boolean isCache){
		return getEntityDao().findAll(isCache);
	}
	
	public List<T> findPage(final List<PropertyFilter> filters){
		return getEntityDao().find(filters);
	}
	
	public PageUtil<T> findPage(final PageUtil<T> page, final List<PropertyFilter> filters) {
		return getEntityDao().findPage(page, filters);
	}
	
	public PageUtil<T> findPage(final PageUtil<T> page, final String hql,final Map<String, ?> values) {
		return getEntityDao().findPage(page, hql, values);
	}
	
	public PageUtil<T> search(final PageUtil<T> page, final String hql,final Object... values) {
		return getEntityDao().findPage(page, hql, values);
	}
	
}