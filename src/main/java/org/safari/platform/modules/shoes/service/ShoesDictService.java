package org.safari.platform.modules.shoes.service;

import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.shoes.entity.ShoesDict;

public interface ShoesDictService {

	public ShoesDict get(String id);
	
	public PageUtil<ShoesDict> findPage(PageUtil<ShoesDict> page, ShoesDict dict);

	public void save(ShoesDict dict);
	
	public void delete(ShoesDict dict);

	public void update(ShoesDict dict);

	public ShoesDict findByTypeAndLabel(String type, String label);
	
	public void updateUseable(ShoesDict dict);


}
