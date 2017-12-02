package org.safari.platform.modules.sys.dao;

import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.modules.sys.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户DAO
 * @author Alitalk
 * @date 2017-02-15
 */
@Repository
public class UserDao extends HibernateDao<User, String>{

}
