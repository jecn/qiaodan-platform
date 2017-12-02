package org.safari.platform.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * Mapper支持类实现
 * @author Alitalk
 * @date 2017-02-10
 * @param <T>
 */
public interface BaseMapper<T> {

	/**
	 * 插入数据
	 * @param entity
	 * @return int
	 */
	public int insert(T entity);
	
	/**
	 * 删除数据（物理删除）
	 * @param id String
	 * @return int
	 */
	public int deleteById(String id);
	
	/**
	 * 批量删除数据（物理删除）
	 * @param idList List<String>
	 * @return int
	 */
	public int deleteByIds(List<String> idList);
	
	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param entity
	 * @return int
	 */
	public int delete(T entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return int
	 */
	public int updateUseable(T entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return int
	 */
	public int update(T entity);
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return T
	 */
	public T get(String id);
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return T
	 */
	public T get(T entity);
	
	/**
	 * 唯一查询
	 * @param key String
	 * @param value String
	 * @return T
	 */
	public T findUnique(@Param("key") String key ,@Param("value")String value,@Param("delFlag")String delFlag);
	
	/**
	 * 查询数据列表
	 * @param entity
	 * @return List<T>
	 */
	public List<T> findList(T entity);
	
	/**
	 * 查询所有数据列表
	 * @param entity
	 * @return List<T>
	 */
	public List<T> findAll();
	
	/**
	 * 查询所有数据列表
	 * @param entity
	 * @return List<T>
	 */
	public List<T> findAll(T entity);
	
	/**
	 * 查找数据数目
	 * @param entity
	 * @return long
	 */
	public long findCount(T entity);
	
	/**
	 * 查找分页数据列表
	 * @param entity
	 * @return List<T>
	 */
	public List<T> findPage(T entity);
	
}