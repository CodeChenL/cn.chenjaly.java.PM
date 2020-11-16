package org.gec.dao;

import java.util.List;

import org.gec.bean.User;
import org.gec.util.PageModel;

public interface UserDao {

	//登录
	User login(String loginname, String password);

	//查找全部用户
	List<User> findUsersByPage(User user, PageModel model);

	int getTotalCountByUser(User user);
       
	//添加用户
	public boolean addUsers(User user);

	//删除用户
	public void deleteUsers(String[] id);

	//修改
	public void update(User user);

	User findUsers(Integer id);
	User findUserById(int id);

}
