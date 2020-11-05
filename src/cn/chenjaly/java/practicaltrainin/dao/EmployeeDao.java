package cn.chenjaly.java.practicaltrainin.dao;

import cn.chenjaly.java.practicaltrainin.bean.Employee;
import cn.chenjaly.java.practicaltrainin.utils.PageModel;

import java.util.List;

public interface EmployeeDao {
	List<Employee> findEmployee(Employee employee, PageModel model);
	int getTotalCountByEmployee(Employee employee);
	Employee findEmployee(Integer id);
	public boolean addEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public void deleteEmployee(String[] id);
}
