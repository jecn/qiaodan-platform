package org.safari.platform.modules.shoes.service;

import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.shoes.entity.Shoes;

public interface ShoesService {

	public Shoes get(String id);

	public PageUtil<Shoes> findPage(PageUtil<Shoes> page, Shoes shoes);

	public void save(Shoes shoes);

	public void delete(Shoes shoes);

	public void update(Shoes shoes);

	public Shoes findUnique(String key, String code);
	
	public void updateUseable(Shoes shoes);

}
