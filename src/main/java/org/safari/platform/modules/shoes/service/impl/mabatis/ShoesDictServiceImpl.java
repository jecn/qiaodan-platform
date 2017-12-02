package org.safari.platform.modules.shoes.service.impl.mabatis;

import org.safari.platform.common.config.Global;
import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.shoes.entity.ShoesDict;
import org.safari.platform.modules.shoes.mapper.ShoesDictMapper;
import org.safari.platform.modules.shoes.service.ShoesDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 球鞋字典service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-03-30
 */
@Service("mshoesDictService")
@Transactional(readOnly=true)
public class ShoesDictServiceImpl extends BaseMybatisService<ShoesDictMapper, ShoesDict> implements ShoesDictService {

	@Autowired
	private ShoesDictMapper shoesDictMapper;
	
	@Override
	public ShoesDict findByTypeAndLabel(String type, String label) {
		return shoesDictMapper.findByTypeAndLabel(type,label,Global.DEL_NO);
	}

}
