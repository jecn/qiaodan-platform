package org.safari.platform.modules.sys.mapper;

import java.util.List;

import org.safari.platform.common.annotation.MyBatisMapper;
import org.safari.platform.common.mapper.BaseMapper;
import org.safari.platform.modules.sys.entity.User;

/**
 * 用户Mapper接口
 * @author Alitalk
 * @date 2017-02-10
 */
@MyBatisMapper
public interface UserMapper extends BaseMapper<User> {
	
	/**
	 * 根据登录名称查询用户
	 * @param user
	 * @return User
	 */
	public User getByUsername(User user);

	/**
	 * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return List<User>
	 */
	public List<User> findUserByOfficeId(User user);
	
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);
	
	/**
	 * 更新登录信息
	 * @param user
	 * @return int
	 */
	public int updateLoginInfo(User user);

	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return int
	 */
	public int insertUserRole(User user);
	
	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return int
	 */
	public int deleteUserRole(User user);

	/**
	 * 通过用户名获取用户信息
	 * @param user
	 * @return User
	 */
	public User findByUsername(User user);

	/**
	 * 修改密码
	 * @param user
	 */
	public void updatePwd(User user);
	
}
