package com.code.basic.Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUtil {

    private static final String username = "epg";
    private static final String password = "epg";
    private static final String jdbcURL = "jdbc:mysql://10.201.52.114:3306/epg?useUnicode=true&characterEncoding=utf-8";
    private static final String jdbcDriver = "com.mysql.jdbc.Driver";

    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public Connection getConnection() {
        try {
            // 注册驱动
            Class.forName(jdbcDriver);
            // 获取连接
            connection = DriverManager.getConnection(jdbcURL, username, password);
        } catch (Exception e) {
            throw new RuntimeException("获取连接失败!", e);
        }
        return connection;
    }

    // 执行查询
    public List<Map<String, Object>> query(String sql, List<String> params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        rs = pstmt.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = rs.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }
        return list;
    }

    // 释放资源
    public void releaseReSource() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
