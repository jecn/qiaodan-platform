package org.safari.platform.modules.sys.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.modules.sys.entity.Privilege;
import org.springframework.stereotype.Repository;

/**
 * 权限DAO
 * @author Alitalk
 * @date 2017-02-15
 */
@Repository
public class PrivilegeDao extends HibernateDao<Privilege, String>{

	/**
	 * 查询用户拥有的权限
	 * @param userId 用户id
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public List<Privilege> findPrivileges(String userId){
		StringBuffer sb=new StringBuffer();
		sb.append("select p.* from sys_privilege p ");
		sb.append("inner join sys_role_privilege rp on p.id = rp.privilege_id ");
		sb.append("inner join sys_role r on r.id = rp.role_id ");
		sb.append("inner join sys_user_role ur on ur.role_id = r.id ");
		sb.append("inner join sys_user u on u.id = ur.user_id ");
		sb.append("where u.id=?0 order by p.sort");
		SQLQuery sqlQuery=createSQLQuery(sb.toString(), userId);
		sqlQuery.addEntity(Privilege.class);
		return sqlQuery.list();
	}
	
	/**
	 * 查询所有的菜单
	 * @param userId
	 * @return 菜单集合
	 */
	@SuppressWarnings("unchecked")
	public List<Privilege> findMenus(){
		StringBuffer sb=new StringBuffer();
		sb.append("select p.id id,p.pid pid,p.name name,p.code code,p.type type,p.sort sort,");
		sb.append("p.icon icon,p.url url,p.useable useable,p.inst inst from sys_privilege p ");
		sb.append("where p.type=" + Global.PRI_MENU + " order by p.sort");
		SQLQuery sqlQuery=createSQLQuery(sb.toString());
		sqlQuery.addScalar("id",StandardBasicTypes.STRING );
		sqlQuery.addScalar("pid", StandardBasicTypes.STRING);
		sqlQuery.addScalar("name",StandardBasicTypes.STRING);
		sqlQuery.addScalar("code",StandardBasicTypes.STRING);
		sqlQuery.addScalar("type",StandardBasicTypes.STRING);
		sqlQuery.addScalar("sort",StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("icon",StandardBasicTypes.STRING);
		sqlQuery.addScalar("url",StandardBasicTypes.STRING);
		sqlQuery.addScalar("useable",StandardBasicTypes.STRING);
		sqlQuery.addScalar("inst",StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(Privilege.class));
		return sqlQuery.list();
	}
	
	
	/**
	 * 查询用户拥有的菜单权限
	 * @param userId
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public List<Privilege> findMenus(String userId,String type){
		StringBuffer sb=new StringBuffer();
		sb.append("select p.id id,p.pid pid,p.name name,p.code code,p.type type,p.sort sort,");
		sb.append("p.icon icon,p.url url,p.useable useable,p.inst inst from sys_privilege p ");
		sb.append("inner join sys_role_privilege rp on p.id=rp.privilege_id ");
		sb.append("inner join sys_role r on r.id=rp.role_id ");
		sb.append("inner join sys_user_role ur on ur.role_id =r.id ");
		sb.append("inner join sys_user u on u.id = ur.user_id ");
		sb.append("where p.type=" + Global.PRI_MENU + " and u.id=?0 order by p.sort");
		SQLQuery sqlQuery=createSQLQuery(sb.toString(), userId);
		sqlQuery.addScalar("id",StandardBasicTypes.STRING );
		sqlQuery.addScalar("pid", StandardBasicTypes.STRING);
		sqlQuery.addScalar("name",StandardBasicTypes.STRING);
		sqlQuery.addScalar("code",StandardBasicTypes.STRING);
		sqlQuery.addScalar("type",StandardBasicTypes.STRING);
		sqlQuery.addScalar("sort",StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("icon",StandardBasicTypes.STRING);
		sqlQuery.addScalar("url",StandardBasicTypes.STRING);
		sqlQuery.addScalar("useable",StandardBasicTypes.STRING);
		sqlQuery.addScalar("inst",StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(Privilege.class));
		return sqlQuery.list();
	}
	
	/**
	 * 查询菜单下的操作权限
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Privilege> findMenuOperation(String pid,String type){
		StringBuffer sb=new StringBuffer();
		sb.append("select p.id id,p.name name,p.code code,p.type type,p.sort sort,");
		sb.append("p.icon icon,p.url url,p.useable useable,p.inst inst from sys_privilege p ");
		sb.append("where p.pid=?0 and p.type=?1 order by p.sort");
		SQLQuery sqlQuery=createSQLQuery(sb.toString(),pid,type);
		sqlQuery.addScalar("id",StandardBasicTypes.STRING );
		sqlQuery.addScalar("name",StandardBasicTypes.STRING);
		sqlQuery.addScalar("code",StandardBasicTypes.STRING);
		sqlQuery.addScalar("type",StandardBasicTypes.STRING);
		sqlQuery.addScalar("sort",StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("icon",StandardBasicTypes.STRING);
		sqlQuery.addScalar("url",StandardBasicTypes.STRING);
		sqlQuery.addScalar("useable",StandardBasicTypes.STRING);
		sqlQuery.addScalar("inst",StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(Privilege.class));
		return sqlQuery.list();
	}
}
