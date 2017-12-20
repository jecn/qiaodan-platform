package org.safari.platform.modules.rept.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.safari.platform.common.web.BaseController;
import org.safari.platform.modules.rept.entity.UserAges;
import org.safari.platform.modules.rept.entity.UserData;
import org.safari.platform.modules.rept.entity.UserMoveData;
import org.safari.platform.modules.rept.entity.s_move;
import org.safari.platform.modules.rept.entity.u_vip;
import org.safari.platform.modules.rept.service.MoveServiceI;
import org.safari.platform.modules.rept.service.UserMoveDataServiceI;
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
	@Autowired
	private UserMoveDataServiceI userMoveDataService;
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
	public List<UserMoveData> getUserMoveData(HttpServletRequest req, HttpServletResponse rep){
		String beginTime = req.getParameter("beginTime");
		String endTime = req.getParameter("endTime");
		List<UserMoveData> userMoveDatas = userMoveDataService.getUserMoveDataForTime(beginTime,endTime);
		
		return userMoveDatas;
	}
	
	/**
	 * 用户身高体重性别比例
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="/user/getHeightWeight.do",method=RequestMethod.GET)
	@ResponseBody
	public String getHeightWeight(HttpServletRequest req, HttpServletResponse rep){
		List<u_vip> allVip = userService.getUser(false);
		List<u_vip> activeVip = userService.getUser(true);
		int allUserCount = allVip.size();//总人数
		int allMan = 0;//总男
		int allWomen = 0;//总女
		double allManHeight = 0;//总男高度
		double allManAvgHeight = 0;
		double allWomenHeight = 0;//总女高度
		double allWomenAvgHeight = 0;
		double allManWeight = 0;//总男体重
		double allManAvgWeight = 0;
		double allWomenWeight =0;//总女体重
		double allWomenAvgWeight = 0;
		DecimalFormat df=new DecimalFormat(".##");
		for (u_vip u : allVip) {
			Integer sex = Integer.parseInt(u.getGender()); 
			/*System.out.println("---------------------"+i++);
			System.out.println("u.getHeight() = " +u.getHeight());
			System.out.println("u.getWeight() = " +u.getWeight());
			*/if (sex == 2) {
				allWomen ++;
				allWomenHeight = allWomenHeight + Integer.parseInt(u.getHeight());
				allWomenWeight = allWomenWeight + Integer.parseInt(u.getWeight());
			}else{
				allMan ++;
				allManHeight = allManHeight + Integer.parseInt(u.getHeight());
				allManWeight = allManWeight + Integer.parseInt(u.getWeight());
			}
		}
		if(allMan != 0){
			allManAvgHeight = allManHeight / allMan;
			allManAvgWeight = allManWeight / allMan;
		}
		if(allWomen != 0){
			allWomenAvgHeight = allWomenHeight / allWomen;
			allWomenAvgWeight = allWomenWeight / allWomen;
		}
		int activeUserCount = activeVip.size();//总人数
		int activeMan = 0;//总男
		int activeWomen = 0;//总女
		double activeManHeight = 0;//总男高度
		double activeManAvgHeight = 0;
		double activeWomenHeight = 0;//总女高度
		double activeWomenAvgHeight = 0;
		double activeManWeight = 0;//总男体重
		double activeManAvgWeight = 0;
		double activeWomenWeight =0;//总女体重
		double activeWomenAvgWeight = 0;
		for (u_vip u : activeVip) {
			Integer sex = Integer.parseInt(u.getGender()); 
			if (sex == 2) {
				activeWomen ++;
				activeWomenHeight = activeWomenHeight + Integer.parseInt(u.getHeight());
				activeWomenWeight = activeWomenWeight + Integer.parseInt(u.getWeight());
			}else{
				activeMan ++;
				activeManHeight = activeManHeight + Integer.parseInt(u.getHeight());
				activeManWeight = activeManWeight + Integer.parseInt(u.getWeight());
			}
		}
		if(activeMan != 0){
			activeManAvgHeight = activeManHeight / activeMan;
			activeManAvgWeight = activeManWeight / activeMan;
		}
		if(activeWomen != 0){
			activeWomenAvgHeight = activeWomenHeight / activeWomen;
			activeWomenAvgWeight = activeWomenWeight / activeWomen;
		}
		JSONObject ob = new JSONObject();
		ob.put("allUserCount", allUserCount);
		ob.put("allManWomen", allMan+":"+allWomen);
		ob.put("allManAvgHeight", df.format(allManAvgHeight));
		ob.put("allWomenAvgHeight", df.format(allWomenAvgHeight));
		ob.put("allManAvgWeight", df.format(allManAvgWeight));
		ob.put("allWomenAvgWeight", df.format(allWomenAvgWeight));
		
		ob.put("activeUserCount", activeUserCount);
		ob.put("activeManWomen", activeMan+":"+activeWomen);
		ob.put("activeManAvgHeight", df.format(activeManAvgHeight));
		ob.put("activeWomenAvgHeight", df.format(activeWomenAvgHeight));
		ob.put("activeManAvgWeight", df.format(activeManAvgWeight));
		ob.put("activeWomenAvgWeight", df.format(activeWomenAvgWeight));
		
		return ob.toString();
	}
	
	
}
