package org.safari.platform.modules.rept.service.impl.mybatis;


import java.util.List;

import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.rept.entity.UserAges;
import org.safari.platform.modules.rept.entity.UserData;
import org.safari.platform.modules.rept.entity.s_move;
import org.safari.platform.modules.rept.entity.u_vip;
import org.safari.platform.modules.rept.mapper.u_vipMapper;
import org.safari.platform.modules.rept.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl extends BaseMybatisService<u_vipMapper, u_vip> implements UserServiceI {
	
	private u_vipMapper vipMapper;
	public u_vipMapper getVipMapper(){
		return vipMapper;
	}
	
	@Autowired
	public void setVipMapper(u_vipMapper vipMapper){
		this.vipMapper = vipMapper;
	}
	
	

	public List<UserData> getUserData() {
		// TODO Auto-generated method stub
		return vipMapper.getUserData();
	}

	public List<UserData> selectTableOfActiveUser() {
		// TODO Auto-generated method stub
		return vipMapper.selectTableOfActiveUser();
	}

	public List<UserAges> getUserAges(boolean isActive) {
		// TODO Auto-generated method stub
		return vipMapper.getUserAges(isActive);
	}

	public List<UserAges> getUserPosition(boolean isActive) {
		// TODO Auto-generated method stub
		return vipMapper.getUserPosition(isActive);
	}
	public List<u_vip> getUser(boolean isActive) {
		// TODO Auto-generated method stub
		return vipMapper.getUser(isActive);
	}

	
}
