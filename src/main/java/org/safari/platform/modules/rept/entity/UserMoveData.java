package org.safari.platform.modules.rept.entity;


import org.safari.platform.common.persistence.DataEntity;

public class UserMoveData extends DataEntity<UserMoveData> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户信息
	 */
	private String username;
    private String name;
    private String nick;
    private String gender;
    private Integer age;
    private String mobile;
    private String weight;
    private String height;
    private String position;//打球位置
    
    /**
     * 打球信息
     */
    protected String address;
    protected String beginTime;
    protected String endTime;
    protected String spend;//运动时长
    protected String totalStep;//总步数
    protected String totalDist;
    protected String totalHorDist;//横向距离
    protected String totalVerDist;//纵向距离
    protected String activeRate;//活跃占比
    protected String avgSpeed;//平均移动速度
    protected String maxSpeed;//最大移动速度
    protected String spurtCount;//冲刺次数
    
    protected String breakinCount;//变向次数
    protected String breakinAvgTime;//变向平均触地时间
    protected String avgHoverTime;//平均滞空时间
    protected String avgTouchAngle;//平均着地旋转角
    protected String touchType;//着地类型(-1内翻 0空的 1外翻)
    protected String maxWallup;//最大冲击力
    protected String avgWallup;//平均冲击力
    protected String type;//类型 (1 全场 2 半场)
    protected String fieldType;//场地类型(1:水泥 2:塑胶 3:木地板)
    
    protected String verJumpCount;//纵跳次数
    protected String verJumpAvgHigh;//平均纵跳高度
    protected String verJumpMaxHigh;//纵跳最大高度
    protected String perfRank;//本场表现
    protected String runRank;//跑动等级
    protected String breakRank;//突破等级
    protected String bounceRank;//弹跳等级
    protected String calorie;
    
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBreakinCount() {
		return breakinCount;
	}
	public void setBreakinCount(String breakinCount) {
		this.breakinCount = breakinCount;
	}
	public String getBreakinAvgTime() {
		return breakinAvgTime;
	}
	public void setBreakinAvgTime(String breakinAvgTime) {
		this.breakinAvgTime = breakinAvgTime;
	}
	public String getAvgHoverTime() {
		return avgHoverTime;
	}
	public void setAvgHoverTime(String avgHoverTime) {
		this.avgHoverTime = avgHoverTime;
	}
	public String getAvgTouchAngle() {
		return avgTouchAngle;
	}
	public void setAvgTouchAngle(String avgTouchAngle) {
		this.avgTouchAngle = avgTouchAngle;
	}
	public String getTouchType() {
		return touchType;
	}
	public void setTouchType(String touchType) {
		this.touchType = touchType;
	}
	public String getMaxWallup() {
		return maxWallup;
	}
	public void setMaxWallup(String maxWallup) {
		this.maxWallup = maxWallup;
	}
	public String getAvgWallup() {
		return avgWallup;
	}
	public void setAvgWallup(String avgWallup) {
		this.avgWallup = avgWallup;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getCalorie() {
		return calorie;
	}
	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSpend() {
		return spend;
	}
	public void setSpend(String spend) {
		this.spend = spend;
	}
	public String getTotalStep() {
		return totalStep;
	}
	public void setTotalStep(String totalStep) {
		this.totalStep = totalStep;
	}
	public String getTotalDist() {
		return totalDist;
	}
	public void setTotalDist(String totalDist) {
		this.totalDist = totalDist;
	}
	public String getTotalHorDist() {
		return totalHorDist;
	}
	public void setTotalHorDist(String totalHorDist) {
		this.totalHorDist = totalHorDist;
	}
	public String getTotalVerDist() {
		return totalVerDist;
	}
	public void setTotalVerDist(String totalVerDist) {
		this.totalVerDist = totalVerDist;
	}
	public String getActiveRate() {
		return activeRate;
	}
	public void setActiveRate(String activeRate) {
		this.activeRate = activeRate;
	}
	public String getAvgSpeed() {
		return avgSpeed;
	}
	public void setAvgSpeed(String avgSpeed) {
		this.avgSpeed = avgSpeed;
	}
	public String getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(String maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public String getSpurtCount() {
		return spurtCount;
	}
	public void setSpurtCount(String spurtCount) {
		this.spurtCount = spurtCount;
	}
	public String getVerJumpCount() {
		return verJumpCount;
	}
	public void setVerJumpCount(String verJumpCount) {
		this.verJumpCount = verJumpCount;
	}
	public String getVerJumpAvgHigh() {
		return verJumpAvgHigh;
	}
	public void setVerJumpAvgHigh(String verJumpAvgHigh) {
		this.verJumpAvgHigh = verJumpAvgHigh;
	}
	public String getVerJumpMaxHigh() {
		return verJumpMaxHigh;
	}
	public void setVerJumpMaxHigh(String verJumpMaxHigh) {
		this.verJumpMaxHigh = verJumpMaxHigh;
	}
	public String getPerfRank() {
		return perfRank;
	}
	public void setPerfRank(String perfRank) {
		this.perfRank = perfRank;
	}
	public String getRunRank() {
		return runRank;
	}
	public void setRunRank(String runRank) {
		this.runRank = runRank;
	}
	public String getBreakRank() {
		return breakRank;
	}
	public void setBreakRank(String breakRank) {
		this.breakRank = breakRank;
	}
	public String getBounceRank() {
		return bounceRank;
	}
	public void setBounceRank(String bounceRank) {
		this.bounceRank = bounceRank;
	}

    
    
    
}
