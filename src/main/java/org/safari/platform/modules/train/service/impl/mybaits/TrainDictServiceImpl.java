package org.safari.platform.modules.train.service.impl.mybaits;

import org.safari.platform.common.config.Global;
import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.train.entity.TrainDict;
import org.safari.platform.modules.train.mapper.TrainDictMapper;
import org.safari.platform.modules.train.service.TrainDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 训练字典service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-03-30
 */
@Service("mtrainDictService")
@Transactional(readOnly=true)
public class TrainDictServiceImpl extends BaseMybatisService<TrainDictMapper, TrainDict> implements TrainDictService {

	@Autowired
	private TrainDictMapper trainDictMapper;
	
	@Override
	public TrainDict findByNameAndType(String name,String type) {
		return trainDictMapper.findByNameAndType(name,type,Global.DEL_NO);
	}

}
