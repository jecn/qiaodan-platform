package org.safari.platform.modules.sys.service;

import java.util.List;

import org.safari.platform.modules.sys.entity.Media;

public interface MediaService {

	public void deleteById(String id);

	public Media get(String id);

	public List<Media> findMedias(String ids);

}
