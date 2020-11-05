package cn.chenjaly.java.practicaltrainin.service.impl;

import java.util.List;

import cn.chenjaly.java.practicaltrainin.bean.Employee;
import cn.chenjaly.java.practicaltrainin.bean.Job;
import cn.chenjaly.java.practicaltrainin.dao.EmployeeDao;
import cn.chenjaly.java.practicaltrainin.dao.JobDao;
import cn.chenjaly.java.practicaltrainin.dao.impl.EmployeeDaoImpl;
import cn.chenjaly.java.practicaltrainin.dao.impl.JobDaoImpl;
import cn.chenjaly.java.practicaltrainin.service.EmployeeService;
import cn.chenjaly.java.practicaltrainin.utils.PageModel;

public class EmployeeServiceImpl implements EmployeeService{
	private EmployeeDao dao = new EmployeeDaoImpl();
	@Override
	public List<Employee> findEmployee(Employee employee, PageModel model) {
		try {
			List<Employee> employees = dao.findEmployee(employee, model);
			return employees;
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public int getTotalCountByEmployee(Employee employee) {
		try {
			int count = dao.getTotalCountByEmployee(employee);
			return count;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			}
		return 0;
	}

	@Override
	public Employee findEmployee(Integer id) {
		return dao.findEmployee(id);
	}

	@Override
	public boolean addEmployee(Employee employee) {
		return dao.addEmployee(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		dao.updateEmployee(employee);
		
	}

	@Override
	public void deleteEmployee(String[] id) {
		dao.deleteEmployee(id);
		
	}

}
