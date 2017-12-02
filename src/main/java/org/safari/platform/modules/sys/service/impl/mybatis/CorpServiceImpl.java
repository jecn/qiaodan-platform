package org.safari.platform.modules.sys.service.impl.mybatis;

import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.sys.entity.Corp;
import org.safari.platform.modules.sys.mapper.CorpMapper;
import org.safari.platform.modules.sys.service.CorpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单位service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-03-30
 */
@Service("mcorpService")
@Transactional(readOnly=true)
public class CorpServiceImpl extends BaseMybatisService<CorpMapper, Corp> implements CorpService {


}
