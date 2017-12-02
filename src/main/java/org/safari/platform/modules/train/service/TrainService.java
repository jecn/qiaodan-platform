package org.safari.platform.modules.train.service;

import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.train.entity.Train;

public interface TrainService {

	public Train get(String id);

	public PageUtil<Train> findPage(PageUtil<Train> page, Train train);

	public void save(Train train);

	public void delete(Train train);

	

}
