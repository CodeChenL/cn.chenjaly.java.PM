package cn.chenjaly.java.practicaltrainin.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import cn.chenjaly.java.practicaltrainin.utils.JDBCUtils;

public class UserServiceTest {
	public static void main(String[] args) {
		Connection conn = JDBCUtils.getConnection();
		String sql = "select * from user_inf where loginname=? and password=?";
		try {
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, "admin");
		pstm.setString(2, "123456");
		
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString("loginname"));
			System.out.println(rs.getString("password"));
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		JDBCUtils.closeConn(conn);
	}
	}
}
