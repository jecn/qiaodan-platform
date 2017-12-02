package org.safari.platform.modules.sys.service;

import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.sys.entity.User;

/**
 * 用户service
 * @author Alitalk
 * @date 2017-02-15
 */
public interface UserService {

	public PageUtil<User> findPage(PageUtil<User> page, User user);

	public void save(User user);

	public User get(String id);

	public void update(User user);

	public void delete(User user);

	public boolean checkPassword(User user, String oldPassword);

	public void updatePwd(User user);

	public User findUnique(String key, String username);

	public User findByUsername(String username);

}
