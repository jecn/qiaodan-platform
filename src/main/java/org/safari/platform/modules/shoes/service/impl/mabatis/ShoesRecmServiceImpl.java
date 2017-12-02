package org.safari.platform.modules.shoes.service.impl.mabatis;

import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.shoes.entity.ShoesRecm;
import org.safari.platform.modules.shoes.mapper.ShoesRecmMapper;
import org.safari.platform.modules.shoes.service.ShoesRecmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 球鞋推荐service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-03-30
 */
@Service("mshoesRecmService")
@Transactional(readOnly=true)
public class ShoesRecmServiceImpl extends BaseMybatisService<ShoesRecmMapper, ShoesRecm> implements ShoesRecmService {

}
