package org.safari.platform.modules.shoes.service.impl.mabatis;

import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.shoes.entity.Shoes;
import org.safari.platform.modules.shoes.mapper.ShoesMapper;
import org.safari.platform.modules.shoes.service.ShoesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 球鞋service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-03-30
 */
@Service("mshoesService")
@Transactional(readOnly=true)
public class ShoesServiceImpl extends BaseMybatisService<ShoesMapper, Shoes> implements ShoesService {

}
