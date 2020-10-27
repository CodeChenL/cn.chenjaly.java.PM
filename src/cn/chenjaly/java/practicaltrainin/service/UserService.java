package cn.chenjaly.java.practicaltrainin.service;

import cn.chenjaly.java.practicaltrainin.bean.User;
import cn.chenjaly.java.practicaltrainin.utiles.PageModel;

import java.util.List;

public interface UserService {
    User login(String loginname,String password);
    List<User> findUsersByPage(User user, PageModel model);
    public boolean addUsers(User user);
    public void deleteUsers(String[] id);
    public void update(User user);
    int getTotalCountByUser(User user);
    public User findUsers(Integer id);
    User findUserById(int id);
}
