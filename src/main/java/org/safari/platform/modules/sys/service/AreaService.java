package org.safari.platform.modules.sys.service;

import java.util.List;

import org.safari.platform.modules.sys.entity.Area;

/**
 * 区域service
 * @author Alitalk
 * @date 2017-02-15
 */
public interface AreaService{

	public List<Area> findAll(Area area);

	public void save(Area area);

	public Area get(String id);

	public void update(Area area);

	public int deleteSubParentId(Area area);

	public int updateSubParentId(Area area);

	public List<Area> findAllTree(Area area);
	
}
