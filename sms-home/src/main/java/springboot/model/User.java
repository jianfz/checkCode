package springboot.model;

import java.util.Date;

public class User {
	private int id;
	//登录名
	private String username;
	//登录密码
	private String password;
	//电话
	private String tel;
	//邮箱
	private String email;
	//是否启用 0=启用 1=停用
	private String status;
	//ip地址
	private String ip;
	//创建日期
	private Date createDate;
	//真实姓名
	private String realName;
	//性别
	private String sex;
	//年龄
	private int age;
	//地址
	private String address;
	//是否发送短信
	private String isSms;
	//是否发送邮件
	private String isEmail;
	
	/**
	 * 系统自动生产的set 和 get 方法
	 * @return
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIsSms() {
		return isSms;
	}
	public void setIsSms(String isSms) {
		this.isSms = isSms;
	}
	public String getIsEmail() {
		return isEmail;
	}
	public void setIsEmail(String isEmail) {
		this.isEmail = isEmail;
	}
}
