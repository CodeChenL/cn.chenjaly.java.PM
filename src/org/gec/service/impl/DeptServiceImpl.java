package org.gec.service.impl;

import java.util.List;

import org.gec.bean.Dept;
import org.gec.bean.User;
import org.gec.dao.DeptDao;
import org.gec.dao.impl.DeptDaoImpl;
import org.gec.service.DeptService;
import org.gec.util.PageModel;

public class DeptServiceImpl implements DeptService {

    //注入
    DeptDao dao = new DeptDaoImpl();

    //查询
    @Override
    public List<Dept> findDept(Dept dept, PageModel model) {

        return dao.findDept(dept, model);
    }

    //添加
    @Override
    public boolean addDept(Dept dept) {
        // TODO Auto-generated method stub
        return dao.addDept(dept);
    }

    //修改
    @Override
    public void updateDept(Dept dept) {
        // TODO Auto-generated method stub
        dao.updateDept(dept);
    }

    //删除
    @Override
    public void deleteDept(String[] id) {
        // TODO Auto-generated method stub
        dao.deleteDept(id);
    }

    @Override
    public int getTotalCountByDept(Dept dept) {
        // TODO Auto-generated method stub
        return dao.getTotalCountByDept(dept);
    }

    @Override
    public Dept findDept(Integer id) {
        // TODO Auto-generated method stub
        return dao.findDept(id);
    }

}
