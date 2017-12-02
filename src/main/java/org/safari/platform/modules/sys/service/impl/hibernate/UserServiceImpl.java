package org.safari.platform.modules.sys.service.impl.hibernate;

import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.common.service.BaseHibernateService;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.sys.dao.UserDao;
import org.safari.platform.modules.sys.entity.User;
import org.safari.platform.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户service实现类(Hibernate)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("huserService")
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseHibernateService<User, String> implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public HibernateDao<User, String> getEntityDao() {
		return userDao;
	}

	@Override
	public boolean checkPassword(User user, String oldPassword) {
		return false;
	}

	@Override
	public void updatePwd(User user) {
		
	}

	@Override
	public User findUnique(String key, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageUtil<User> findPage(PageUtil<User> page, User user) {
		return null;
	}


}
