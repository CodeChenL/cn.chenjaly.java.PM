package cn.chenjaly.java.practicaltrainin.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.chenjaly.java.practicaltrainin.bean.Employee;
import cn.chenjaly.java.practicaltrainin.dao.EmployeeDao;
import cn.chenjaly.java.practicaltrainin.utils.JDBCUtils;
import cn.chenjaly.java.practicaltrainin.utils.PageModel;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public List<Employee> findEmployee(Employee employee, PageModel model) {
		List<Employee> employees = new ArrayList<>();
		Connection conn = JDBCUtils.getConnection();
		StringBuffer  sql = new StringBuffer("select * from employee_inf where 1=1 and employee_inf.job_id = job_inf.id and employee.dept_id = dept_inf.id ");
		try {
			Statement pstm = conn.createStatement();
			
			if(StringUtils.isNoneBlank(employee.getName())) {
				sql.append("and name like '%").append(employee.getName()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(employee.getSex()!=null) {
				sql.append("and sex like '%").append(employee.getSex()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(StringUtils.isNotBlank(employee.getCard_id())) {
				sql.append("and card_id like'%").append(employee.getCard_id()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(StringUtils.isNotBlank(employee.getPhone())) {
				sql.append("and phone like'%").append(employee.getPhone()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(employee.getJob_id()!=null) {
				sql.append("and job_id =").append(employee.getJob_id());
				System.out.println("sql:"+sql);
			}
			if(employee.getDept_id()!=null) {
				sql.append("and dept_id =").append(employee.getDept_id());
				System.out.println("sql:"+sql);
			}
			sql.append(" limit ").append(model.getStartRow()).append(",").append(PageModel.pageSize);
			
			ResultSet rs = pstm.executeQuery(sql.toString());
			System.out.println("sql:"+sql);
			
			while(rs.next()) {
				Employee e = new Employee();
				e.setName(rs.getString("name"));
				e.setSex(rs.getInt("sex"));
				e.setPhone(rs.getString("phone"));
				e.setEmail(rs.getString("email"));
				e.setJob_id(rs.getInt("job_id"));
				e.setEducation(rs.getString("education"));
				e.setCard_id(rs.getString("card_id"));
				e.setDept_id(rs.getInt("dept_id"));
				e.setAddress(rs.getString("address"));
				e.setCreatedate(rs.getDate("createdate"));
				employees.add(e);
			}
			return employees;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConn(conn);
		}
		return null;
	}

	@Override
	public int getTotalCountByEmployee(Employee employee) {
		List<Employee> employees = new ArrayList<>();
		Connection conn = JDBCUtils.getConnection();
		StringBuffer sql =new StringBuffer("select count(*) from employee_inf where 1=1 and employee_inf.job_id = job_inf.id and employee.dept_id = dept_inf.id");
		try {
			Statement pstm = conn.createStatement();
			if(StringUtils.isNoneBlank(employee.getName())) {
				sql.append("and name like '%").append(employee.getName()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(employee.getSex()!=null) {
				sql.append("and sex like '%").append(employee.getSex()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(StringUtils.isNotBlank(employee.getCard_id())) {
				sql.append("and card_id like'%").append(employee.getCard_id()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(StringUtils.isNotBlank(employee.getPhone())) {
				sql.append("and phone like'%").append(employee.getPhone()).append("%'");
				System.out.println("sql:"+sql);
			}
			if(employee.getJob_id()!=null) {
				sql.append("and job_id =").append(employee.getJob_id());
				System.out.println("sql:"+sql);
			}
			if(employee.getDept_id()!=null) {
				sql.append("and dept_id =").append(employee.getDept_id());
				System.out.println("sql:"+sql);
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
	public Employee findEmployee(Integer id) {
		Connection conn = JDBCUtils.getConnection();
		String sql = "select * from employee_inf where id=?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1,id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				Employee e = new Employee();
				e.setName(rs.getString("name"));
				e.setSex(rs.getInt("sex"));
				e.setPhone(rs.getString("phone"));
				e.setEmail(rs.getString("email"));
				e.setJob_id(rs.getInt("job_id"));
				e.setEducation(rs.getString("education"));
				e.setCard_id(rs.getString("card_id"));
				e.setDept_id(rs.getInt("dept_id"));
				e.setAddress(rs.getString("address"));
				e.setCreatedate(rs.getDate("createdate"));
				return e;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConn(conn);
		}
		return null;
	}

	@Override
	public boolean addEmployee(Employee employee) {
		Connection conn = JDBCUtils.getConnection();
		String sql = "insert into employee_inf (name,cardId,sex,job_id,education,email,phone,tel,party,qqnum,address,postcode,birthday,race,speciality,hobby,remark,dept_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1,employee.getName());
			pstm.setString(2, employee.getCard_id());
			pstm.setInt(3, employee.getSex());
			pstm.setInt(4, employee.getJob_id());
			pstm.setString(5, employee.getEducation());
			pstm.setString(6, employee.getEmail());
			pstm.setString(7, employee.getPhone());
			pstm.setString(8, employee.getTel());
			pstm.setString(9, employee.getParty());
			pstm.setString(10, employee.getQqnum());
			pstm.setString(11, employee.getAddress());
			pstm.setString(12, employee.getPost_code());
			pstm.setDate(13, (Date) employee.getBirthday());
			pstm.setString(14, employee.getRace());
			pstm.setString(15, employee.getSpeciality());
			pstm.setString(16, employee.getHobby());
			pstm.setString(17, employee.getRemark());
			pstm.setInt(18, employee.getDept_id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void updateEmployee(Employee employee) {
		Connection conn = JDBCUtils.getConnection();
		String sql = "update employee_inf name=?,cardId=?,sex=?,job_id=?,education=?,email=?,phone=?,tel=?,party=?,qqnum=?,address=?,postcode=?,birthday=?,race=?,speciality=?,hobby=?,remark=?,dept_id=? where id= ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1,employee.getName());
			pstm.setString(2, employee.getCard_id());
			pstm.setInt(3, employee.getSex());
			pstm.setInt(4, employee.getJob_id());
			pstm.setString(5, employee.getEducation());
			pstm.setString(6, employee.getEmail());
			pstm.setString(7, employee.getPhone());
			pstm.setString(8, employee.getTel());
			pstm.setString(9, employee.getParty());
			pstm.setString(10, employee.getQqnum());
			pstm.setString(11, employee.getAddress());
			pstm.setString(12, employee.getPost_code());
			pstm.setDate(13, (Date) employee.getBirthday());
			pstm.setString(14, employee.getRace());
			pstm.setString(15, employee.getSpeciality());
			pstm.setString(16, employee.getHobby());
			pstm.setString(17, employee.getRemark());
			pstm.setInt(18, employee.getDept_id());
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConn(conn);
		}
	}

	@Override
	public void deleteEmployee(String[] id) {
		Connection conn = JDBCUtils.getConnection();
		try {
		for(int i=0;i<=id.length-1;i++) {
			String sql = "delete from employee_inf where id = '"+id[i]+"'";
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
