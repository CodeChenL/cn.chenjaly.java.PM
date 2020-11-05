package cn.chenjaly.java.practicaltrainin.service.impl;

import java.util.List;

import cn.chenjaly.java.practicaltrainin.bean.Dept;
import cn.chenjaly.java.practicaltrainin.dao.DeptDao;
import cn.chenjaly.java.practicaltrainin.dao.impl.DeptDaoImpl;
import cn.chenjaly.java.practicaltrainin.service.DeptService;
import cn.chenjaly.java.practicaltrainin.utils.PageModel;

public class DeptServiceImpl implements DeptService {
	private DeptDao dao = new DeptDaoImpl();
	@Override
	public List<Dept> findDept(Dept dept, PageModel model) {
		try {
		List<Dept> depts = dao.findDept(dept, model);
		return depts;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getTotalCountByDept(Dept dept) {
		try {
			int count = dao.getTotalCountByDept(dept);
			return count;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			}
		return 0;
	}

	@Override
	public boolean addDept(Dept dept) {
		return dao.addDept(dept);
	}

	@Override
	public void updateDept(Dept dept) {
		dao.updateDept(dept);
		
	}

	@Override
	public void deleteDept(String[] id) {
		dao.deleteDept(id);
		
	}

	@Override
	public Dept findDept(Integer id) {
		
		return dao.findDept(id);
	}
	
}
