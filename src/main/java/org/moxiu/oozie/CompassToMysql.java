package org.moxiu.oozie;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.moxiu.bean.CompassInfo;
import org.moxiu.tool.JdbcUtils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MX-092 on 2017/2/16.
 */
public class CompassToMysql {

    private FileSystem fs;
    private Configuration conf;
    private FSDataInputStream fin;
    private FileOutputStream fos;
    private InputStream in;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    String oozie_flow_sql = "insert into compass_flow (date,job_name,job_chineseName,work_path,useName,groupsName,start,end,time_intervaal,point_date,lastmodified,description,flow_pic) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    String oozie_depend_sql="insert into compass_depend(date,job_name,check_date,dependjob) values(?,?,?,?)";
    String oozie_mysql_sql="insert into compass_mysql(date,job_name,mysql_table,description) values(?,?,?,?)";

    Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
    String today = simpleDateFormat.format(date);

    public void savaToFlow(CompassInfo info,String workpath,String picpath) {

        System.setProperty("hadoop.home.dir", "D:\\hadoop-2.6.0");
        System.setProperty("HADOOP_USER_NAME", "guoyiguang");
        conf = new Configuration();
        conf.set("fs.hdfs.impl","org.apache.hadoop.hdfs.DistributedFileSystem");
        GetLastModified last = new GetLastModified();
        boolean flag = last.readHdfs(workpath);
        try {
            fs = FileSystem.get(conf);
            //获取读取流
            fin = fs.open(new Path(picpath));
            conn = JdbcUtils.getConnection2();
            ps = conn.prepareStatement(oozie_flow_sql);

            CompassGetField getField = new CompassGetField();


            //通过调用对象获取字段信息，为sql赋值。
            ps.setString(1,today);
            ps.setString(2,info.getJob_name());
            ps.setString(3,info.getJob_chineseName());
            ps.setString(4,workpath);
            ps.setString(5,info.getUseName());
            ps.setString(6,info.getGroupsName());
            ps.setString(7,info.getStart());
            ps.setString(8,info.getEnd());
            ps.setString(9,info.getTime_interval());
            ps.setString(10,info.getPoint_date());
            ps.setBoolean(11,flag);
            ps.setString(12,info.getDescription());
            ps.setBinaryStream(13,fin,fin.available());
            int count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JdbcUtils.closeConnection(conn);
        JdbcUtils.closePreparedStatement(ps);
    }
    public void saveToDepend(CompassInfo info){
        try {
            fs = FileSystem.get(conf);
            //获取读取流
            conn = JdbcUtils.getConnection2();
            ps = conn.prepareStatement(oozie_depend_sql);

            //通过调用对象获取字段信息，为sql赋值。
            String check_date=info.getCheck_date();
            String depend_job=info.getDepend_job();
            if(check_date.length()>0){
                String [] check_dates=check_date.split("\t");
                String [] depend_jobs=depend_job.split("\t");
                for( int i=0;i<check_dates.length;i++){
                    ps.setString(1,today);
                    ps.setString(2,info.getJob_name());
                    ps.setString(3,check_dates[i]);
                    ps.setString(4,depend_jobs[i]);
                    ps.executeUpdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        JdbcUtils.closeConnection(conn);
        JdbcUtils.closePreparedStatement(ps);
    }
    /**
     * 将数据存储到 oozie_mysql中
     */
     public void saveToMysql(CompassInfo info)
    {
        try {
            fs = FileSystem.get(conf);
            //获取读取流
            conn = JdbcUtils.getConnection2();
            ps = conn.prepareStatement(oozie_mysql_sql);

            String mysql_table=info.getMysql_table();
            String mysql_description=info.getMysql_description();

            if(mysql_table.length()>0){
                String [] mysql_tables=mysql_table.split("\t");
                String [] mysql_descriptions=mysql_description.split("\t");
                for( int i=0;i<mysql_tables.length;i++){
                    ps.setString(1,today);
                    ps.setString(2,info.getJob_name());
                    ps.setString(3,mysql_tables[i]);
                    ps.setString(4,mysql_descriptions[i]);
                    ps.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JdbcUtils.closeConnection(conn);
        JdbcUtils.closePreparedStatement(ps);
    }
}
