package org.safari.platform.modules.rept.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.rept.entity.UserAges;
import org.safari.platform.modules.rept.entity.UserData;
import org.safari.platform.modules.rept.entity.s_move;
import org.safari.platform.modules.rept.service.MoveServiceI;
import org.safari.platform.modules.rept.service.UserServiceI;
import org.safari.platform.modules.rept.util.LocationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("${adminPath}/rept")
public class ReptChartUser extends BaseController{
	private static final Logger logger = Logger.getLogger(ReptChartUser.class);
	@Autowired
	private UserServiceI userService;
	
	@Autowired
	private MoveServiceI moveService;
	
	/* 
	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}*/
	/**
	 * 注册用户统计页
	 */
	@RequestMapping(value="/chartUser",method = RequestMethod.GET)
	public String chartUser() {
		return "rept/chartUser";
	}
	/**
	 * 注册活跃用户统计页
	 */
	@RequestMapping(value="/chartActiveUser",method = RequestMethod.GET)
	public String chartActiveUser() {
		return "rept/chartActiveUser";
	}
	/**
	 * 用户打球位置
	 */
	@RequestMapping(value="/userPosition",method = RequestMethod.GET)
	public String userPosition() {
		return "rept/chartUserPosition";
	}
	/**
	 * 活跃用户打球位置
	 */
	@RequestMapping(value="/activeUserPosition",method = RequestMethod.GET)
	public String chartActiveUserPosition() {
		return "rept/chartActiveUserPosition";
	}
	
	/**
	 * 注册用户年龄段
	 */
	@RequestMapping(value="/userAges",method = RequestMethod.GET)
	public String userAges() {
		return "rept/chartUserAges";
	}
	
	/**
	 * 注册活跃用户年龄段
	 */
	@RequestMapping(value="/activeUserAges",method = RequestMethod.GET)
	public String activeUserAges() {
		return "rept/chartActiveUserAges";
	}
	/**
	 * 注册用户省份统计
	 */
	@RequestMapping(value="/userLocation",method = RequestMethod.GET)
	public String chartUserLocation() {
		return "rept/chartUserLocation";
	}
	/**
	 * 用户身高体重
	 */
	@RequestMapping(value="/HeightWeight",method = RequestMethod.GET)
	public String chartUserHeightAndWeight() {
		return "rept/chartUserHeightAndWeight";
	}
	/**
	 * 用户打球数据
	 */
	@RequestMapping(value="/userData",method = RequestMethod.GET)
	public String userData() {
		return "rept/chartUserData";
	}

	/**
	 * 注册用户统计
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="/chartUser/selectTableOfUser.do",method=RequestMethod.GET)
	@ResponseBody
	public List<UserData> getUserData(HttpServletRequest req, HttpServletResponse rep){
		//String beginTime = req.getParameter("beginTime");
		//String endTime = req.getParameter("endTime");
		List<UserData> userDatas = new ArrayList<UserData>();
		/*if(beginTime != null && !"".equals(beginTime)
				&& endTime != null && !"".equals(endTime)){
			userDatas = userService.getUserDataForTime(beginTime, endTime);
		}else{
			logger.warn("传入的参数为空哦！");
		}*/
		userDatas = userService.getUserData();
		logger.info(userDatas);
		
		return userDatas;
		
	}
	/**
	 * 注册活跃用户统计
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="/chartActiveUser/selectTableOfActiveUser.do",method=RequestMethod.GET)
	@ResponseBody
	public List<UserData> selectTableOfActiveUser(HttpServletRequest req, HttpServletResponse rep){
		//String beginTime = req.getParameter("beginTime");
		//String endTime = req.getParameter("endTime");
		List<UserData> userDatas = new ArrayList<UserData>();
		/*if(beginTime != null && !"".equals(beginTime)
				&& endTime != null && !"".equals(endTime)){
			userDatas = userService.getUserDataForTime(beginTime, endTime);
		}else{
			logger.warn("传入的参数为空哦！");
		}*/
		userDatas = userService.selectTableOfActiveUser();
		logger.info(userDatas);
		
		return userDatas;
		
	}
	/**
	 * 注册用户年龄段
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="/userAges/getUserAges.do",method=RequestMethod.GET)
	@ResponseBody
	public List<UserAges> getUserAges(HttpServletRequest req, HttpServletResponse rep){
		//String beginTime = req.getParameter("beginTime");
		//String endTime = req.getParameter("endTime");
		List<UserAges> userAges= new ArrayList<UserAges>();
		/*if(beginTime != null && !"".equals(beginTime)
				&& endTime != null && !"".equals(endTime)){
			userDatas = userService.getUserDataForTime(beginTime, endTime);
		}else{
			logger.warn("传入的参数为空哦！");
		}*/
		userAges = userService.getUserAges(false);
		return getUserAgesModel(userAges);
		
	}
	/**
	 * 活跃用户年龄段
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="/activeUser/getActiveUserAges.do",method=RequestMethod.GET)
	@ResponseBody
	public List<UserAges> getActiveUserAges(HttpServletRequest req, HttpServletResponse rep){
		//String beginTime = req.getParameter("beginTime");
		//String endTime = req.getParameter("endTime");
		List<UserAges> userAges= new ArrayList<UserAges>();
		/*if(beginTime != null && !"".equals(beginTime)
				&& endTime != null && !"".equals(endTime)){
			userDatas = userService.getUserDataForTime(beginTime, endTime);
		}else{
			logger.warn("传入的参数为空哦！");
		}*/
		userAges = userService.getUserAges(true);
		return getUserAgesModel(userAges);
		
	}
	
	
	private List<UserAges> getUserAgesModel(List<UserAges> userAges){
		int sum = 0;
		for (UserAges user : userAges) {
			sum += Integer.parseInt(user.getUserCount());
			
		}
		List<UserAges> newUserAges = new ArrayList<UserAges>();
		for (int i = 0; i< userAges.size();i++) {
			UserAges agesIndex = userAges.get(i);
			switch(Integer.parseInt(agesIndex.getAges())){
				case 0:
					agesIndex.setAges("其他");
					break;
				case 1:	
					agesIndex.setAges("0-12");
					break;
				case 2:
					agesIndex.setAges("13-20");
					break;
				case 3:
					agesIndex.setAges("21-30");
					break;
				case 4:
					agesIndex.setAges("21-30");
					break;
				case 5:
					agesIndex.setAges(">=40");
					break;
					
			}
			Double e = Double.parseDouble(agesIndex.getUserCount())/sum *100;
			DecimalFormat df = new DecimalFormat("0.00");  
			agesIndex.setUserCount(df.format(e));
			newUserAges.add(agesIndex);
		}
		
		logger.info(newUserAges);
		return newUserAges;
	}
	
	
	/**
	 * 用户打球位置
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="/userPosition/getUserPosition.do",method=RequestMethod.GET)
	@ResponseBody
	public List<UserAges> getUserPosition(HttpServletRequest req, HttpServletResponse rep){
		String isActive = req.getParameter("isActive");
		List<UserAges> userAges= new ArrayList<UserAges>();
		if(isActive == null || "".equals(isActive)){
			userAges = userService.getUserPosition(false);
		}else{
			userAges = userService.getUserPosition(true);
		}
		
		int sum = 0;
		List<UserAges> newUserAges = new ArrayList<UserAges>();
		for(UserAges user : userAges){
			if(user.getAges() == null || "".equals(user.getAges())){
				//userAges.remove(user);
			}else{
			
				sum += Integer.parseInt(user.getUserCount());
				newUserAges.add(user);
			}
		}
		for(int i = 0; i<newUserAges.size();i++){
			Double temp = Double.parseDouble(newUserAges.get(i).getUserCount());
			Double e = temp/sum *100;
			DecimalFormat df = new DecimalFormat("0.00");  
			newUserAges.get(i).setUserCount(df.format(e));
		}
		return newUserAges;
		
	}
	
	
	/*
	 * 省份统计
	 * 
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="/userLocation/getUserLoction.do",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Integer> getUserLoction(HttpServletRequest req, HttpServletResponse rep){
		Map<String,Integer> locationMap = new HashMap<String, Integer>();
		List<String> provinceList = moveService.getProvince();
		List<s_move> moveList = moveService.getUserLoction();
		//将用户的第一条运动数据分组到省份里面,调用省份算法
		locationMap = LocationUtil.getProvince(provinceList, moveList);
		return locationMap;
	}
	
	/*public String view(HttpServletRequest request, Model model) {
		Map<String,Integer> locationMap = new HashMap<String, Integer>();
		List<String> provinceList = moveService.getProvince();
		List<s_move> moveList = moveService.getUserLoction();
		//将用户的第一条运动数据分组到省份里面,调用省份算法
		locationMap = LocationUtil.getProvince(provinceList, moveList);
		model.addAttribute("locationMap",locationMap);
		return "rept/chartUserLocation";
	}*/
	
	/**
	 * 用户打球数据
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="/userData/getUserMoveData.do",method=RequestMethod.GET)
	@ResponseBody
	public List<s_move> getUserMoveData(HttpServletRequest req, HttpServletResponse rep){
		String beginTime = req.getParameter("beginTime");
		String endTime = req.getParameter("endTime");
		List<s_move> userMoveDatas = moveService.getUserMoveDataForTime(beginTime,endTime);
		
		return userMoveDatas;
	}
	
	
}
