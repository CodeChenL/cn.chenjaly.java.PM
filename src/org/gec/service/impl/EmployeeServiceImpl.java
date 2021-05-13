package org.gec.service.impl;

import java.util.List;

import org.gec.bean.Dept;
import org.gec.bean.Employee;
import org.gec.bean.Job;
import org.gec.bean.User;
import org.gec.dao.EmployeeDao;
import org.gec.dao.impl.EmployeeDaoImpl;
import org.gec.service.EmployeeService;
import org.gec.util.PageModel;

public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDao dao = new EmployeeDaoImpl();

    @Override
    public List<Employee> findEmployee(Employee emp, PageModel model) {
        // TODO Auto-generated method stub
        return dao.findEmployee(emp, model);
    }

    @Override
    public int getTotalCountByEmployee(Employee emp) {
        // TODO Auto-generated method stub
        return dao.getTotalCountByEmployee(emp);
    }

    @Override
    public boolean addEmployee(Employee emp) {
        // TODO Auto-generated method stub
        return dao.addEmployee(emp);
    }

    @Override
    public void deleteEmployee(String[] id) {
        // TODO Auto-generated method stub

        dao.deleteEmployee(id);
    }

    @Override
    public void update(Employee emp) {
        // TODO Auto-generated method stub

        dao.update(emp);
    }

    @Override
    public Employee findEmployees(Integer id) {
        // TODO Auto-generated method stub
        return dao.findEmployees(id);
    }


    @Override
    public List<Dept> findDept() {
        // TODO Auto-generated method stub
        return dao.findDept();
    }

    @Override
    public List<Job> findJob() {
        // TODO Auto-generated method stub
        return dao.findJob();
    }

    @Override
    public String findCardId(String cardId) {
        // TODO Auto-generated method stub
        return dao.findCardId(cardId);
    }

}
