package org.safari.platform.modules.train.utils;

import java.util.List;

import org.safari.platform.common.utils.SpringContextHolderUtil;
import org.safari.platform.modules.train.entity.TrainDict;
import org.safari.platform.modules.train.mapper.TrainDictMapper;

/**
 * 训练工具类
 * @author Alitalk
 * @date 2013-5-29
 */
public class TrainUtil {
	
	private static TrainDictMapper trainDictMapper = SpringContextHolderUtil.getBean(TrainDictMapper.class);
	
	public static List<TrainDict> getNames(){
		return trainDictMapper.findAll(new TrainDict());
	}

}
