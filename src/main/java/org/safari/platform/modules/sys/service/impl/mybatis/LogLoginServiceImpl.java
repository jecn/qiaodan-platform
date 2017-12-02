package org.safari.platform.modules.sys.service.impl.mybatis;

import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.sys.entity.LogLogin;
import org.safari.platform.modules.sys.mapper.LogLoginMapper;
import org.safari.platform.modules.sys.service.LogLoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 日志service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("mlogLoginService")
@Transactional(readOnly=true)
public class LogLoginServiceImpl extends BaseMybatisService<LogLoginMapper, LogLogin> implements LogLoginService {

}
