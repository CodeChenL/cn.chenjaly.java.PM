package cn.chenjaly.java.practicaltrainin.service;

import cn.chenjaly.java.practicaltrainin.utiles.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
    static void testLogin(){
        Connection conn = DBUtils.getConnection();
        String sql = "select * from user_inf where loginname=? and password=?";
        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            System.out.println(pstm);
            pstm.setString(1,"admin");
            pstm.setString(2,"123456");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("loginname"));
                System.out.println(rs.getString("password"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DBUtils.closeConn(conn);
        }
    }

    public static void main(String[] args) {
        test.testLogin();
    }
}
