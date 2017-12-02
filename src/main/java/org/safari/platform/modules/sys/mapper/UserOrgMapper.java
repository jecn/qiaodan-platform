package org.safari.platform.modules.sys.mapper;

import java.util.List;

import org.safari.platform.common.annotation.MyBatisMapper;
import org.safari.platform.common.mapper.BaseMapper;
import org.safari.platform.modules.sys.entity.UserOrg;

/**
 * 用户Mapper接口
 * @author Alitalk
 * @date 2017-02-10
 */
@MyBatisMapper
public interface UserOrgMapper extends BaseMapper<UserOrg> {

	/**
	 * 查找用户所属机构
	 * @param userId
	 * @return
	 */
	public List<String> findByUserId(String userId);
	
	/**
	 * 删除用户所属机构
	 * @param userId
	 * @return
	 */
	public void deleteByUserId(String userId);
	
	/**
	 * 查找机构下所有用户
	 * @param orgId
	 * @return
	 */
	public List<String> findByOrgId(String orgId);
	
	/**
	 * 删除机构下所有用户
	 * @param orgId
	 * @return
	 */
	public void deleteByOrgId(String orgId);
	
}
