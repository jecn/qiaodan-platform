package org.safari.platform.modules.user.service;

import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.user.entity.Vip;

public interface VipService {
	
	public Vip get(String id);

	public PageUtil<Vip> findPage(PageUtil<Vip> page, Vip vip);
	
	public void resetPassword(String id , String password);

	public void updateStat(Vip vip);


}
