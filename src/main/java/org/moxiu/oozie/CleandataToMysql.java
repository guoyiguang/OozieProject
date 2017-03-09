package org.moxiu.oozie;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.moxiu.bean.CleanInfo;
import org.moxiu.tool.JdbcUtils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MX-092 on 2017/2/15.
 */
public class CleandataToMysql {
    private FileSystem fs;
    private Configuration conf;
    private FSDataInputStream fin;
    private FileOutputStream fos;
    private InputStream in;
    private Connection conn; public void saveToMysql(CleanInfo info,String workpath,String picpath){
        String oozie_flow_sql = "insert into cleandata_flow (date,job_name,job_chineseName,work_path,useName,groupsName,start,end," +
                "time_intervaal,point_date,source_data,target_data,Hive_table,lastmodified,description,flow_pic) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Configuration conf = new Configuration();
        conf.set("fs.hdfs.impl","org.apache.hadoop.hdfs.DistributedFileSystem");
        GetLastModified last = new GetLastModified();
        boolean flag = last.readHdfs(workpath);


        try {
            fs = FileSystem.get(conf);
            //获取读取流
            fin = fs.open(new Path(picpath));
            conn = JdbcUtils.getConnection2();
            ps = conn.prepareStatement(oozie_flow_sql);
            GetMessage gm = new GetMessage();
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
            String today = simpleDateFormat.format(date);

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
            ps.setString(11,info.getSource_data());
            ps.setString(12,info.getTarget_data());
            ps.setString(13,info.getHive_table());
            ps.setBoolean(14,flag);
            ps.setString(15,info.getDescription());
            ps.setBinaryStream(16,fin,fin.available());
            int count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JdbcUtils.closeConnection(conn);
        JdbcUtils.closePreparedStatement(ps);
    }
    private PreparedStatement ps;
    private ResultSet rs;

    }

