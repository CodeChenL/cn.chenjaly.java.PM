package org.gec.service.impl;

import java.util.List;

import org.gec.bean.User;
import org.gec.dao.UserDao;
import org.gec.dao.impl.UserDaoImpl;
import org.gec.service.UserService;
import org.gec.util.PageModel;

public class UserServiceImpl implements UserService {
	
	//注入(依赖)dao
	private UserDao dao = new UserDaoImpl();

	@Override
	public User login(String loginname, String password) {
		try {
			User user = dao.login(loginname,password);
			return user;
		}catch (Exception e) {
			System.out.println("登录异常----");
			e.printStackTrace();
		}
		return null;
	}
       //查找
	//TODO
	@Override
	public List<User> findUsersByPage(User user,PageModel model) {
		try {
			List<User> users = dao.findUsersByPage(user,model);
			return users;	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
      //分页
	@Override
	public int getTotalCountByUser(User user) {
		try {
			int count = dao.getTotalCountByUser(user);
			return count;	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
     //添加用户
	@Override
	public boolean addUsers(User user) {
		return dao.addUsers(user);
	}
	
	//删除用户
	@Override
	public void deleteUsers(String[] id) {
		dao.deleteUsers(id);
		
	}
	@Override
	public void update(User user) {
		dao.update(user);
		
	}
	@Override
	public User findUsers(Integer id) {
		
		return dao.findUsers(id);
	}
	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return dao.findUserById(id);
	}
	

}
