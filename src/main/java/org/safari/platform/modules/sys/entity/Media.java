package org.safari.platform.modules.sys.entity;

import java.io.Serializable;

/**
 *<p>Title:媒体</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2016-09-14
 */
public class Media implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    private String id;
    
    /**
     * 创建者
     */
    private String createBy;

    /**
     * 类型 1头像 2 图片 3 音频 4 视频 5 文件
     */
    private String type;

    /**
     * 文件夹
     */
    private String folder;

    /**
     * 名称
     */
    private String name;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 来源 1 内部 2 外部
     */
    private String source;

    /**
     * 相对路径
     */
    private String path;

    /**
     * 绝对路径
     */
    private String url;

    /**
     * 文件大小
     */
    private String length;

    /**
     * 时长
     */
    private String time;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}