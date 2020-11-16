package org.gec.dao.impl;

import java.io.UnsupportedEncodingException;
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
import org.gec.bean.Notice;
import org.gec.bean.Type;
import org.gec.bean.User;
import org.gec.dao.NoticeDao;
import org.gec.util.JDBCUtils;
import org.gec.util.PageModel;

public class NoticeDaoImpl implements NoticeDao {

	//查询
	@Override
	public List<Notice> findNotice(Notice not, PageModel model) {
		List<Notice> nots = new ArrayList<>();
		Connection conn = JDBCUtils.getConnection();
		StringBuffer sql = new StringBuffer("select * from notice_inf  , type_inf , user_inf where 1=1 and notice_inf.user_id=user_inf.id and notice_inf.type_id=type_inf.id ");
		try {
			Statement pstm = conn.createStatement();
			
			//判断
			if(StringUtils.isNotBlank(not.getTitle())) {
				sql.append(" and title like '%").append(not.getTitle()).append("%'");
				System.out.println("sql:"+sql);
			}
			
			if(not.getTypeId() != null) {
				sql.append(" and type_id = ").append(not.getTypeId());
			}
			//添加分页
			sql.append(" limit ").append(model.getStartRow()).append(",").append(PageModel.pageSize);

			// 执行查询
			ResultSet rs = pstm.executeQuery(sql.toString());
			System.out.println("sql:" + sql);
			while (rs.next()) {
				//shift+alt+A 可以批量修改 恢复也是按这个快捷方式
				Notice n=new Notice();
		        	n.setId(rs.getInt("id"));
	                n.setTitle(rs.getString("title"));
	                n.setTypeId(rs.getInt("type_id"));
	                n.setUserId(rs.getInt("user_id"));
	                try {
						n.setRemark(new String(rs.getString("remark").getBytes("ISO-8859-1"), "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                n.setCreatedate(rs.getDate("create_date"));
	               n.setUser(new User(rs.getInt("id"),rs.getString("username"),rs.getString("loginname")));
	               n.setType(new Type(rs.getInt("id"),rs.getString("name")));
				
				
				nots.add(n);
			}
			
			return nots;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		
		return null;
	}

	@Override
	public int getTotalCountByNotice(Notice not) {
		Connection conn = JDBCUtils.getConnection();
		StringBuffer sql = new StringBuffer("select * from notice_inf  , type_inf , user_inf where 1=1 and notice_inf.user_id=user_inf.id and notice_inf.type_id=type_inf.id ");
		try {
			Statement pstm = conn.createStatement();
			
			//判断
			if(StringUtils.isNotBlank(not.getTitle())) {
				sql.append(" and title like '%").append(not.getTitle()).append("%'");
				System.out.println("sql:"+sql);
			}
			
			if(not.getTypeId() != null) {
				sql.append(" and type_id = ").append(not.getTypeId());
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

	@Override
	public Notice findNotice(Integer id) {
		Connection conn = JDBCUtils.getConnection();

		//1=1 条件永远成立，为了拼接条件 
		String sql = "select * from notice_inf , type_inf , user_inf where notice_inf.id=? ";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			// 执行查询
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Notice n=new Notice();
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("title"));
                n.setTypeId(rs.getInt("type_id"));
                n.setUserId(rs.getInt("user_id"));
                try {
					n.setRemark(new String(rs.getString("remark").getBytes("ISO-8859-1"), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                n.setCreatedate(rs.getDate("create_date"));
               n.setUser(new User(rs.getInt("id"),rs.getString("username"),rs.getString("loginname")));
               n.setType(new Type(rs.getInt("id"),rs.getString("name")));
                return n;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		return null;
	}
	//添加
	@Override
	public void addNotice(Notice not) {
		 Connection conn = JDBCUtils.getConnection();
	        
			String sql = "insert into notice_inf(title,type_id,user_id,remark)  values(?,?,?,?)";
			try {
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setString(1, not.getTitle());
				pstm.setInt(2, not.getTypeId());
				pstm.setInt(3, not.getUserId());
				pstm.setString(4, not.getRemark());
				// 执行查询
				 pstm.executeUpdate();
				 
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.closeConn(conn);
			}
			 
		}
	
	//修改
	@Override
	public void update(Notice not) {
		 Connection conn = JDBCUtils.getConnection();
	        
			String sql = "update notice_inf set title=?,type_id=?,remark=? where id=?";
			try {
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setString(1, not.getTitle());
				pstm.setInt(2, not.getTypeId());
				pstm.setString(3, not.getRemark());
				pstm.setInt(4, not.getId());

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
	public void deleteNotice(String[] id) {
		
		   Connection conn = JDBCUtils.getConnection();
	        
			try {
				for(int i=0;i<=id.length-1;i++) {
	           	 String sql = "delete from notice_inf where id='"+id[i]+"'";
	            
				PreparedStatement pstm = conn.prepareStatement(sql);
//				pstm.setString(1, "id");;
				 pstm.executeUpdate();

				// 执行查询
				}	 
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.closeConn(conn);
			}
		
	}

	@Override
	public List<Type> findType() {
		Connection conn = JDBCUtils.getConnection();
		List<Type> type=new ArrayList<Type>();
		String sql = "select * from type_inf  ";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			// 执行查询
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Type t=new Type();
				t.setId(rs.getInt("id"));
				t.setName(rs.getString("name"));
				
				
				type.add(t);
			}
			
			return type;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		return null;
	}

	@Override
	public List<User> findUser() {
		Connection conn = JDBCUtils.getConnection();
		List<User> user=new ArrayList<User>();
		String sql = "select * from user_inf  ";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			// 执行查询
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				User u=new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setLoginname(rs.getString("loginname"));
				
				
				user.add(u);
			}
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		return null;
	}

	@Override
	public Notice showNotice(Integer id) {
		Connection conn = JDBCUtils.getConnection();

		//1=1 条件永远成立，为了拼接条件 
		String sql = "select * from notice_inf where id=? ";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			// 执行查询
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Notice n=new Notice();
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("title"));
                n.setTypeId(rs.getInt("type_id"));
                n.setUserId(rs.getInt("user_id"));
                try {
					n.setRemark(new String(rs.getString("remark").getBytes("ISO-8859-1"), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                n.setCreatedate(rs.getDate("create_date"));
               n.setUser(new User(rs.getInt("id")));
//               n.setType(new Type(rs.getInt("id"),rs.getString("name")));
                return n;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		return null;
	}


}
