package org.safari.platform.modules.rept.service.impl.mybatis;

import java.util.List;

import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.rept.entity.s_move;
import org.safari.platform.modules.rept.mapper.s_moveMapper;
import org.safari.platform.modules.rept.service.MoveServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("moveService")
public class MoveServiceImpl extends BaseMybatisService<s_moveMapper, s_move> implements MoveServiceI {

	@Autowired
	private s_moveMapper moveMapper;
	@Override
	public List<s_move> getUserLoction() {
		// TODO Auto-generated method stub
		return moveMapper.getUserLocation();
	}
	@Override
	public List<String> getProvince() {
		// TODO Auto-generated method stub
		return moveMapper.getProvince();
	}

}
