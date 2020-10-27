package cn.chenjaly.java.practicaltrainin.service;

import cn.chenjaly.java.practicaltrainin.bean.User;
import cn.chenjaly.java.practicaltrainin.dao.UserDao;
import cn.chenjaly.java.practicaltrainin.dao.UserDaoImpl;
import cn.chenjaly.java.practicaltrainin.utiles.JDBCUtils;
import cn.chenjaly.java.practicaltrainin.utiles.PageModel;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    public User login(String loginname,String password){
        try {
            User user = dao.login(loginname,password);
            return user;
        }catch (Exception e){
            System.out.println("登陆异常");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findUsersByPage(User user, PageModel model) {
        try {
            List<User> users = dao.findUsersByPage(user, model);
            return users;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addUsers(User user) {
        return dao.addUsers(user);
    }

    @Override
    public void deleteUsers(String[] id) {
        dao.deleteUsers(id);
    }

    @Override
    public void update(User user) {
        dao.update(user);
    }

    @Override
    public int getTotalCountByUser(User user) {
        try {
            int count = dao.getTotalCountByUser(user);
            return  count;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User findUsers(Integer id) {
        return dao.findUserById(id);
    }

    @Override
    public User findUserById(int id) {
        return dao.findUserById(id);
    }
}
