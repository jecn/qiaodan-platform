package org.safari.platform.modules.sys.service.impl.mybatis;

import java.util.List;

import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.sys.entity.Role;
import org.safari.platform.modules.sys.entity.User;
import org.safari.platform.modules.sys.entity.UserRole;
import org.safari.platform.modules.sys.mapper.UserRoleMapper;
import org.safari.platform.modules.sys.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("muserRoleService")
@Transactional(readOnly=true)
public class UserRoleServiceImpl extends BaseMybatisService<UserRoleMapper, UserRole> implements UserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public List<String> getRoleIdList(String userId) {
		return userRoleMapper.findByUserId(userId);
	}
	
	@Override
	public List<String> getUserIdList(String roleId) {
		return userRoleMapper.findByRoleId(roleId);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateUserRole(String userId, List<String> roleList) {
		userRoleMapper.deleteByUserId(userId);
		
		for (int i = 0; i < roleList.size(); i++) {
			userRoleMapper.insert(getUserRole(userId, roleList.get(i)));
		}
	}

	/**
	 * 构建UserRole
	 * 
	 * @param userId
	 * @param roleId
	 * @return UserRole
	 */
	private UserRole getUserRole(String userId, String roleId) {
		UserRole ur = new UserRole();
		ur.preInsert();
		ur.setUser(new User(userId));
		ur.setRole(new Role(roleId));
		return ur;
	}

	
}
