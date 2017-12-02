package org.safari.platform.modules.sys.entity;

import org.safari.platform.common.persistence.DataEntity;

/**
 *<p>Title:公司单位</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-03-30
 */
public class Corp extends DataEntity<Corp> {

    private static final long serialVersionUID = 1L;
    
    /**
     * 区域ID
     */
    private String areaId;

    /**
     * 区域
     */
    private Area area;

    /**
     * 名称
     */
    private String name;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 类型
     */
    private String type;

    /**
     * 性质
     */
    private String lnc;

    /**
     * 电话
     */
    private String tel;

    /**
     * 传真
     */
    private String fax;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 规模
     */
    private String scale;

    /**
     * 地址
     */
    private String address;
    
    public Corp() {
		super();
	}
	
	public Corp(String id){
		super(id);
	} 

    public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId== null ? null : areaId.trim();
	}

	public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLnc() {
        return lnc;
    }

    public void setLnc(String lnc) {
        this.lnc = lnc == null ? null : lnc.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale == null ? null : scale.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}