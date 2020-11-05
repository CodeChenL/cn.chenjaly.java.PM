package cn.chenjaly.java.practicaltrainin.bean;

import java.util.Date;

public class User {
	private Integer id;
	private String loginname;
	private String password;
	private Date createdate;
	private String username;
	private Integer status;
	public User(Integer id, String loginname, String password, Date createdate, String username, Integer status) {
		super();
		this.id = id;
		this.loginname = loginname;
		this.password = password;
		this.createdate = createdate;
		this.username = username;
		this.status = status;
	}
	public User() {
		super();
	}
	
	public User(String loginname, String password,Integer status, String username) {
		super();
		this.loginname = loginname;
		this.password = password;
		this.username = username;
		this.status = status;
	}
	public User(Integer id, String loginname, String password, String username, Integer status) {
		super();
		this.id = id;
		this.loginname = loginname;
		this.password = password;
		this.username = username;
		this.status = status;
	}
	
	public User(Integer id) {
		super();
		this.id = id;
	}
	public User(String loginname, String password, String username, Integer status) {
		super();
		this.loginname = loginname;
		this.password = password;
		this.username = username;
		this.status = status;
	}
	public User(Integer id, String loginname, String username) {
		super();
		this.id = id;
		this.loginname = loginname;
		this.username = username;
	}
	public User(String loginname, String username, Integer status) {
		super();
		this.loginname = loginname;
		this.username = username;
		this.status = status;
	}
	public User(String loginname) {
		super();
		this.loginname = loginname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", loginname=" + loginname + ", password=" + password + ", createdate=" + createdate
				+ ", username=" + username + ", status=" + status + "]";
	}
	
}
