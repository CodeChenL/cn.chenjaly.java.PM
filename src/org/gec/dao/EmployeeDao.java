package org.gec.dao;

import java.util.List;

import org.gec.bean.Dept;
import org.gec.bean.Employee;
import org.gec.bean.Job;
import org.gec.bean.User;
import org.gec.util.PageModel;

public interface EmployeeDao {

    //查找全部用户
    List<Employee> findEmployee(Employee emp, PageModel model);

    int getTotalCountByEmployee(Employee emp);

    //添加用户
    public boolean addEmployee(Employee emp);

    //删除用户
    public void deleteEmployee(String[] id);

    //修改
    public void update(Employee emp);

    Employee findEmployees(Integer id);

    String findCardId(String cardId);

    List<Job> findJob();

    List<Dept> findDept();
}
