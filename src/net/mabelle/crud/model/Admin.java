package net.mabelle.crud.model;

public class Admin {
	private int id;
	private String workNum;
	private String passWord;
	private String name;
	private String role;
	private String token;
	private String authority;
	private String stamp;
	
	
	
	
	public Admin(int id, String workNum, String passWord, String name, String role, String token, String authority,
			String stamp) {
		super();
		this.id = id;
		this.workNum = workNum;
		this.passWord = passWord;
		this.name = name;
		this.role = role;
		this.token = token;
		this.authority = authority;
		this.stamp = stamp;
	}
	
	
	
	public Admin(String workNum, String passWord, String name, String role, String token, String authority,
			String stamp) {
		super();
		this.workNum = workNum;
		this.passWord = passWord;
		this.name = name;
		this.role = role;
		this.token = token;
		this.authority = authority;
		this.stamp = stamp;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWorkNum() {
		return workNum;
	}
	public void setWorkNum(String workNum) {
		this.workNum = workNum;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	
	
}
