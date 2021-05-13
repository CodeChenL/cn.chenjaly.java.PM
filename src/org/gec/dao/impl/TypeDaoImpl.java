package org.gec.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.gec.bean.Type;
import org.gec.dao.TypeDao;
import org.gec.util.JDBCUtils;
import org.gec.util.PageModel;

public class TypeDaoImpl implements TypeDao {

    @Override
    public List<Type> findTypes() {
        List<Type> types = new ArrayList<>();
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from type_inf";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Type type = new Type();
                type.setId(rs.getInt("id"));
                type.setName(rs.getString("name"));

                types.add(type);
            }

            return types;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConn(conn);
        }
        return null;
    }

    @Override
    public Type findTypeById(int id) {
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from type_inf where id=?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            Type type = new Type();
            while (rs.next()) {
                type = new Type();
                type.setId(rs.getInt("id"));
                type.setName(rs.getString("name"));

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
    public void saveType(Type type) {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into type_inf (name)values(?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, type.getName());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConn(conn);
        }
    }

    @Override
    public int getTotalCountByType(String name) {
        Connection conn = JDBCUtils.getConnection();

        StringBuffer sql = new StringBuffer("select count(*) from type_inf where 1=1 ");
        if (StringUtils.isNotBlank(name)) {
            sql.append(" and name like '%").append(name).append("%'");
        }
        try {
            PreparedStatement pstm = conn.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConn(conn);
        }
        return 0;
    }

    @Override
    public List<Type> findTypesByPage(String name, PageModel model) {
        List<Type> types = new ArrayList<>();
        Connection conn = JDBCUtils.getConnection();

        StringBuffer sql = new StringBuffer("select * from type_inf where 1=1 ");
        if (StringUtils.isNotBlank(name)) {
            sql.append(" and name like '%").append(name).append("%'");
        }

        sql.append(" limit ").append(model.getStartRow()).append(",").append(PageModel.pageSize);
        try {
            PreparedStatement pstm = conn.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Type type = new Type();
                type.setId(rs.getInt("id"));
                type.setName(rs.getString("name"));
                types.add(type);
            }

            return types;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConn(conn);
        }
        return null;
    }

    @Override
    public void updateType(Type type) {
        Connection conn = JDBCUtils.getConnection();
        String sql = "update type_inf set name=? where id=?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, type.getName());
            pstm.setInt(2, type.getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConn(conn);
        }
    }

    @Override
    public void deleteType(String[] ids) {
        Connection conn = JDBCUtils.getConnection();
        String sql = "delete from type_inf where id=?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            for (int i = 0; i < ids.length; i++) {
                int id = Integer.parseInt(ids[i]);
                pstm.setInt(1, id);
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConn(conn);
        }
    }


}
