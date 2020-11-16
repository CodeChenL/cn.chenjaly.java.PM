package org.gec.dao;

import java.util.List;

import org.gec.bean.Dept;
import org.gec.bean.User;
import org.gec.util.PageModel;

public interface DeptDao {
	//查询
 public  List<Dept>  findDept(Dept dept,PageModel model);
   
   //添加
   public boolean addDept(Dept dept);
   
   //修改
   public void updateDept(Dept dept);
   
   //删除
   public void deleteDept(String[] id);

 public int getTotalCountByDept(Dept dept);

public Dept findDept(Integer id);
}
