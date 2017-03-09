package org.moxiu.oozie;

import org.moxiu.bean.CompassInfo;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by MX-092 on 2017/2/16.
 */
public class CompassGetField {

    public CompassInfo getField(String path1,String path2){

        GetMessage gm = new GetMessage();
        Properties prop1 = gm.getProperties(path1);
        Properties prop2 = gm.getProperties(path2);
        Object[] obj1 = prop1.keySet().toArray();
        Object[] obj2 = prop2.keySet().toArray();

        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        StringBuffer sb3 = new StringBuffer();
        StringBuffer sb4 = new StringBuffer();

        CompassInfo info = new CompassInfo();
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

        }
        for(int i=0;i<obj2.length;i++){
            if(obj2[i].equals("job_chineseName")){
                try {
                    info.setJob_chineseName(new String(prop2.get(obj2[i]).toString().getBytes("ISO-8859-1"),"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if(obj2[i].equals("time_interval")){
                info.setTime_interval(prop2.get(obj2[i]).toString());
            }
            if(obj2[i].equals("description")){
                try {
                    info.setDescription(new String(prop2.get(obj2[i]).toString().getBytes("ISO-8859-1"),"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if(obj2[i].toString().contains("depend_job")){
                sb1.append(prop2.get(obj2[i]).toString()+"\t");
            }
            if (obj2[i].toString().contains("check") && prop2.get(obj2[i]).toString().length() > 5) {
                sb2.append(prop2.get(obj2[i]).toString() + "\t"); // 一般checkdate会有多个值，因此使用stringbuffer存储多个字符串。
            }
            if(obj2[i].toString().contains("mysql_table")){
                sb3.append(prop2.get(obj2[i]).toString()+"\t");
            }
            if(obj2[i].toString().contains("mysql_description")){
                try {
                    sb4.append(new String(prop2.get(obj2[i]).toString().getBytes("ISO-8859-1"),"utf-8") + "\t");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        info.setCheck_date(sb2.toString()); // 为checkdate赋值
        info.setDepend_job(sb1.toString()); // 为dependname赋值
        info.setMysql_table(sb3.toString()); //为 mysql_table 赋值
        info.setMysql_description(sb4.toString()); //为 mysql_description赋值
        return info;
    }

}
