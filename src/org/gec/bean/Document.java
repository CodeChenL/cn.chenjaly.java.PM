package org.gec.bean;

import java.util.Arrays;
import java.util.Date;

public class Document {
	private Integer id;
	private String title;
	private String filename;
	private String filetype;
	private byte[] filebytes;
	private String remark;
	private Date createdate;
	private Integer user_id;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public byte[] getFilebytes() {
		return filebytes;
	}
	public void setFilebytes(byte[] filebytes) {
		this.filebytes = filebytes;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	
	
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Document() {
		super();
	}
	
	
	
	
	
	
	public Document(Integer id, String title, String filename, String filetype, byte[] filebytes, String remark,
			Date createdate, Integer user_id, User user) {
		super();
		this.id = id;
		this.title = title;
		this.filename = filename;
		this.filetype = filetype;
		this.filebytes = filebytes;
		this.remark = remark;
		this.createdate = createdate;
		this.user_id = user_id;
		this.user = user;
	}
	public Document(String title, String filename, String filetype, byte[] filebytes, String remark) {
		super();
		
		this.title = title;
		this.filename = filename;
		this.filetype = filetype;
		this.filebytes = filebytes;
		this.remark = remark;
	}
	
	
	public Document(String title, String filename, String filetype, byte[] filebytes, String remark,Integer user_id) {
		
		this.title = title;
		this.filename = filename;
		this.filetype = filetype;
		this.filebytes = filebytes;
		this.remark = remark;
		this.user_id = user_id;
	}
	public Document(String title, String remark, Integer user_id,Date createdate) {
		this.title = title;
		this.remark = remark;
		this.user_id = user_id;
		this.createdate = createdate;
	}
	public Document(String title) {
		this.title = title;
		
	}
	public Document(Integer id,String title,  String remark,byte[] filebytes, String filename, String filetype) {
		this.id=id;
		this.title = title;
		this.remark = remark;
		this.filebytes = filebytes;
		this.filename = filename;
		this.filetype = filetype;
	}
	@Override
	public String toString() {
		return "Document [id=" + id + ", title=" + title + ", filename=" + filename + ", filetype=" + filetype
				+ ", filebytes=" + Arrays.toString(filebytes) + ", remark=" + remark + ", createdate=" + createdate
				+ ", user_id=" + user_id + ", user=" + user + "]";
	}
	
	
	
	
}
