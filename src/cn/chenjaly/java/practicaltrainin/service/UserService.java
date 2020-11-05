package cn.chenjaly.java.practicaltrainin.service;

import java.util.List;

import cn.chenjaly.java.practicaltrainin.bean.User;
import cn.chenjaly.java.practicaltrainin.utils.PageModel;

public interface UserService {
	User login(String loginname,String password);
	List<User> findUsersByPage(User user,PageModel model);
	
	int getTotalCountByUser(User user);
	
	public boolean addUser(User user);
	
	public void deleteUsers(String[] id);
	public void updateUsers(User user);
	public User findUsers(Integer id);
	User findUserById(int id);
}
