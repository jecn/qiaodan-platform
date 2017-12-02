package org.safari.platform.common.enums;

/**
 * <p>Title: 媒体类型</p>
 * <p>Description:枚举 </p>
 * <p>Company: 深圳市萨法瑞科技有限公司</p>
 * @author Alitalk 
 * @date 2016-4-7
 */
public enum MediaEnum {

	/**
	 * 媒体类型 1 头像 2 图片 3 音频 4 视频 5 文件 6 二维码 7其他
	 */
	MEDIA_IMG("1","头像","imgs"), 
	MEDIA_PIC( "2","图片","pics"), 
	MEDIA_AUDIO("3","音频","audios"), 
	MEDIA_VIDEO("4","视频", "videos"),
	MEDIA_FILE("5","文件","files"),
	MEDIA_QR("6","二维码","qrs");
	
    // 成员变量  
    private String key;
    private String value; 
    private String folder;
    
    // 构造方法  
    private MediaEnum(String key, String value, String folder) {  
        this.key = key;  
        this.value = value;  
        this.folder = folder;
    } 
    
    // 普通方法 
    public static String getKey(String value) {  
        for (MediaEnum device : MediaEnum.values()) {  
            if (device.getValue().equals(value)) {  
                return device.key;  
            }  
        }  
        return null;  
    }  
    
    // 普通方法  
    public static String getValue(String key) {  
        for (MediaEnum device : MediaEnum.values()) {  
            if (device.getKey().equals(key)) {  
                return device.value;  
            }  
        }  
        return null;  
    }  
    
    // 普通方法  
    public static String getFolder(String key) {  
        for (MediaEnum device : MediaEnum.values()) {  
            if (device.getKey().equals(key)) {  
                return device.folder;  
            }  
        }  
        return null;  
    }  
    
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

}
