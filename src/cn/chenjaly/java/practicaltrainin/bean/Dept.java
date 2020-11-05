package cn.chenjaly.java.practicaltrainin.bean;

public class Dept {
	private Integer id;
	private String name;
	private String remark;
	public Dept(Integer id, String name, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
	}
	
	public Dept(String name, String remark) {
		super();
		this.name = name;
		this.remark = remark;
	}

	public Dept() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "dept [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}
	
	
}
