package org.safari.platform.modules.sys.service.impl.mybatis;

import java.util.List;

import org.safari.platform.modules.sys.entity.Media;
import org.safari.platform.modules.sys.mapper.MediaMapper;
import org.safari.platform.modules.sys.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mmediaService")
@Transactional(readOnly=true)
public class MediaServiceImpl implements MediaService {

	@Autowired
	private MediaMapper mediaMapper;
	
	@Override
	public Media get(String id) {
		return mediaMapper.get(id);
	}
	
	@Override
	@Transactional
	public void deleteById(String id) {
		mediaMapper.deleteById(id);
	}

	@Override
	public List<Media> findMedias(String ids) {
		return mediaMapper.findByIds(ids.split(","));
	}

	

}
