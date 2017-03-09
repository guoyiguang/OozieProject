
package org.moxiu.oozie;

        import org.moxiu.tool.JdbcUtils;
        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.HashSet;
        import java.util.Iterator;
        import java.util.Set;

/**
 * Created by gyg on 2017/2/10.
 * 本类用于查询Oozie安装自带mysql数据库表名为COORD_ACTIONS获取各个Coordinator的路径
 */
public class GetPath {
    static Connection conn;
    static Statement st;
    static ResultSet rs;
    static Set<String> set;
    private static String sql = "select app_name,app_path from COORD_JOBS;";

    /**
     * 因为路径存在重复的因此定义为Set集合
     * 定义一个方法返回一个set集合用来存储线上Oozie job作业所存储的路径。
     * @return
     */
    public static Set<String> getPath(){
        set = new HashSet<String>();
        conn  = JdbcUtils.getConnection();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                String oldpath = rs.getString(2);
//                String newpath=oldpath.replace("coordinator.xml","");
                set.add(oldpath);
//              System.out.println(newpath);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

}
