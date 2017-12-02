package org.safari.platform.modules.train.service.impl.mybaits;

import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.train.entity.Train;
import org.safari.platform.modules.train.mapper.TrainMapper;
import org.safari.platform.modules.train.service.TrainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 训练service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-03-30
 */
@Service("mtrainService")
@Transactional(readOnly=true)
public class TrainServiceImpl extends BaseMybatisService<TrainMapper, Train> implements TrainService {

}
