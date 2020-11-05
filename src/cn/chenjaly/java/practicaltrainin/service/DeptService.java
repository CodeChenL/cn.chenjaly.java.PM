package cn.chenjaly.java.practicaltrainin.service;

import java.util.List;

import cn.chenjaly.java.practicaltrainin.bean.Dept;
import cn.chenjaly.java.practicaltrainin.utils.PageModel;

public interface DeptService {
	List<Dept> findDept(Dept dept,PageModel model);
	int getTotalCountByDept(Dept dept);
	public boolean addDept(Dept dept);
	public void updateDept(Dept dept);
	public void deleteDept(String[] id);
	Dept findDept(Integer id);
}
