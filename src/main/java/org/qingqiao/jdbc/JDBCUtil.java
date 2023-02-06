package org.qingqiao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/*
    Math
    全都是静态方法
 */
public class JDBCUtil {
    static String DRIVERNAME = "com.mysql.cj.jdbc.Driver";
    static String USERNAME = "root";
    static String PASSWORD = "root";
    static String URL ="jdbc:mysql://localhost:3306/open";
    static Connection conn = null;

    // 获取连接
    public static Connection getConn() {
        try {
            Class.forName(DRIVERNAME);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 关流
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 关流
    public static void close(Connection conn, PreparedStatement ps) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}