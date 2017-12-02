package org.safari.platform.modules.shoes.service;

import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.shoes.entity.ShoesRecm;

public interface ShoesRecmService {

	public ShoesRecm get(String id);

	public void save(ShoesRecm recm);

	public void delete(ShoesRecm recm);

	public void update(ShoesRecm recm);

	public PageUtil<ShoesRecm> findPage(PageUtil<ShoesRecm> page, ShoesRecm recm);

	public void updateUseable(ShoesRecm recm);

}
