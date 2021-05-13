package org.gec.service;

import java.util.List;

import org.gec.bean.User;
import org.gec.util.PageModel;

public interface UserService {
    User login(String loginname, String password);

    //查找所有用户
    List<User> findUsersByPage(User user, PageModel model);

    //添加用户
    public boolean addUsers(User user);

    //删除用户
    public void deleteUsers(String[] id);

    //修改
    public void update(User user);

    int getTotalCountByUser(User user);

    public User findUsers(Integer id);

    User findUserById(int id);
}
