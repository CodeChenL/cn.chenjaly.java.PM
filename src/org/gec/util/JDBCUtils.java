package org.gec.util;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtils {
	private static Properties p = new Properties();
	
	private static DataSource ds;
	
	static {
		try(InputStream is = JDBCUtils.class.getResourceAsStream("/db.properties")){
			//System.out.println("is:" + is);
			p.load(is);
			
			//创建数据连接池
			ds = DruidDataSourceFactory.createDataSource(p);
			
			System.out.println("conn:" + ds.getConnection());
		} catch (Exception e) {

		}
	}
	
	//提供连接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//释放连接
	public static void closeConn(Connection conn) {
		try {
			if(conn != null) conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
	}
}
