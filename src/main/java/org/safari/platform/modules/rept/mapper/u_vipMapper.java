package org.safari.platform.modules.rept.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.platform.common.annotation.MyBatisMapper;
import org.safari.platform.common.mapper.BaseMapper;
import org.safari.platform.modules.rept.entity.UserAges;
import org.safari.platform.modules.rept.entity.UserData;
import org.safari.platform.modules.rept.entity.u_vip;

@MyBatisMapper
public interface u_vipMapper extends BaseMapper<UserData>{
    int deleteByPrimaryKey(String id);

    int insert(u_vip record);

    int insertSelective(u_vip record);

    u_vip selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(u_vip record);

    int updateByPrimaryKey(u_vip record);
    List<UserData> getUserData();

	List<UserData> getUserDataForTime(@Param("beginTime")String beginTime, @Param("endTime")String endTime);

	List<UserData> selectTableOfActiveUser();

	List<UserAges> getUserAges(@Param("isActive")boolean isActive);

	List<UserAges> getUserPosition(@Param("isActive")boolean isActive);

}