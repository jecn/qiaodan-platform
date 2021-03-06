package org.safari.platform.modules.sys.dao;

import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.modules.sys.entity.LogLogin;
import org.springframework.stereotype.Repository;

/**
 * 登录日志DAO
 * @author Alitalk
 * @date 2017-02-15
 */
@Repository
public class LogLoginDao extends HibernateDao<LogLogin, String>{

}
