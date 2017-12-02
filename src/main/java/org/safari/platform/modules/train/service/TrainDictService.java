package org.safari.platform.modules.train.service;

import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.train.entity.TrainDict;

public interface TrainDictService {

	public TrainDict get(String id);

	public PageUtil<TrainDict> findPage(PageUtil<TrainDict> page, TrainDict dict);

	public void save(TrainDict dict);

	public void delete(TrainDict dict);

	public TrainDict findByNameAndType(String name, String type);
}
