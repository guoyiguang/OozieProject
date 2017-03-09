package org.moxiu.oozie;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;
import java.util.Properties;

/**
 * Created by gyg on 2017/2/10.
 * @return Properties
 * 通过传入workflow的路径。获取一个读取流FSDataInputStream。然后创建Properties对象，并关联流。
 */
public class GetMessage {
    FileSystem fs;
    FSDataInputStream in;
    Properties pro;
//    OozieInfo info=new OozieInfo();
    public Properties getProperties(String path) {
        Configuration conf = new Configuration();
        conf.set("fs.hdfs.impl","org.apache.hadoop.hdfs.DistributedFileSystem");
        try {
            fs = FileSystem.get(conf);
            //获取读取流
            in = fs.open(new Path(path));


        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用properties类的方法来获取配置文件的信息
        pro = new Properties();
        try {
            pro.load(in); //关联读取流
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pro; // 调用方法返回定义好的properties 对象。

    }

}
