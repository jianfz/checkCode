package springboot.redis;

import java.io.Serializable;

public class User implements Serializable {

	private String username;
	private String password;
	private String age;
	private String classname;
	private String schoolname;

	public User(String username, String password, String age, String classname,
			String schoolname) {
		this.username = username;
		this.password = password;
		this.age = age;
		this.classname = classname;
		this.schoolname = schoolname;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

}
