package cn.chenjaly.java.practicaltrainin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.chenjaly.java.practicaltrainin.bean.User;
import cn.chenjaly.java.practicaltrainin.dao.UserDao;
import cn.chenjaly.java.practicaltrainin.utils.JDBCUtils;
import cn.chenjaly.java.practicaltrainin.utils.PageModel;
import org.apache.commons.lang3.StringUtils;

public class UserDaoImpl implements UserDao {

	public User login(String loginname, String password) {
		Connection conn = JDBCUtils.getConnection();
		String sql = "select * from user_inf where loginname = ? and password = ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, loginname);
			pstm.setString(2, password);

			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLoginname(rs.getString("loginname"));
				user.setPassword(rs.getString("password"));
				user.setCreatedate(rs.getDate("createdate"));
				user.setUsername(rs.getString("username"));
				user.setStatus(rs.getInt("status"));
				return user;
			}
			return null;
		}catch(SQLException e) {
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

		StringBuffer sql = new  StringBuffer("select * from user_inf where 1=1");

		try {
			Statement pstm = conn.createStatement();

			if(StringUtils.isNotBlank(user.getLoginname())) {
				sql.append(" and loginname like '%").append(user.getLoginname()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(StringUtils.isNotBlank(user.getUsername())) {
				sql.append(" and username like '%").append(user.getUsername()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(user.getStatus()!=null) {
				sql.append(" and status = ").append(user.getStatus());
			}

			sql.append(" limit ").append(model.getStartRow()).append(" , ").append(PageModel.pageSize);

			ResultSet rs = pstm.executeQuery(sql.toString());
			System.out.println("sql:"+sql);
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setLoginname(rs.getString("loginname"));
				u.setPassword(rs.getString("password"));
				u.setCreatedate(rs.getDate("createdate"));
				u.setUsername(rs.getString("username"));
				u.setStatus(rs.getInt("status"));

				users.add(u);
			}
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		StringBuffer sql = new  StringBuffer("select count(*) from user_inf where 1=1");
		try {
			Statement pstm = conn.createStatement();

			if(StringUtils.isNotBlank(user.getLoginname())) {
				sql.append(" and loginname like '%").append(user.getLoginname()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(StringUtils.isNotBlank(user.getUsername())) {
				sql.append(" and username like '%").append(user.getUsername()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(user.getStatus()!=null) {
				sql.append(" and status = ").append(user.getStatus());
			}

			ResultSet rs = pstm.executeQuery(sql.toString());

			while(rs.next()) {
				return rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConn(conn);
		}
		return 0;
	}

	@Override
	public boolean addUser(User user) {
		Connection conn = JDBCUtils.getConnection();
		String sql = "insert into user_inf(loginname,password,status,username) VALUE(?,?,?,?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getLoginname());
			pstm.setString(2, user.getPassword());
			pstm.setInt(3, user.getStatus());
			pstm.setString(4, user.getUsername());;

			int row = pstm.executeUpdate();
			if(row>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConn(conn);
		}
		return false;
	}

	@Override
	public void deleteUsers(String[] id) {
		Connection conn = JDBCUtils.getConnection();
		try {
			for(int i=0;i<=id.length-1;i++) {
				String sql = "delete from user_inf where id='"+id[i]+"'";
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConn(conn);
		}
	}

	@Override
	public void updateUsers(User user) {
		Connection conn = JDBCUtils.getConnection();
		String sql = "update user_inf set loginname=?,password=?,status=?,username=? where id=?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1,user.getLoginname());
			pstm.setString(2,user.getPassword());
			pstm.setInt(3,user.getStatus());
			pstm.setString(4,user.getUsername());
			pstm.setInt(5, user.getId());
			int rs = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConn(conn);
		}
	}

	@Override
	public User findUsers(Integer id) {
		Connection conn = JDBCUtils.getConnection();
		String sql = "select *from user_inf where id=?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setLoginname(rs.getString("loginname"));
				u.setPassword(rs.getString("password"));
				u.setCreatedate(rs.getDate("createdate"));
				u.setUsername(rs.getString("username"));
				u.setStatus(rs.getInt("status"));
				return u;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.closeConn(conn);
		}
		return null;
	}

	@Override
	public User findUserById(int id) {
		Connection conn = JDBCUtils.getConnection();
		String sql = "select *from user_inf where id=?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLoginname(rs.getString("loginname"));
				user.setPassword(rs.getString("password"));
				user.setCreatedate(rs.getDate("createdate"));
				user.setUsername(rs.getString("username"));
				user.setStatus(rs.getInt("status"));
				return user;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConn(conn);
		}
		return null;
	}

}
