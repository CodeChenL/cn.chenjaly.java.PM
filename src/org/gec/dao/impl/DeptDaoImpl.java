package org.gec.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.gec.bean.Dept;
import org.gec.bean.User;
import org.gec.dao.DeptDao;
import org.gec.util.JDBCUtils;
import org.gec.util.PageModel;

public class DeptDaoImpl implements DeptDao {

	@Override
	public List<Dept> findDept(Dept dept,PageModel model) {
		List<Dept> depts = new ArrayList<>();
		Connection conn = JDBCUtils.getConnection();
		StringBuffer sql = new StringBuffer("select * from dept_inf where 1=1 ");
		try {
			Statement pstm = conn.createStatement();
			
			//判断
			if(StringUtils.isNotBlank(dept.getName())) {
				sql.append("and name like '%").append(dept.getName()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(StringUtils.isNotBlank(dept.getRemark())) {
				sql.append(" and remark like '%").append(dept.getRemark()).append("%'");
				System.out.println("sql:"+sql);
			}
			
			//添加分页
			sql.append(" limit ").append(model.getStartRow()).append(",").append(PageModel.pageSize);

			// 执行查询
			ResultSet rs = pstm.executeQuery(sql.toString());
			System.out.println("sql:" + sql);
			while (rs.next()) {
				//shift+alt+A 可以批量修改 恢复也是按这个快捷方式
				Dept d=new Dept();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setRemark(rs.getString("remark"));
				depts.add(d);
			}
			
			return depts;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		
		return null;
	}
	
	//分页
	@Override
	public int getTotalCountByDept(Dept dept) {
		List<Dept> depts = new ArrayList<>();
		Connection conn = JDBCUtils.getConnection();

		//1=1 条件永远成立，为了拼接条件 
		StringBuffer sql = new StringBuffer("select count(*) from dept_inf where 1=1  ");
		try {
			Statement pstm = conn.createStatement();
			
			//判断
			if(StringUtils.isNotBlank(dept.getName())) {
				sql.append(" and name like '%").append(dept.getName()).append("%'");
				
			}
			if(StringUtils.isNotBlank(dept.getRemark())) {
				sql.append(" and remark like '%").append(dept.getRemark()).append("%'");
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
 
	//添加
	@Override
	public boolean addDept(Dept dept) {
		Connection conn = JDBCUtils.getConnection();
        
		String sql = "insert into dept_inf(name,remark) values(?,?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, dept.getName());
			pstm.setString(2, dept.getRemark());
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

	//修改
	@Override
	public void updateDept(Dept dept) {
            Connection conn = JDBCUtils.getConnection();
        
		String sql = "update dept_inf set name=? , remark=? where id=? ";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, dept.getName());
			pstm.setString(2, dept.getRemark());
			pstm.setInt(3, dept.getId());
//			String date;
//			pstm.setString(4, user.getCreatedate());

			// 执行查询
			 pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		
	}

	//删除
	@Override
	public void deleteDept(String[] id) {
		Connection conn = JDBCUtils.getConnection();
//		System.out.println(sql);
		try { 
			for(int i=0;i<=id.length-1;i++) {
            	 String sql = "delete from dept_inf where id='"+id[i]+"'";
             
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

	@Override
	public Dept findDept(Integer id) {
		Connection conn = JDBCUtils.getConnection();

		String sql = "select * from dept_inf where id=? ";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			// 执行查询
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Dept dept=new Dept();
				dept.setId(rs.getInt("id"));
				dept.setName(rs.getString("name"));
				dept.setRemark(rs.getString("remark"));
				return dept;
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
