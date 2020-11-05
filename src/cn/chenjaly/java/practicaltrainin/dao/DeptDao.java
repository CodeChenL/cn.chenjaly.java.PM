package cn.chenjaly.java.practicaltrainin.dao;

import cn.chenjaly.java.practicaltrainin.bean.Dept;
import cn.chenjaly.java.practicaltrainin.utils.PageModel;

import java.util.List;

public interface DeptDao {
	List<Dept> findDept(Dept dept, PageModel model);
	int getTotalCountByDept(Dept dept);
	public boolean addDept(Dept dept);
	public void updateDept(Dept dept);
	public void deleteDept(String[] id);
	Dept findDept(Integer id);
	
}
