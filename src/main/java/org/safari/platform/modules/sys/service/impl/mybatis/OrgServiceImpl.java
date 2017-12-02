package org.safari.platform.modules.sys.service.impl.mybatis;

import org.safari.platform.common.service.TreeService;
import org.safari.platform.modules.sys.entity.Org;
import org.safari.platform.modules.sys.mapper.OrgMapper;
import org.safari.platform.modules.sys.service.OrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 机构service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("morgService")
@Transactional(readOnly=true)
public class OrgServiceImpl extends TreeService<OrgMapper , Org> implements OrgService {

}
