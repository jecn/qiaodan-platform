package org.safari.platform.modules.rept.service;

import java.util.List;

import org.safari.platform.modules.rept.entity.UserAges;
import org.safari.platform.modules.rept.entity.UserData;
import org.safari.platform.modules.rept.entity.s_move;


public interface UserServiceI {
	public List<UserData> getUserData();
	public List<UserData> selectTableOfActiveUser();
	public List<UserAges> getUserAges(boolean isActive);
	public List<UserAges> getUserPosition(boolean isActive);
	

}
