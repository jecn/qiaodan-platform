package org.safari.platform.modules.sys.service.impl.mybatis;


import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.sys.entity.User;
import org.safari.platform.modules.sys.mapper.UserMapper;
import org.safari.platform.modules.sys.service.UserService;
import org.safari.platform.modules.sys.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("muserService")
@Transactional(readOnly=true)
public class UserServiceImpl extends BaseMybatisService<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean checkPassword(User user, String oldPassword) {
		user = userMapper.get(user);
		return false;
	}

	@Override
	public void updatePwd(User user) {
		userMapper.updatePwd(user);
	}

	@Override
	public User findByUsername(String username) {
		return UserUtil.getByUsername(username);
	}

}
