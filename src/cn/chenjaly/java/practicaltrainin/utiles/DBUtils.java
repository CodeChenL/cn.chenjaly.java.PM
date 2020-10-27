package cn.chenjaly.java.practicaltrainin.utiles;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DBUtils {
    private static Properties p = new Properties();
    private static DataSource ds;
    static {
        try (InputStream is = DBUtils.class.getResourceAsStream("/db.properties")){
            p.load(is);
            ds = DruidDataSourceFactory.createDataSource(p);
            System.out.println("con:"+ds.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = ds.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    public  static void closeConn(Connection conn){
        try {
            if (conn!=null)conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
