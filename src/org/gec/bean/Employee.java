package org.gec.bean;

import java.util.Date;

public class Employee {

	private Integer deptId;
	private Integer id;
	private Integer jobId;
	private String cardId;
	private String name;
	private String address;
	private String postcode;
	private String tel;
	private String phone;
	private String qqnum;
	private String email;
	private String party;
	private Date birthday;
	private String race;
	private String education;
	private String speciality;
	private String hobby;
	private String remark;
	private Date createdate;
	private Integer sex;
	private Dept dept;
	private Job job;
	
	
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQqnum() {
		return qqnum;
	}
	public void setQqnum(String qqnum) {
		this.qqnum = qqnum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Employee() {
		super();
	}

	
	
	public Employee(Integer deptId, Integer id, Integer jobId, String cardId, String name, String address,
			String postcode, String tel, String phone, String qqnum, String email, String party, Date birthday,
			String race, String education, String speciality, String hobby, String remark, Date createdate, Integer sex,
			Dept dept, Job job) {
		super();
		this.deptId = deptId;
		this.id = id;
		this.jobId = jobId;
		this.cardId = cardId;
		this.name = name;
		this.address = address;
		this.postcode = postcode;
		this.tel = tel;
		this.phone = phone;
		this.qqnum = qqnum;
		this.email = email;
		this.party = party;
		this.birthday = birthday;
		this.race = race;
		this.education = education;
		this.speciality = speciality;
		this.hobby = hobby;
		this.remark = remark;
		this.createdate = createdate;
		this.sex = sex;
		this.dept = dept;
		this.job = job;
	}
	
	
	
	public Employee(Integer deptId, Integer jobId, String cardId, String name, String phone, Integer sex) {
     
		
		this.deptId = deptId;
		this.jobId = jobId;
		this.cardId = cardId;
		this.name = name;
		this.phone = phone;
		this.sex = sex;
		
	}
	
	public Employee(Integer deptId, Integer jobId, String cardId, String name, String address, String postcode,
			String tel, String phone, String qqnum, String email, Integer sex, String party, Date birthday,
			String race, String education, String speciality, String hobby, String remark) {
		this.deptId = deptId;
		this.jobId = jobId;
		this.cardId = cardId;
		this.name = name;
		this.address = address;
		this.postcode = postcode;
		this.tel = tel;
		this.phone = phone;
		this.qqnum = qqnum;
		this.email = email;
		this.party = party;
		this.birthday = birthday;
		this.race = race;
		this.education = education;
		this.speciality = speciality;
		this.hobby = hobby;
		this.remark = remark;
		this.sex = sex;
	}
	
	public Employee(Integer id, Integer deptId, Integer jobId, String cardId, String name, String address,
			String postcode, String tel, String phone, String qqnum, String email, Integer sex, String party,
			Date birthday, String race, String education, String speciality, String hobby, String remark) {
		this.deptId = deptId;
		this.id = id;
		this.jobId = jobId;
		this.cardId = cardId;
		this.name = name;
		this.address = address;
		this.postcode = postcode;
		this.tel = tel;
		this.phone = phone;
		this.qqnum = qqnum;
		this.email = email;
		this.party = party;
		this.birthday = birthday;
		this.race = race;
		this.education = education;
		this.speciality = speciality;
		this.hobby = hobby;
		this.remark = remark;
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Employee [deptId=" + deptId + ", id=" + id + ", jobId=" + jobId + ", cardId=" + cardId + ", name="
				+ name + ", address=" + address + ", postcode=" + postcode + ", tel=" + tel + ", phone=" + phone
				+ ", qqnum=" + qqnum + ", email=" + email + ", sex=" + sex + ", party=" + party + ", birthday="
				+ birthday + ", race=" + race + ", education=" + education + ", speciality=" + speciality + ", hobby="
				+ hobby + ", remark=" + remark + ", createdate=" + createdate + ", sexs=" + sex + "]";
	}


	
	
	
	
}
