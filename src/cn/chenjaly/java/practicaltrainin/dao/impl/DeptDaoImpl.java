package cn.chenjaly.java.practicaltrainin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.chenjaly.java.practicaltrainin.bean.Dept;
import cn.chenjaly.java.practicaltrainin.dao.DeptDao;
import cn.chenjaly.java.practicaltrainin.utils.JDBCUtils;
import cn.chenjaly.java.practicaltrainin.utils.PageModel;

public class DeptDaoImpl implements DeptDao {

	@Override
	public List<Dept> findDept(Dept dept, PageModel model) {
		List<Dept> depts = new ArrayList<>();
		Connection conn = JDBCUtils.getConnection();
		StringBuffer  sql = new StringBuffer("select * from dept_inf where 1=1 ");
		try {
			Statement pstm = conn.createStatement();
			
			if(StringUtils.isNoneBlank(dept.getName())) {
				sql.append("and name like '%").append(dept.getName()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(StringUtils.isNotBlank(dept.getRemark())) {
				sql.append("and remark like'%").append(dept.getRemark()).append("%'");
				System.out.println("sql:"+sql);
			}
			
			sql.append(" limit ").append(model.getStartRow()).append(",").append(PageModel.pageSize);
			
			ResultSet rs = pstm.executeQuery(sql.toString());
			System.out.println("sql:"+sql);
			
			while(rs.next()) {
				Dept d = new  Dept();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setRemark(rs.getString("remark"));
				depts.add(d);
			}
			return depts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConn(conn);
		}
		return null;
	}

	@Override
	public int getTotalCountByDept(Dept dept) {
		List<Dept> depts = new ArrayList<>();
		Connection conn = JDBCUtils.getConnection();
		StringBuffer sql =new StringBuffer("select count(*) from dept_inf where 1=1 ");
		try {
			Statement pstm = conn.createStatement();
			if(StringUtils.isNoneBlank(dept.getName())) {
				sql.append(" and name like '&").append(dept.getName()).append("%'");
			}
			if(StringUtils.isNoneBlank(dept.getRemark())) {
				sql.append(" and remark like '%").append(dept.getRemark()).append("%'");
			}
			ResultSet rs = pstm.executeQuery(sql.toString());
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConn(conn);
		}
		
		
		return 0;
	}

	@Override
	public boolean addDept(Dept dept) {
		Connection conn = JDBCUtils.getConnection();
		String sql = "insert into dept_inf (name,remark) values(?,?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1,dept.getName());
			pstm.setString(2, dept.getRemark());
			
			int rs = pstm.executeUpdate();
			if(rs>0) {
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
	public void updateDept(Dept dept) {
		Connection conn = JDBCUtils.getConnection();
		String sql = "update dept_inf set name =? ,remark = ? where id= ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1,dept.getName());
			pstm.setString(2, dept.getRemark());
			pstm.setInt(3, dept.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConn(conn);
		}
		
		
	}

	@Override
	public Dept findDept(Integer id) {
		Connection conn = JDBCUtils.getConnection();
		String sql = "select * from dept_inf where id=?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1,id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				Dept dept = new Dept();
				dept.setId(rs.getInt("id"));
				dept.setName(rs.getString("name"));
				dept.setRemark(rs.getString("remark"));
				return dept;
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

	@Override
	public void deleteDept(String[] id) {
		Connection conn = JDBCUtils.getConnection();
		try {
		for(int i=0;i<=id.length-1;i++) {
			String sql = "delete from dept_inf where id = '"+id[i]+"'";
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

	
	
}
