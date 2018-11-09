package springboot.model;

public class UrlConfig {
	private int id;
	//地址
	private String url;
	//名称
	private String keyWord;
	//是否启用
	private String status;
	//类型 getNum=获取号码 
	private String type;
	//对外urlname 可以用1234代表，防止别人知道具体地址
	private String urlName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrlName() {
		return urlName;
	}
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

}
