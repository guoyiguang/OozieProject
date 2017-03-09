package org.moxiu.oozie;

import org.moxiu.bean.CleanInfo;
import org.moxiu.bean.CompassInfo;

import java.util.Iterator;
import java.util.Set;


/**
 *本类用于调用GetPath方法，用于循环遍历Set集合获取所有的路径。然后将参数传给savepic类存储数据到mysql
 */
public class Application {

    public static void main(String args[])
    {
        GetPath gp = new GetPath();
        Set set = gp.getPath();
        Iterator<String> it = set.iterator();

         while (it.hasNext()) {
            String str = it.next();
             String workpath = str.replace("coordinator.xml","");
             String cleandata1 = str.replace("coordinator.xml", "job.properties");
             String cleandata2 = str.replace("coordinator.xml","chinese.properties");
             String ooziepic = str.replace("coordinator.xml", "flow_pic.png");
             if(str.contains("cleandata")){

                 CleandataGetField ct = new CleandataGetField();
                 CleanInfo info = ct.readproperties(cleandata1,cleandata2);
                 CleandataToMysql cleandataToMysql = new CleandataToMysql();
                 cleandataToMysql.saveToMysql(info,workpath,ooziepic);
                 System.out.println(info);
             }else{

                 CompassGetField cf = new CompassGetField();
                 CompassInfo info = cf.getField(cleandata1,cleandata2);
                 CompassToMysql compassToMysql = new CompassToMysql();
                 compassToMysql.savaToFlow(info,workpath,ooziepic);
                 compassToMysql.saveToDepend(info);
                 compassToMysql.saveToMysql(info);
                 System.out.print(info);
             }
        }

    }
}
