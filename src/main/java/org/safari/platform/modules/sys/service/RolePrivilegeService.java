package org.safari.platform.modules.sys.service;

import java.util.List;

public interface RolePrivilegeService {

	public List<String> getPrivilegeIdsByRoleId(String roleId);

	public void updateRolePrivilege(String roleId, List<String> privilegeList);

}
