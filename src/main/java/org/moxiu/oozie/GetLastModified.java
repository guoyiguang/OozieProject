package org.moxiu.oozie;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import java.util.Date;
import java.util.Properties;

/**
 * Created by gyg on 2017/2/20.
 * 获取hdfs中给定一个目录下的所有文件的最后修改时间
 */

public class GetLastModified {
    FileSystem fs;
    FSDataInputStream in;
    Properties pro;
    public boolean readHdfs(String path){
        Configuration conf = new Configuration();
        conf.set("fs.hdfs.impl","org.apache.hadoop.hdfs.DistributedFileSystem");
        Date date;
        try {
            fs = FileSystem.get(conf);
            FileStatus filestatus = fs.getFileStatus(new Path(path));
            long x =  filestatus.getModificationTime();
            FileStatus[] status = fs.listStatus(new Path(path));

            Path[] listpath = FileUtil.stat2Paths(status);
            date = new Date();
            long time1=date.getTime();
            for(Path list:listpath){
                System.out.println(list);
                filestatus = fs.getFileLinkStatus(new Path(list.toString()));
                long time2 = filestatus.getModificationTime();
                if(time1-time2 < 86400000){
                    return true;
                }
            }
            System.out.println(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
