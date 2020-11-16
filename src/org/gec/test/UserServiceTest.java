package org.gec.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.gec.bean.User;
import org.gec.service.UserService;
import org.gec.service.impl.UserServiceImpl;
import org.gec.util.JDBCUtils;
import org.gec.util.PageModel;

class UserServiceTest {
	void testLogin() {
		Connection conn = JDBCUtils.getConnection();

		String sql = "select * from user_inf where loginname=? and password=?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, "admin");
			pstm.setString(2, "123456");

			// 执行查询
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("loginname"));
				System.out.println(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
	}
	void testFindUsersByPage() {
		/**
		 * List<User> users = new ArrayList<>();
		Connection conn = JDBCUtils.getConnection();

		//1=1 条件永远成立，为了拼接条件 
		StringBuffer sql = new StringBuffer("select * from user_inf where 1=1 ");
		try {
			Statement pstm = conn.createStatement();
			
			//判断
			sql.append(" and username like '%管%'");
			
			
			

			// 执行查询
			ResultSet rs = pstm.executeQuery(sql.toString());
			
			System.out.println("sql:" + pstm.toString());
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		 */
		UserService service = new UserServiceImpl();
		User user = new User();
		user.setUsername("管");
		
		PageModel model = new PageModel();
		model.setPageIndex(1);
		List<User> users = service.findUsersByPage(user,model);
		System.out.println(users.size());
	}

}
