package org.gec.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.gec.bean.Dept;
import org.gec.bean.Employee;
import org.gec.bean.Job;
import org.gec.bean.User;
import org.gec.dao.EmployeeDao;
import org.gec.util.JDBCUtils;
import org.gec.util.PageModel;

public class EmployeeDaoImpl implements EmployeeDao {

	//查询
	@Override
	public List<Employee> findEmployee(Employee emp, PageModel model) {
		List<Employee> emps = new ArrayList<>();
		Connection conn = JDBCUtils.getConnection();
		StringBuffer sql = new StringBuffer("select * from employee_inf  , job_inf  , dept_inf  where 1=1 and employee_inf.job_id=job_inf.id and employee_inf.dept_id=dept_inf.id ");
		try {
			Statement pstm = conn.createStatement();
			
			//判断
			if(StringUtils.isNotBlank(emp.getName())) {
				sql.append(" and employee_inf.name like '%").append(emp.getName()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(emp.getSex() !=null) {
				sql.append(" and sex = ").append(emp.getSex());
				System.out.println("sql:"+sql);
			}
			if(StringUtils.isNotBlank(emp.getCardId())) {
				sql.append(" and card_id like '%").append(emp.getCardId()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(StringUtils.isNotBlank(emp.getPhone())) {
				sql.append(" and phone like '%").append(emp.getPhone()).append("%'");
				System.out.println("sql:"+sql);
			}
			
			if(emp.getJobId() != null) {
				sql.append(" and job_id = ").append(emp.getJobId());
			}
			if(emp.getDeptId() != null) {
				sql.append(" and dept_id = ").append(emp.getDeptId());
			}
			//添加分页
			sql.append(" limit ").append(model.getStartRow()).append(",").append(PageModel.pageSize);

			// 执行查询
			ResultSet rs = pstm.executeQuery(sql.toString());
			System.out.println("sql:" + sql);
			while (rs.next()) {
				//shift+alt+A 可以批量修改 恢复也是按这个快捷方式
				Employee e=new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSex(rs.getInt("sex"));
				e.setPhone(rs.getString("phone"));
				e.setEmail(rs.getString("email"));
				e.setJobId(rs.getInt("job_id"));
				e.setEducation(rs.getString("education"));
				e.setCardId(rs.getString("card_id"));
				e.setDeptId(rs.getInt("dept_id"));
				e.setAddress(rs.getString("address"));
				e.setCreatedate(rs.getDate("create_date"));
				e.setPostcode(rs.getString("post_code"));
				
				e.setJob(new Job(rs.getInt("id"),rs.getString("job_inf.name"),rs.getString("job_inf.remark")));
				e.setDept(new Dept(rs.getInt("id"),rs.getString("dept_inf.name"),rs.getString("dept_inf.remark")));
				emps.add(e);
			}
			
			return emps;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		
		return null;
	}

	//分页
	@Override
	public int getTotalCountByEmployee(Employee emp) {
		List<Employee> emps = new ArrayList<>();
		Connection conn = JDBCUtils.getConnection();

		//1=1 条件永远成立，为了拼接条件 
		StringBuffer sql = new StringBuffer("select * from employee_inf as emp , job_inf as job , dept_inf as dept where 1=1 and emp.job_id=job.id and emp.dept_id=dept.id ");
		try {
			Statement pstm = conn.createStatement();
			
			//判断
			if(StringUtils.isNotBlank(emp.getName())) {
				sql.append(" and emp.name like '%").append(emp.getName()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(StringUtils.isNotBlank(emp.getCardId())) {
				sql.append(" and card_id like '%").append(emp.getCardId()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(StringUtils.isNotBlank(emp.getPhone())) {
				sql.append(" and phone like '%").append(emp.getPhone()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(emp.getSex() !=null) {
				sql.append(" and sex = ").append(emp.getSex());
				System.out.println("sql:"+sql);
			}
			if(emp.getJobId() != null) {
				sql.append(" and job_id = ").append(emp.getJobId());
			}
			if(emp.getDeptId() != null) {
				sql.append(" and dept_id = ").append(emp.getDeptId());
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
	public boolean addEmployee(Employee emp) {
           Connection conn = JDBCUtils.getConnection();

		String sql = "insert into employee_inf(dept_id,job_id,name,card_id,address,post_code,tel,phone,qq_num,email,sex,party,birthday,race,education,speciality,hobby,remark) "
				+ "   values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1,emp.getDeptId());
			pstm.setInt(2,emp.getJobId());
			pstm.setString(3, emp.getName());
			pstm.setString(4,emp.getCardId());
			pstm.setString(5, emp.getAddress());
			pstm.setString(6, emp.getPostcode());
			pstm.setString(7, emp.getTel());
			pstm.setString(8, emp.getPhone());
			pstm.setString(9, emp.getQqnum());
			pstm.setString(10, emp.getEmail());
			pstm.setInt(11, emp.getSex());
			pstm.setString(12, emp.getParty());
			pstm.setDate(13, new Date(emp.getBirthday().getTime()));
			pstm.setString(14, emp.getEducation());
			pstm.setString(15, emp.getRace());
			pstm.setString(16, emp.getSpeciality());
			pstm.setString(17, emp.getHobby());
			pstm.setString(18, emp.getRemark());
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

	//删除
	@Override
	public void deleteEmployee(String[] id) {
             Connection conn = JDBCUtils.getConnection();
        
		try {
			for(int i=0;i<=id.length-1;i++) {
           	 String sql = "delete from employee_inf where id='"+id[i]+"'";
            
			PreparedStatement pstm = conn.prepareStatement(sql);
//			pstm.setString(1, "id");;
			 pstm.executeUpdate();

			// 执行查询
			}	 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		

	}

	//修改
	@Override
	public void update(Employee emp) {
		
		 Connection conn = JDBCUtils.getConnection();
	        
			String sql = "update employee_inf set dept_id=?,job_id=?,name=?,card_id=?,address=?,post_code=?,tel=?,"
					+ "phone=?,qq_num=?,email=?,sex=?,party=?,birthday=?,race=?,education=?,speciality=?,hobby=?,remark=? where id=?";
			try {
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setInt(1,emp.getDeptId());
				pstm.setInt(2,emp.getJobId());
				pstm.setString(3, emp.getName());
				pstm.setString(4,emp.getCardId());
				pstm.setString(5, emp.getAddress());
				pstm.setString(6, emp.getPostcode());
				pstm.setString(7, emp.getTel());
				pstm.setString(8, emp.getPhone());
				pstm.setString(9, emp.getQqnum());
				pstm.setString(10, emp.getEmail());
				pstm.setInt(11, emp.getSex());
				pstm.setString(12, emp.getParty());
				pstm.setDate(13, new Date(emp.getBirthday().getTime()));
				pstm.setString(14, emp.getEducation());
				pstm.setString(15, emp.getRace());
				pstm.setString(16, emp.getSpeciality());
				pstm.setString(17, emp.getHobby());
				pstm.setString(18, emp.getRemark());
				pstm.setInt(19, emp.getId());
//				String date;
//				pstm.setString(4, user.getCreatedate());

				// 执行查询
				pstm.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.closeConn(conn);
			}

	}

	//查找
	@Override
	public Employee findEmployees(Integer id) {
		
		Connection conn = JDBCUtils.getConnection();

		//1=1 条件永远成立，为了拼接条件 
		String sql = "select * from employee_inf , job_inf , dept_inf where employee_inf.id=? ";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			// 执行查询
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
                Employee emp=new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setCardId(rs.getString("card_id"));
                emp.setSex(rs.getInt("sex"));
                emp.setJobId(rs.getInt("job_id"));
                emp.setEducation(rs.getString("education"));
                emp.setPhone(rs.getString("phone"));
                emp.setEmail(rs.getString("email"));
                emp.setRace(rs.getString("race"));
                emp.setQqnum(rs.getString("qq_num"));
                emp.setTel(rs.getString("tel"));
                emp.setDeptId(rs.getInt("dept_id"));
                emp.setAddress(rs.getString("address"));
                emp.setBirthday(rs.getDate("birthday"));
                emp.setParty(rs.getString("party"));
                emp.setHobby(rs.getString("hobby"));
                emp.setRemark(rs.getString("remark"));
                emp.setSpeciality(rs.getString("speciality"));
                emp.setCreatedate(rs.getDate("create_date"));
                emp.setPostcode(rs.getString("post_code"));
                emp.setJob(new Job(rs.getInt("id"),rs.getString("name"),rs.getString("remark")));
                emp.setDept(new Dept(rs.getInt("id"),rs.getString("name"),rs.getString("remark")));
                return emp;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		return null;
	}

	@Override
	public List<Dept> findDept() {
		Connection conn = JDBCUtils.getConnection();
		List<Dept> dept=new ArrayList<Dept>();
		String sql = "select * from dept_inf  ";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			// 执行查询
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Dept d=new Dept();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setRemark(rs.getString("remark"));
				
				dept.add(d);
			}
			
			return dept;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		return null;
	}

	@Override
	public List<Job> findJob() {
		Connection conn = JDBCUtils.getConnection();
		List<Job> job=new ArrayList<Job>();
		String sql = "select * from job_inf  ";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			// 执行查询
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Job j=new Job();
				j.setId(rs.getInt("id"));
				j.setName(rs.getString("name"));
				j.setRemark(rs.getString("remark"));
				
				job.add(j);
			}
			
			return job;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		return null;
	}

	@Override
	public String findCardId(String cardId) {
		Connection conn = JDBCUtils.getConnection();
//		List<Job> job=new ArrayList<Job>();
		String sql = "select card_id from employee_inf where card_id=?  ";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			 pstm.setString(1, cardId);
			// 执行查询
			ResultSet rs= pstm.executeQuery();
			while(rs.next()) {
				return cardId;
			}
				
				
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		return null;
	}
}
