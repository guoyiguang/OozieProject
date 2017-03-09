package org.moxiu.tool;

import java.sql.*;

/**
 * Created by gyg on 2017/2/10.
 * 这时一个工具类，用于提供获取mysql驱动，连接mysql,并释放连接等方法。
 */
public class JdbcUtils {
    private static String url ="jdbc:mysql://10.1.0.166:3306/oozie"; //定义mysql的地址
    private static String username="reader_all"; //定义连接mysql的用户名
    private static String password="OaJ4h%bJmo"; //定义连接mysql的密码

    private static String url2="jdbc:mysql://10.1.0.20:3306/columbus_monitor";
    private static String username2="admin_mx";
    private static String password2="F]D3+XIi50C}qpdD";
    private JdbcUtils(){}
    //注册驱动
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            throw new ExceptionInInitializerError(e);
        }
    }
    /**
     * 建立连接=》连接10.1.0.166
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection2(){
        Connection conn=null;
        try{
            conn = DriverManager.getConnection(url2,username2,password2);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放 ResultSet 资源
     */
    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 释放Statement资源
     */
    public static void closeStatement(Statement st){
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 释放Connection连接
     */
    public static void  closeConnection(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 释放PreparedStatement连接
     */
    public static void closePreparedStatement(PreparedStatement ps){
        try {
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
