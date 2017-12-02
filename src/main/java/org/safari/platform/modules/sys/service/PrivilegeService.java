package org.safari.platform.modules.sys.service;

import java.util.List;

import org.safari.platform.modules.sys.entity.Privilege;

/**
 * 权限service
 * @author Alitalk
 * @date 2017-02-15
 */
public interface PrivilegeService{

	public List<Privilege> findAll(Privilege privilege);

	public List<Privilege> getMenus();

	public List<Privilege> getMenuOperation(String pid);

	public List<Privilege> findByUserId(String userId);

	public void save(Privilege privilege);

	void addBaseOpe(String pid, String pname);

	public Privilege get(String id);

	public void delete(Privilege privilege);

	public List<Privilege> findSubByParentId(Privilege privilege);

	public  List<Privilege> findTopMenu(Privilege privilege);

	public int deleteSubParentId(Privilege privilege);
	
	public int updateSubParentId(Privilege privilege);

	public Privilege findByPidAndName(String pid, String name);
	
	public Privilege findUnique(String key, String value);

	public List<Privilege> findAllTree(Privilege privilege);

}
