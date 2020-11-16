package org.gec.service;

import java.util.List;

import org.gec.bean.Dept;
import org.gec.bean.Employee;
import org.gec.bean.Job;
import org.gec.bean.User;
import org.gec.util.PageModel;

public interface EmployeeService {

	//查找全部用户
			List<Employee> findEmployee(Employee emp, PageModel model);

			int getTotalCountByEmployee(Employee emp);
		       
			//添加用户
			public boolean addEmployee(Employee emp);

			//删除用户
			public void deleteEmployee(String[] id);

			//修改
			public void update(Employee emp);

			List<Dept> findDept();
			List<Job> findJob();
			 String findCardId(String cardId);

			Employee findEmployees(Integer id);
}
