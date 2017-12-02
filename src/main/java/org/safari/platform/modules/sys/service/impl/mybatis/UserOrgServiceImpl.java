package org.safari.platform.modules.sys.service.impl.mybatis;

import java.util.List;

import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.sys.entity.Org;
import org.safari.platform.modules.sys.entity.User;
import org.safari.platform.modules.sys.entity.UserOrg;
import org.safari.platform.modules.sys.mapper.UserOrgMapper;
import org.safari.platform.modules.sys.service.UserOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户机构service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("muserOrgService")
@Transactional(readOnly=true)
public class UserOrgServiceImpl extends BaseMybatisService<UserOrgMapper, UserOrg> implements UserOrgService {

	@Autowired
	private UserOrgMapper userOrgMapper;
	
	@Override
	public List<String> getOrgIdList(String userId) {
		return userOrgMapper.findByUserId(userId);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateUserOrg(String userId, List<String> orgList) {
		userOrgMapper.deleteByUserId(userId);
		
		for (int i = 0; i < orgList.size(); i++) {
			userOrgMapper.insert(getUserOrg(userId, orgList.get(i)));
		}
	}

	/**
	 * 构建UserOrg
	 * 
	 * @param userId
	 * @param orgId
	 * @return UserOrg
	 */
	private UserOrg getUserOrg(String userId, String orgId) {
		UserOrg uo = new UserOrg();
		uo.preInsert();
		uo.setUser(new User(userId));
		uo.setOrg(new Org(orgId));
		return uo;
	}

}
