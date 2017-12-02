package org.safari.platform.modules.sys.service.impl.mybatis;

import java.util.List;

import org.safari.platform.common.config.Global;
import org.safari.platform.common.service.TreeService;
import org.safari.platform.modules.sys.entity.Privilege;
import org.safari.platform.modules.sys.entity.User;
import org.safari.platform.modules.sys.mapper.PrivilegeMapper;
import org.safari.platform.modules.sys.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("mprivilegeService")
@Transactional(readOnly=true)
public class PrivilegeServiceImpl extends TreeService<PrivilegeMapper , Privilege> implements PrivilegeService {

	@Autowired
	private PrivilegeMapper privilegeMapper;
	
	@Override
	public List<Privilege> getMenus() {
		Privilege privilege = new Privilege();
		privilege.setType(Global.PRI_MENU);
		return privilegeMapper.findAll(privilege);
	}

	@Override
	public List<Privilege> getMenuOperation(String pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Privilege> findByUserId(String userId) {
		return privilegeMapper.findByUser(new Privilege(new User(userId)));
	}

	@Override
	public void addBaseOpe(String pid, String pname) {
		
	}

	@Override
	public List<Privilege> findTopMenu(Privilege privilege) {
		return privilegeMapper.findTopMenu(privilege);
	}

	@Override
	public Privilege findByPidAndName(String pid, String name) {
		return privilegeMapper.findByPidAndName(pid,name,Global.DEL_NO);
	}


}
