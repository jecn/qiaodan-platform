package org.safari.platform.modules.shoes.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.mapper.JsonMapper;
import org.safari.platform.common.utils.CacheUtil;
import org.safari.platform.common.utils.SpringContextHolderUtil;
import org.safari.platform.modules.shoes.entity.ShoesDict;
import org.safari.platform.modules.shoes.mapper.ShoesDictMapper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 球鞋工具类
 * @author Alitalk
 * @date 2013-5-29
 */
public class ShoesUtil {
	
	private static ShoesDictMapper shoesDictMapper = SpringContextHolderUtil.getBean(ShoesDictMapper.class);

	public static final String CACHE_DICT_MAP = "shoes_dictMap";
	
	public static String getShoesDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (ShoesDict dict : getShoesDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getShoesDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getShoesDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getShoesDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (ShoesDict dict : getShoesDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<ShoesDict> getShoesDictList(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<ShoesDict>> dictMap = (Map<String, List<ShoesDict>>)CacheUtil.get(CACHE_DICT_MAP);
		if (dictMap==null || dictMap.size() < 1){
			ShoesDict dict = new ShoesDict();
			dict.setUseable(Global.USE_FLAG_YES);
			List<ShoesDict> list = shoesDictMapper.findAll(dict);
			dictMap = Maps.newHashMap();
			for (ShoesDict shoesDict : list){
				List<ShoesDict> dictList = dictMap.get(shoesDict.getType());
				if (dictList != null){
					dictList.add(shoesDict);
				}else{
					dictMap.put(shoesDict.getType(), Lists.newArrayList(shoesDict));
				}
			}
			CacheUtil.put(CACHE_DICT_MAP, dictMap);
		}
		List<ShoesDict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
	/**
	 * 返回字典列表（JSON）
	 * @param type
	 * @return
	 */
	public static String getShoesDictListJson(String type){
		return JsonMapper.toJsonString(getShoesDictList(type));
	}
}
