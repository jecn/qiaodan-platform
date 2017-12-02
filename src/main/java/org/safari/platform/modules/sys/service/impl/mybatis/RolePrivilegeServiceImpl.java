package org.safari.platform.modules.sys.service.impl.mybatis;

import java.util.List;

import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.sys.entity.Privilege;
import org.safari.platform.modules.sys.entity.Role;
import org.safari.platform.modules.sys.entity.RolePrivilege;
import org.safari.platform.modules.sys.mapper.RolePrivilegeMapper;
import org.safari.platform.modules.sys.service.RolePrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("mrolePrivilegeService")
@Transactional(readOnly=true)
public class RolePrivilegeServiceImpl extends BaseMybatisService<RolePrivilegeMapper, RolePrivilege> implements RolePrivilegeService {

	@Autowired
	private RolePrivilegeMapper rolePrivilegeMapper;
	
	@Override
	public List<String> getPrivilegeIdsByRoleId(String roleId) {
		return rolePrivilegeMapper.findByRoleId(roleId);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateRolePrivilege(String roleId, List<String> privilegeList) {
		rolePrivilegeMapper.deleteByRoleId(roleId);
		
		for (int i = 0; i < privilegeList.size(); i++) {
			rolePrivilegeMapper.insert(getRolePrivilege(roleId, privilegeList.get(i)));
		}
	}

	private RolePrivilege getRolePrivilege(String roleId, String privilegeId) {
		RolePrivilege rp = new RolePrivilege();
		rp.preInsert();
		rp.setRole(new Role(roleId));
		rp.setPrivilege(new Privilege(privilegeId));
		return rp;
	}


}
