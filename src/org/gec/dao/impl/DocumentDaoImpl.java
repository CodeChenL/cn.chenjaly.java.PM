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
import org.gec.bean.Document;
import org.gec.bean.Employee;
import org.gec.bean.User;
import org.gec.dao.DocumentDao;
import org.gec.util.JDBCUtils;
import org.gec.util.PageModel;

public class DocumentDaoImpl implements DocumentDao {

	//查询
	@Override
	public List<Document> findDocumentpage(Document doc) {
		Connection conn = JDBCUtils.getConnection();
        List<Document> docs=new ArrayList<Document>();
        StringBuffer sql = new StringBuffer("select * from document_inf left join user_inf on 1=1 and document_inf.USER_ID=user_inf.ID ");
		try {
			Statement pstm = conn.createStatement();
			
			//判断
			if(StringUtils.isNotBlank(doc.getTitle())) {
				sql.append(" and title like '%").append(doc.getTitle()).append("%'");
				System.out.println("sql:"+sql);
			}
			
			
//			//添加分页
//			sql.append(" limit ").append(model.getStartRow()).append(",").append(PageModel.pageSize);

			// 执行查询
			ResultSet rs = pstm.executeQuery(sql.toString());
			System.out.println("sql:" + sql);
			while (rs.next()) {
				
				Document d=new Document();
				d.setId(rs.getInt("id"));
				Integer userid = rs.getInt("user_id");
				d.setUser_id(userid);
				d.setTitle(rs.getString("title"));
				d.setRemark(rs.getString("remark"));
				d.setCreatedate(rs.getDate("create_date"));
				d.setFilename(rs.getString("filename"));
				d.setUser(new User(userid,rs.getString("loginname"),rs.getString("username")));
				docs.add(d);
			}
			
			return docs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		return null;
	}
	
	//分页
		@Override
		public int getTotalCountByDocument(Document doc) {
			Connection conn = JDBCUtils.getConnection();
	        StringBuffer sql = new StringBuffer("select * from docudemt_inf,user_inf where 1=1 ");
			try {
				Statement pstm = conn.createStatement();
				
				//判断
				if(StringUtils.isNotBlank(doc.getTitle())) {
					sql.append(" and title like '%").append(doc.getTitle()).append("%'");
					System.out.println("sql:"+sql);
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
	public void saveFile(Document doc) {
		Connection conn = JDBCUtils.getConnection();

		String sql = "insert into document_inf(title,filename,filetype,filebytes,remark,user_id) values(?,?,?,?,?,?) ";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, doc.getTitle());
			pstm.setString(2, doc.getFilename());
			pstm.setString(3, doc.getFiletype());
			pstm.setObject(4, doc.getFilebytes());
			pstm.setString(5, doc.getRemark());
            pstm.setInt(6, doc.getUser_id());
			// 执行查询
			 pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConn(conn);
		}
		

	}

	@Override
	public List<User> findUser() {
		Connection con=JDBCUtils.getConnection();
		List<User> user=new ArrayList<User>();
		String sql ="select * from user_inf";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			ResultSet rs=pstm.executeQuery();
			while(rs.next()) {
				User u=new User();
				u.setId(rs.getInt("id"));
				u.setLoginname(rs.getString("loginname"));
				user.add(u);
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConn(con);
		}
		return null;
	}
   //删除
	@Override
	public void deleteDocument(String[] id) {
		Connection conn = JDBCUtils.getConnection();
//		System.out.println(sql);
		try { 
			for(int i=0;i<=id.length-1;i++) {
            	 String sql = "delete from document_inf where id='"+id[i]+"'";
             
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

	//修改
	@Override
	public void updateDocument(Document doc) {
		 Connection conn = JDBCUtils.getConnection();
	        
			String sql = "update document_inf set title=? ,  remark=? , filebytes=?,filename=?,filetype=? where id=? ";
			try {
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setString(1, doc.getTitle());
				pstm.setString(2, doc.getRemark());
				pstm.setObject(3, doc.getFilebytes());
				pstm.setString(4, doc.getFilename());
				pstm.setString(5, doc.getFiletype());
				pstm.setInt(6, doc.getId());
//				String date;
//				pstm.setString(4, user.getCreatedate());
               
				// 执行查询
				 pstm.executeUpdate();
				 System.out.println(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.closeConn(conn);
			}
	}

	@Override
	public Document findDocument(Integer id) {
		 Connection conn = JDBCUtils.getConnection();
		 
			String sql = "select * from document_inf where id=? ";
			try {
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setInt(1, id);
				
				// 执行查询
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {
					Document doc=new Document();
					doc.setId(rs.getInt("id"));
					doc.setTitle(rs.getString("title"));
					doc.setFilename(rs.getString("filename"));
					doc.setFilebytes(rs.getBytes("filebytes"));
					doc.setFiletype(rs.getString("filetype"));
					doc.setCreatedate(rs.getDate("create_date"));
					doc.setRemark(rs.getString("remark"));
					return doc;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.closeConn(conn);
			}
			return null;
	}

	

}
