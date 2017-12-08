package org.safari.platform.modules.rept.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.safari.platform.modules.rept.entity.s_move;



public class LocationUtil {

	public static Map<String, Integer> getProvince(List<String> provinceList,
			List<s_move> moveList) {
		Map<String, Integer> provinceMap = new HashMap<String, Integer>();
		/*for (String pro : provinceList) {
			//把省份做Key
			provinceMap.put(pro, 0);
		}*/
		for (int i = 0; i<moveList.size();i++) {
			String address = moveList.get(i).getAddress();
			for(int j = 0;j<provinceList.size();j++){
				if(address.contains(provinceList.get(j).substring(0, 2))){
					//如果匹配到某个省，则把这个省的人数+1
					String province = provinceList.get(j);
					Integer proInt = provinceMap.get(provinceList.get(j)) == null ? 0 : provinceMap.get(provinceList.get(j));
					provinceMap.put(province, proInt + 1);
					continue;
				}
			}
		}
		return provinceMap;
	}
	

}
