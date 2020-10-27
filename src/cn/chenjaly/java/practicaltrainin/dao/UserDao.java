package cn.chenjaly.java.practicaltrainin.dao;

import cn.chenjaly.java.practicaltrainin.bean.User;
import cn.chenjaly.java.practicaltrainin.utiles.PageModel;

import java.util.List;

public interface UserDao {
    User login(String loginname,String password);
    List<User> findUsersByPage(User user, PageModel model);
    int getTotalCountByUser(User user);
    public boolean addUsers(User user);
    public void deleteUsers(String[] id);
    public void update(User user);
    User findUser(Integer id);
    User findUserById(int id);
}
