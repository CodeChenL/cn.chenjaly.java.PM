package cn.chenjaly.java.practicaltrainin.service.impl;

import java.util.List;

import cn.chenjaly.java.practicaltrainin.bean.User;
import cn.chenjaly.java.practicaltrainin.dao.UserDao;
import cn.chenjaly.java.practicaltrainin.dao.impl.UserDaoImpl;
import cn.chenjaly.java.practicaltrainin.service.UserService;
import cn.chenjaly.java.practicaltrainin.utils.PageModel;

public class UserServiceImpl implements UserService {

	private UserDao dao = new UserDaoImpl();
	
	@Override
	public User login(String loginname, String password) {
		try {
			User user = dao.login(loginname, password);
			return user;
			}catch(Exception e) {
				System.out.println("��¼�쳣----");
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public List<User> findUsersByPage(User user, PageModel model) {
		try {
		List<User> users = dao.findUsersByPage(user,model);
		return users;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getTotalCountByUser(User user) {
		try {
			int count = dao.getTotalCountByUser(user);
			return count;
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return 0;
	}

	@Override
	public boolean addUser(User user) {
		return dao.addUser(user);
	}

	@Override
	public void deleteUsers(String[] id) {
		dao.deleteUsers(id);
	}

	@Override
	public void updateUsers(User user) {
		dao.updateUsers(user);
		
	}

	@Override
	public User findUsers(Integer id) {
		return dao.findUsers(id);
	}

	@Override
	public User findUserById(int id) {
		return dao.findUserById(id);
	}

}
