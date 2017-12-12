package org.safari.platform.modules.rept.service;

import java.util.List;

import org.safari.platform.modules.rept.entity.UserMoveData;


public interface UserMoveDataServiceI {

	List<UserMoveData> getUserMoveDataForTime(String beginTime, String endTime);

}
