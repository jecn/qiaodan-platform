package org.safari.platform.modules.sys.service;

import java.util.List;

public interface UserRoleService {

	public List<String> getRoleIdList(String userId);

	public void updateUserRole(String userId, List<String> roleList);

	public List<String> getUserIdList(String roleId);

}
