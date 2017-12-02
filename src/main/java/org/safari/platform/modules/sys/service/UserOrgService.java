package org.safari.platform.modules.sys.service;

import java.util.List;

public interface UserOrgService {

	public List<String> getOrgIdList(String userId);

	public void updateUserOrg(String userId, List<String> orgList);

}
