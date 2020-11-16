package org.gec.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.gec.bean.User;
import org.gec.dao.UserDao;
import org.gec.util.JDBCUtils;
import org.gec.util.PageModel;

public class UserDaoImpl implements UserDao {

	@Override
	public User login(String loginname, String password) {
		Connection conn = JDBCUtils.getConnection();

		String sql = "select * from user_inf where loginname=? and password=?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, loginname);
			pstm.setString(2, password);

			// 执行查询
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
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
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		
		return null;
	}

	@Override
	public List<User> findUsersByPage(User user,PageModel model) {
		List<User> users = new ArrayList<>();
		Connection conn = JDBCUtils.getConnection();

		//1=1 条件永远成立，为了拼接条件 
		StringBuffer sql = new StringBuffer("select * from user_inf where 1=1 ");
		try {
			Statement pstm = conn.createStatement();
			
			//判断
			if(StringUtils.isNotBlank(user.getLoginname())) {
				sql.append(" and loginname like '%").append(user.getLoginname()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(StringUtils.isNotBlank(user.getUsername())) {
				sql.append(" and username like '%").append(user.getUsername()).append("%'");
				System.out.println("sql:"+sql);
			}
			
			if(user.getStatus() != null) {
				sql.append(" and status = ").append(user.getStatus());
			}
			
			//添加分页
			sql.append(" limit ").append(model.getStartRow()).append(",").append(PageModel.pageSize);

			// 执行查询
			ResultSet rs = pstm.executeQuery(sql.toString());
			System.out.println("sql:" + sql);
			while (rs.next()) {
				//shift+alt+A 可以批量修改 恢复也是按这个快捷方式
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
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		
		return null;
	}

	@Override
	public int getTotalCountByUser(User user) {
		List<User> users = new ArrayList<>();
		Connection conn = JDBCUtils.getConnection();

		//1=1 条件永远成立，为了拼接条件 
		StringBuffer sql = new StringBuffer("select count(*) from user_inf where 1=1 ");
		try {
			Statement pstm = conn.createStatement();
			
			//判断
			if(StringUtils.isNotBlank(user.getLoginname())) {
				sql.append(" and loginname='").append(user.getLoginname()).append("'");
				
			}
			if(StringUtils.isNotBlank(user.getUsername())) {
				sql.append(" and username like '%").append(user.getUsername()).append("%'");
			}
			
			if(user.getStatus() != null) {
				sql.append(" and status = ").append(user.getStatus());
			}
			
			ResultSet rs = pstm.executeQuery(sql.toString());
			//
			while(rs.next()) {
				//columnIndex the first column is 1, the second is 2
				return rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConn(conn);
		}
		
		return 0;
	}
          //添加用户
	@Override
	public boolean addUsers(User user) {
		Connection conn = JDBCUtils.getConnection();
           
		String sql = "insert into user_inf(loginname,password,status,username) values(?,?,?,?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getLoginname());
			pstm.setString(2, user.getPassword());
			pstm.setInt(3, user.getStatus());
			pstm.setString(4, user.getUsername());
//			String date;
//			pstm.setString(4, user.getCreatedate());

			// 执行查询
			int rs= pstm.executeUpdate();
			 if(rs>0) {
				 return true;
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		 return false;
	}

	//删除用户
	@Override
	public void deleteUsers(String[] id) {
		Connection conn = JDBCUtils.getConnection();
//		System.out.println(sql);
		try { 
			for(int i=0;i<=id.length-1;i++) {
            	 String sql = "delete from user_inf where id='"+id[i]+"'";
             
			PreparedStatement pstm = conn.prepareStatement(sql);
//			pstm.setString(1, "id");;
			 pstm.executeUpdate();
            }
			// 执行查询
			
//			  if(rs!=0) {
//				 System.out.println(rs);
//			 }else {
//				 System.out.println("删除失败");
//			 }
			 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		
		
	}

	//修改
	@Override
	public void update(User user) {
		Connection conn = JDBCUtils.getConnection();
        
		String sql = "update user_inf set loginname=? , password=? , status=? , username=? where id=?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getLoginname());
			pstm.setString(2, user.getPassword());
			pstm.setInt(3, user.getStatus());
			pstm.setString(4, user.getUsername());
			pstm.setInt(5, user.getId());
//			String date;
//			pstm.setString(4, user.getCreatedate());

			// 执行查询
			int rs= pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		
	}

	@Override
	public User findUsers(Integer id) {
		Connection conn = JDBCUtils.getConnection();

		String sql = "select * from user_inf where id=? ";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			// 执行查询
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
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
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		return null;
	}

	@Override
	public User findUserById(int id) {
		Connection conn = JDBCUtils.getConnection();

		String sql = "select * from user_inf where id=?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);

			// 执行查询
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
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
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		
		return null;
	}

}
