package org.safari.platform.modules.user.entity;

import org.safari.platform.common.persistence.DataEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *<p>Title:会员表</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2016-09-13
 */
public class Vip extends DataEntity<Vip> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 授权ID
     */
    private String openId;

    /**
     * 授权类型(1微信、2微博、3 QQ、4百度 )
     */
    private String type;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nick;

    /**
     * 头像
     */
    private String img;
    
    /**
     * 性别 1 男 2 女
     */
    private String gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 出生日期
     */
    private String birthday;
    
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * QQ号
     */
    private String qq;
    
    /**
     * 擅长位置
     */
    private String position;
    
    /**
     * 体重
     */
    private String weight;
    
    /**
     * 身高
     */
    private String height;
    
    /**
     * 左右脚  R 右脚 L 左脚
     */
    private String footer;
    
    /**
     * 二维码
     */
    private String qr;
    
    /**
     * 状态 0 注销 1 正常使用 2 暂停使用 (默认为1)
     */
    private String stat;
    
    public Vip(){
    	super();
    }
    
    public Vip(String id){
    	super(id);
    }
    
   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @JsonIgnore
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name ;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick ;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

	public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender ;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday ;
	}

	public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email ;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq ;
    }

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position ;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight ;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height ;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer ;
	}

	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr ;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat ;
	}
    
}