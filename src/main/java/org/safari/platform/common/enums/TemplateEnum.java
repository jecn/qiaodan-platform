package org.safari.platform.common.enums;

/**
 * 模板枚举
 * @author Alitalk
 * @date 2017-03-12
 */
public enum TemplateEnum {

	T_EXCEL("xls","excel"),
	T_EXCELX("xlsx","excel"),
	T_WORD("doc","ftl"),
	T_WORDX("docx","ftl");
	
	private String suffix;
	private String name;
	
	private TemplateEnum(String suffix, String name) {
		this.suffix = suffix;
		this.name = name;
	}
	
	 // 普通方法 
    public static String getSuffix(String name) {  
        for (TemplateEnum template : TemplateEnum.values()) {  
            if (template.getName().equals(name)) {  
                return template.suffix;  
            }  
        }  
        return null;  
    }  
    
    // 普通方法  
    public static String getName(String suffix) {  
        for (TemplateEnum template : TemplateEnum.values()) {  
            if (template.getSuffix().equals(suffix)) {  
                return template.name;  
            }  
        }  
        return null;  
    }

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
    
}
