package org.safari.platform.modules.user.service.mybatis;

import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.user.entity.Vip;
import org.safari.platform.modules.user.mapper.VipMapper;
import org.safari.platform.modules.user.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-03-30
 */
@Service("mvipService")
@Transactional(readOnly=true)
public class VipServiceImpl extends BaseMybatisService<VipMapper, Vip> implements VipService {

	@Autowired
	private VipMapper vipMapper;
	
	@Override
	@Transactional
	public void resetPassword(String id, String password) {
		vipMapper.resetPassword(id, password);
	}

	@Override
	@Transactional
	public void updateStat(Vip vip) {
		vipMapper.updateStat(vip);
	}

}
