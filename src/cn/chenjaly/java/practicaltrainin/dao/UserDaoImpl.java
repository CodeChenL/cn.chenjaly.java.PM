package cn.chenjaly.java.practicaltrainin.dao;

import cn.chenjaly.java.practicaltrainin.bean.User;
import cn.chenjaly.java.practicaltrainin.utiles.JDBCUtils;
import cn.chenjaly.java.practicaltrainin.utiles.PageModel;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{


    @Override
    public User login(String loginname, String password) {
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from user_inf where loginname=? and password=?";
        try {
            PreparedStatement pstm =conn.prepareStatement(sql);
            pstm.setString(1,loginname);
            pstm.setString(2,password);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLoginname(rs.getString("loginname"));
                user.setPassword(rs.getString("password"));
                user.setCreatedate(rs.getString("createdate"));
                user.setUsername(rs.getString("username"));
                user.setStatus(rs.getInt("status"));
                return user;
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConn(conn);
        }
        return null;
    }

    @Override
    public List<User> findUsersByPage(User user, PageModel model) {
        List<User> users = new ArrayList<>();
        Connection conn = JDBCUtils.getConnection();
        StringBuffer sql = new StringBuffer("select * from user_inf where 1=1 ");
        try {
            Statement pstm = conn.createStatement();
            if (StringUtils.isNotBlank(user.getLoginname())){
                sql.append(" and loginname like '%").append(user.getLoginname()).append("%'");
                System.out.println("sql:"+sql);
            }if (StringUtils.isNotBlank(user.getLoginname())){
                sql.append(" and username like '%").append(user.getLoginname()).append("%'");
                System.out.println("sql:"+sql);
            }if (user.getStatus() != null){
                sql.append(" and status = ").append(user.getStatus());
            }
            sql.append(" limit ").append(model.getStartRow()).append(",").append(PageModel.getPagesize());
            ResultSet rs = pstm.executeQuery(sql.toString());
            System.out.println("sql:"+sql);
            while (rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setLoginname(rs.getString("loginname"));
                u.setPassword(rs.getString("password"));
                u.setCreatedate(rs.getString("createdate"));
                u.setUsername(rs.getString("username"));
                u.setStatus(rs.getInt("status"));
                users.add(u);
            }
            return users;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConn(conn);
        }
        return null;
    }

    @Override
    public int getTotalCountByUser(User user) {
        List<User> users = new ArrayList<>();
        Connection conn = JDBCUtils.getConnection();
        StringBuffer sql = new StringBuffer("select count(*) from user_inf where 1=1 ");
        try {
            Statement pstm = conn.createStatement();
            if (StringUtils.isNotBlank(user.getLoginname())){
                sql.append(" and loginname='").append(user.getLoginname()).append("'");
            }if (StringUtils.isNotBlank(user.getUsername())){
                sql.append(" and username like '%").append(user.getUsername()).append("%'");
            }if (user.getStatus() != null){
                sql.append(" and status = ").append(user.getStatus());
            }
            ResultSet rs = pstm.executeQuery(sql.toString());
            while (rs.next()){
                return  rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConn(conn);
        }
        return 0;
    }

    @Override
    public boolean addUsers(User user) {
        return false;
    }

    @Override
    public void deleteUsers(String[] id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User findUser(Integer id) {
        return null;
    }

    @Override
    public User findUserById(int id) {
        return null;
    }
}
