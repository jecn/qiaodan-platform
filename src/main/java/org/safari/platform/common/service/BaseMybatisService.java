package org.safari.platform.common.service;

import java.util.List;

import org.safari.platform.common.config.Global;
import org.safari.platform.common.mapper.BaseMapper;
import org.safari.platform.common.persistence.BaseEntity;
import org.safari.platform.common.persistence.DataEntity;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

/**
 * Service基类
 * @author Alitalk
 * @date 2017-02-16
 */
@Transactional(readOnly = true)
public abstract class BaseMybatisService<D extends BaseMapper<T>, T extends DataEntity<T>> {
	
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	/**
	 * 获取单条数据
	 * @param id String
	 * @return T
	 */
	public T get(String id) {
		return dao.get(id);
	}
	
	/**
	 * 获取单条数据
	 * @param entity T
	 * @return T
	 */
	public T get(T entity) {
		return dao.get(entity);
	}
	
	/**
	 * 唯一性查询
	 * @param key String
	 * @param value String 
	 * @return T
	 */
	public T findUnique(String key ,String value){
		return dao.findUnique(key, value, Global.DEL_NO);
	}
	
	/**
	 * 查询列表数据
	 * @param entity T
	 * @return List<T>
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}
	
	/**
	 * 查询分页数据
	 * @param page PageUtil<T> 分页对象
	 * @param entity T
	 * @return PageUtil<T>
	 */
	public PageUtil<T> findPage(PageUtil<T> page, T entity) {
		page.setCount(dao.findCount(entity));
		page.setList(dao.findPage(entity));
		return page;
	}
	
	/**
	 * 查询所有数据
	 * @return List<T>
	 */
	public List<T> findAll() {
		return dao.findAll();
	}
	
	/**
	 * 查询所有数据
	 * @param entity T
	 * @return List<T>
	 */
	public List<T> findAll(T entity) {
		return dao.findAll(entity);
	}

	/**
	 * 增加数据
	 * @param entity T
	 */
	@Transactional(readOnly = false)
	public void insert(T entity) {
		entity.preInsert();
		dao.insert(entity);
	}
	
	/**
	 * 修改数据
	 * @param entity T
	 */
	@Transactional(readOnly = false)
	public void updateUseable(T entity) {
		entity.preUpdate();
		dao.updateUseable(entity);
	}
	
	/**
	 * 修改数据
	 * @param entity T
	 */
	@Transactional(readOnly = false)
	public void update(T entity) {
		entity.preUpdate();
		dao.update(entity);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param entity T
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}
	
	/**
	 * 删除数据 (物理删除)
	 * @param id String
	 */
	@Transactional(readOnly = false)
	public void delete(String id) {
		dao.deleteById(id);
	}
	
	/**
	 * 删除数据 (物理删除)
	 * @param idList List<String>
	 */
	@Transactional(readOnly = false)
	public void delete(List<String> idList) {
		dao.deleteByIds(idList);
	}
	
	/**
	 * 删除数据 (逻辑删除)
	 * @param entity T
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}

	/**
	 * 数据范围过滤
	 * @param user 当前用户对象，通过“entity.getCurrentUser()”获取
	 * @param officeAlias 机构表别名，多个用“,”逗号隔开。
	 * @param userAlias 用户表别名，多个用“,”逗号隔开，传递空，忽略此参数
	 * @return 标准连接条件对象
	 */
	public static String dataScopeFilter(User user, String officeAlias, String userAlias) {

		StringBuilder sqlString = new StringBuilder();
		
		// 进行权限过滤，多个角色权限范围之间为或者关系。
		List<String> dataScope = Lists.newArrayList();
		
		// 超级管理员，跳过权限过滤
		if (!user.isAdmin()){
			/*boolean isDataScopeAll = false;
			for (Role r : user.getRoleList()){
				for (String oa : StringUtils.split(officeAlias, ",")){
					if (!dataScope.contains(r.getDataScope()) && StringUtils.isNotBlank(oa)){
						if (Role.DATA_SCOPE_ALL.equals(r.getDataScope())){
							isDataScopeAll = true;
						}
						else if (Role.DATA_SCOPE_COMPANY_AND_CHILD.equals(r.getDataScope())){
							sqlString.append(" or " + oa + ".id = '" + user.getCompany().getId() + "'");
							sqlString.append(" or " + oa + ".parent_id ='" + user.getCompany().getId() +"'");
						}
						else if (Role.DATA_SCOPE_COMPANY.equals(r.getDataScope())){
							sqlString.append(" or " + oa + ".id = '" + user.getCompany().getId() + "'");
							// 包括本公司下的部门 （type=1:公司；type=2：部门）
							sqlString.append(" or (" + oa + ".parent_id ='" + user.getCompany().getId() + "' and " + oa + ".type = '2')");
						}
						else if (Role.DATA_SCOPE_OFFICE_AND_CHILD.equals(r.getDataScope())){
							sqlString.append(" or " + oa + ".id = '" + user.getOffice().getId() + "'");
							sqlString.append(" or " + oa + ".parent_id ='" + user.getOffice().getId() + "'");
						}
						else if (Role.DATA_SCOPE_OFFICE.equals(r.getDataScope())){
							sqlString.append(" or " + oa + ".id = '" + user.getOffice().getId() + "'");
						}
						else if (Role.DATA_SCOPE_CUSTOM.equals(r.getDataScope())){
//							String officeIds =  StringUtils.join(r.getOfficeIdList(), "','");
//							if (StringUtils.isNotEmpty(officeIds)){
//								sqlString.append(" OR " + oa + ".id IN ('" + officeIds + "')");
//							}
							sqlString.append(" or exists (select 1 from sys_role_office where role_id = '" + r.getId() + "'");
							sqlString.append(" and office_id = " + oa +".id)");
						}
						//else if (Role.DATA_SCOPE_SELF.equals(r.getDataScope())){
						dataScope.add(r.getDataScope());
					}
				}
			}*/
			// 如果没有全部数据权限，并设置了用户别名，则当前权限为本人；如果未设置别名，当前无权限为已植入权限
			/*if (!isDataScopeAll){
				if (StringUtils.isNotBlank(userAlias)){
					for (String ua : StringUtils.split(userAlias, ",")){
						sqlString.append(" or " + ua + ".id = '" + user.getId() + "'");
					}
				}else {
					for (String oa : StringUtils.split(officeAlias, ",")){
						//sqlString.append(" OR " + oa + ".id  = " + user.getOffice().getId());
						sqlString.append(" or " + oa + ".id is null");
					}
				}
			}else{
				// 如果包含全部权限，则去掉之前添加的所有条件，并跳出循环。
				sqlString = new StringBuilder();
			}*/
		}
		if (StringUtil.isNotBlank(sqlString.toString())){
			return " and (" + sqlString.substring(4) + ")";
		}
		return "";
	}

	/**
	 * 数据范围过滤（符合业务表字段不同的时候使用，采用exists方法）
	 * @param entity 当前过滤的实体类
	 * @param sqlMapKey sqlMap的键值，例如设置“dsf”时，调用方法：${sqlMap.sdf}
	 * @param officeWheres office表条件，组成：部门表字段=业务表的部门字段
	 * @param userWheres user表条件，组成：用户表字段=业务表的用户字段
	 * @example
	 * 		dataScopeFilter(user, "dsf", "id=a.office_id", "id=a.create_by");
	 * 		dataScopeFilter(entity, "dsf", "code=a.jgdm", "no=a.cjr"); // 适应于业务表关联不同字段时使用，如果关联的不是机构id是code。
	 */
	public static void dataScopeFilter(BaseEntity entity, String sqlMapKey, String officeWheres, String userWheres) {

		User user = entity.getCurrentUser();
		
		// 如果是超级管理员，则不过滤数据
		if (user.isAdmin()) {
			return;
		}

		// 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置）
		StringBuilder sqlString = new StringBuilder();
		
		// 获取到最大的数据权限范围
		String roleId = "";
		int dataScopeInteger = 8;
		/*for (Role r : user.getRoleList()){
			int ds = Integer.valueOf(r.getDataScope());
			if (ds == 9){
				roleId = r.getId();
				dataScopeInteger = ds;
				break;
			}else if (ds < dataScopeInteger){
				roleId = r.getId();
				dataScopeInteger = ds;
			}
		}*/
		String dataScopeString = String.valueOf(dataScopeInteger);
		
		// 生成部门权限SQL语句
		/*for (String where : StringUtils.split(officeWheres, ",")){
			if (Role.DATA_SCOPE_COMPANY_AND_CHILD.equals(dataScopeString)){
				// 包括本公司下的部门 （type=1:公司；type=2：部门）
				sqlString.append(" and exists (select 1 from sys_office");
				sqlString.append(" where type='2'");
				sqlString.append(" and (id = '" + user.getCompany().getId() + "'");
				sqlString.append(" or parent_id = '" + user.getCompany().getId() + "')");
				sqlString.append(" and " + where +")");
			}
			else if (Role.DATA_SCOPE_COMPANY.equals(dataScopeString)){
				sqlString.append(" and exists (select 1 from sys_office");
				sqlString.append(" where type='2'");
//				sqlString.append(" and id = '" + user.getCompany().getId() + "'");
				sqlString.append(" and " + where +")");
			}
			else if (Role.DATA_SCOPE_OFFICE_AND_CHILD.equals(dataScopeString)){
				sqlString.append(" and exists (select 1 from sys_office");
				sqlString.append(" where (id = '" + user.getOffice().getId() + "'");
				sqlString.append(" or parent_id= '" + user.getOffice().getId() + "')");
				sqlString.append(" and " + where +")");
			}
			else if (Role.DATA_SCOPE_OFFICE.equals(dataScopeString)){
				sqlString.append(" and exists (select 1 from sys_office");
//				sqlString.append(" where id = '" + user.getOffice().getId() + "'");
				sqlString.append(" and " + where +")");
			}
			else if (Role.DATA_SCOPE_CUSTOM.equals(dataScopeString)){
				sqlString.append(" and exists (select 1 from sys_role_office ro123456, sys_office o123456");
				sqlString.append(" where ro123456.office_id = o123456.id");
				sqlString.append(" and ro123456.role_id = '" + roleId + "'");
				sqlString.append(" and o123456." + where +")");
			}
		}
		// 生成个人权限SQL语句
		for (String where : StringUtils.split(userWheres, ",")){
			if (Role.DATA_SCOPE_SELF.equals(dataScopeString)){
				sqlString.append(" and exists (select 1 from sys_user");
				sqlString.append(" where id='" + user.getId() + "'");
				sqlString.append(" and " + where + ")");
			}
		}*/

//		System.out.println("dataScopeFilter: " + sqlString.toString());

		// 设置到自定义SQL对象
		entity.getSqlMap().put(sqlMapKey, sqlString.toString());
		
	}
	
}
