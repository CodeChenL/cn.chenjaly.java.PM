package cn.chenjaly.java.practicaltrainin.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtils {
	private static final Properties p = new Properties();
	private static DataSource ds;
	
	static {
		try(InputStream is = JDBCUtils.class.getResourceAsStream("/db.properties")){
			p.load(is);
			ds = DruidDataSourceFactory.createDataSource(p);
			System.out.println("conn:"+ds.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}
	
	public static void closeConn(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[]args) {
		
	}
}
