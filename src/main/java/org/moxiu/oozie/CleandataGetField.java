package org.moxiu.oozie;

import org.moxiu.bean.CleanInfo;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by gyg on 2017/2/15.
 * 用于读取清洗程序的配置文件然后存储到mysql
 */
public class CleandataGetField {

    public CleanInfo readproperties(String path1,String path2) {
        GetMessage gm = new GetMessage();

        Properties prop1 = gm.getProperties(path1);
        Properties prop2 = gm.getProperties(path2);
        CleanInfo info = new CleanInfo();
//      prop.list(System.out);  //输出所有得键值对
        Object[] obj1 = prop1.keySet().toArray();
        Object[] obj2 = prop2.keySet().toArray();
        Date date = new Date();

        for (int i = 0; i < obj1.length; i++) {
            if (obj1[i].equals("job_name")) {
                info.setJob_name(prop1.get(obj1[i]).toString());
//                String info = prop.get(obj[i]).toString();
            }
            if (obj1[i].equals("userName")) {
                info.setUseName(prop1.get(obj1[i]).toString());
            }
            if (obj1[i].equals("groupsName")) {
                info.setGroupsName(prop1.get(obj1[i]).toString());
            }
            if (obj1[i].equals("start")) {
                info.setStart(prop1.get(obj1[i]).toString());
            }
            if (obj1[i].equals("end")) {
                info.setEnd(prop1.get(obj1[i]).toString());
            }
            if (obj1[i].toString().contains("point") && prop1.get(obj1[i]).toString().length() > 5) {
                info.setPoint_date(prop1.get(obj1[i]).toString());
            }
            if (obj1[i].toString().contains("source")|obj1[i].toString().contains("result")) {
                info.setSource_data(prop1.get(obj1[i]).toString());
            }
            if (obj1[i].toString().contains("dist")) {
                info.setTarget_data(prop1.get(obj1[i]).toString());
            }
            if (obj1[i].toString().contains("warehouse_tb")) {
                info.setHive_table(prop1.get(obj1[i]).toString());
            }
        }

        for (int i = 0; i < obj2.length; i++) {
            if (obj2[i].equals("job_chineseName")) {
                try {
                    info.setJob_chineseName(new String(prop2.get(obj2[i]).toString().getBytes("ISO-8859-1"), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (obj2[i].equals("time_interval")) {
                info.setTime_interval(prop2.get(obj2[i]).toString());
            }
            if (obj2[i].equals("description")) {
                try {
                    info.setDescription(new String(prop2.get(obj2[i]).toString().getBytes("ISO-8859-1"), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        return info;
    }
}
