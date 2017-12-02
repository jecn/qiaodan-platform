package org.safari.platform.common.enums;

/**
 * 功能枚举
 * @author Alitalk
 * @date 2017-03-15
 */
public enum BarEnum {
	bar_view("view","icon-view","查看"),
	bar_add("add","icon-add","新增"),
	bar_update("update","icon-edit","修改"),
	bar_delete("del","icon-delete","删除"),
	bar_import("import","icon-import","导入"),
	bar_export("export","icon-export","导出"),
	bar_download("download","icon-download","下载"),
	
	
	bar_user_role("userForRole","icon-suppliers","用户角色"),
	bar_user_corp("userForCorp","icon-corp","所属单位"),
	bar_user_org("userForOrg","icon-home","所属机构");
	
	/**
	 * 英文名称
	 */
	private String ename;
	
	/**
	 * 图标
	 */
	private String icon;
	
	/**
	 * 中文名称
	 */
	private String cname;
	
	
	private BarEnum(String ename, String icon,String cname) {
		this.ename = ename;
		this.icon = icon;
		this.cname = cname;
	}
	
	public static String toIcon(String cname){
		 for (BarEnum barEnum : BarEnum.values()) {  
            if (barEnum.getCname().equals(cname)) {  
                return barEnum.icon;  
            }  
	     } 
		 return null;
	}
	
	public static String toCname(String ename){
		 for (BarEnum barEnum : BarEnum.values()) {  
           if (barEnum.getEname().equals(ename)) {  
               return barEnum.cname;  
           }  
	     } 
		 return null;
	}
	
	public static String toEname(String cname){
		 for (BarEnum barEnum : BarEnum.values()) {  
          if (barEnum.getCname().equals(cname)) {  
              return barEnum.ename;  
          }  
	     } 
		 return null;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	} 
	
	
}
