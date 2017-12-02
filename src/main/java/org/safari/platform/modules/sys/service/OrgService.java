package org.safari.platform.modules.sys.service;

import java.util.List;

import org.safari.platform.modules.sys.entity.Org;

/**
 * 区域service
 * @author Alitalk
 * @date 2017-02-15
 */
public interface OrgService {

	public List<Org> findAll(Org org);

	public void save(Org org);

	public Org get(String id);

	public void update(Org org);

	public void delete(Org org);

	public int deleteSubParentId(Org org);

	public int updateSubParentId(Org org);

	public List<Org> findAllTree(Org org);

}
