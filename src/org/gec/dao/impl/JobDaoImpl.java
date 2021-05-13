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
import org.gec.bean.Job;
import org.gec.dao.JobDao;
import org.gec.util.JDBCUtils;
import org.gec.util.PageModel;

public class JobDaoImpl implements JobDao {

    @Override
    public List<Job> findJob(Job job, PageModel model) {
        List<Job> jobs = new ArrayList<>();
        Connection conn = JDBCUtils.getConnection();
        StringBuffer sql = new StringBuffer("select * from job_inf where 1=1 ");
        try {
            Statement pstm = conn.createStatement();

            //判断
            if (StringUtils.isNotBlank(job.getName())) {
                sql.append(" and name like '%").append(job.getName()).append("%'");
                System.out.println("sql:" + sql);
            }
            if (StringUtils.isNotBlank(job.getRemark())) {
                sql.append(" and remark like '%").append(job.getRemark()).append("%'");
                System.out.println("sql:" + sql);
            }

            //添加分页
            sql.append(" limit ").append(model.getStartRow()).append(",").append(PageModel.pageSize);

            // 执行查询
            ResultSet rs = pstm.executeQuery(sql.toString());
            System.out.println("sql:" + sql);
            while (rs.next()) {
                //shift+alt+A 可以批量修改 恢复也是按这个快捷方式
                Job j = new Job();
                j.setId(rs.getInt("id"));
                j.setName(rs.getString("name"));
                j.setRemark(rs.getString("remark"));
                jobs.add(j);
            }

            return jobs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConn(conn);
        }

        return null;
    }

    //添加
    @Override
    public boolean addJob(Job job) {
        Connection conn = JDBCUtils.getConnection();

        String sql = "insert into job_inf(name,remark) values(?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, job.getName());
            pstm.setString(2, job.getRemark());
//			String date;
//			pstm.setString(4, user.getCreatedate());

            // 执行查询
            int rs = pstm.executeUpdate();
            if (rs > 0) {
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
    public void updateJob(Job job) {
        Connection conn = JDBCUtils.getConnection();

        String sql = "update job_inf set name=? , remark=? where id=? ";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, job.getName());
            pstm.setString(2, job.getRemark());
            pstm.setInt(3, job.getId());
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

    //删除
    @Override
    public void deleteJob(String[] id) {
        Connection conn = JDBCUtils.getConnection();
//		System.out.println(sql);
        try {
            for (int i = 0; i <= id.length - 1; i++) {
                String sql = "delete from job_inf where id='" + id[i] + "'";

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

    //分页
    @Override
    public int getTotalCountByJob(Job job) {
        List<Job> jobs = new ArrayList<>();
        Connection conn = JDBCUtils.getConnection();

        //1=1 条件永远成立，为了拼接条件
        StringBuffer sql = new StringBuffer("select count(*) from job_inf where 1=1  ");
        try {
            Statement pstm = conn.createStatement();

            //判断
            if (StringUtils.isNotBlank(job.getName())) {
                sql.append(" and name like '%").append(job.getName()).append("%'");

            }
            if (StringUtils.isNotBlank(job.getRemark())) {
                sql.append(" and remark like '%").append(job.getRemark()).append("%'");
            }

            ResultSet rs = pstm.executeQuery(sql.toString());
            //
            while (rs.next()) {
                //columnIndex the first column is 1, the second is 2
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConn(conn);
        }

        return 0;
    }

    @Override
    public Job findJob(Integer id) {
        Connection conn = JDBCUtils.getConnection();

        String sql = "select * from job_inf where id=? ";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            // 执行查询
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Job job = new Job();
                job.setId(rs.getInt("id"));
                job.setName(rs.getString("name"));
                job.setRemark(rs.getString("remark"));
                return job;
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

