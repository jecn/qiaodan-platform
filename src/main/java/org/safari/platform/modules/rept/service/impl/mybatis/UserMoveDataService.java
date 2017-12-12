package org.safari.platform.modules.rept.service.impl.mybatis;

import java.util.List;

import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.rept.entity.UserMoveData;
import org.safari.platform.modules.rept.mapper.UserMoveDataMapper;
import org.safari.platform.modules.rept.service.UserMoveDataServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userMoveDataService")
public class UserMoveDataService extends BaseMybatisService<UserMoveDataMapper, UserMoveData> implements
		UserMoveDataServiceI {
	@Autowired
	private UserMoveDataMapper userMoveDataMapper;

	@Override
	public List<UserMoveData> getUserMoveDataForTime(String beginTime, String endTime) {
		// TODO Auto-generated method stub
		return userMoveDataMapper.getUserMoveDataForTime(beginTime, endTime);
	}



}
