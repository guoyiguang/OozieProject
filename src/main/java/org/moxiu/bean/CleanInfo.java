package org.moxiu.bean;

/**
 * Created by MX-092 on 2017/2/15.
 */
public class CleanInfo {
    private String date;
    private String job_name; //流程名称 ID
    private String job_chineseName; //流程中文名称
    private String useName; //提交用户名
    private String groupsName;//提交用户组
    private String start;//作业开始时间
    private String end; //作业结束时间
    private String time_interval;//作业运行时间间隔
    private String point_date;//状态日期文件
    private String description;//项目描述
    private String source_data; // 原始日志路径
    private String target_data; //清洗后日志存储路径
    private String Hive_table;//一个流程使用到的Hive表的表名。
    public void setDescription(String description) {
        this.description = description;
    }




    public CleanInfo(String job_name, String job_chineseName, String useName, String groupsName, String start, String end, String time_interval, String point_date, String description, String source_data, String target_data, String mysql_table, String mysql_description) {
        this.job_name = job_name;
        this.job_chineseName = job_chineseName;
        this.useName = useName;
        this.groupsName = groupsName;
        this.start = start;
        this.end = end;
        this.time_interval = time_interval;
        this.point_date = point_date;
        this.description = description;
        this.source_data = source_data;
        this.target_data = target_data;
        this.Hive_table = mysql_table;
    }
    public CleanInfo()
    {

    }
    public String getJob_name() {
        return job_name;
    }

    public String getJob_chineseName() {
        return job_chineseName;
    }
    public String getHive_table() {

        return Hive_table;
    }
    public String getUseName() {
        return useName;
    }

    public String getGroupsName() {
        return groupsName;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getTime_interval() {
        return time_interval;
    }

    public String getPoint_date() {
        return point_date;
    }

    public String getDescription() {
        return description;
    }

    public String getSource_data() {
        return source_data;
    }

    public String getTarget_data() {
        return target_data;
    }


    public String Hive_table() {
        return Hive_table();
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public void setJob_chineseName(String job_chineseName) {
        this.job_chineseName = job_chineseName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public void setGroupsName(String groupsName) {
        this.groupsName = groupsName;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setTime_interval(String time_interval) {
        this.time_interval = time_interval;
    }

    public void setPoint_date(String point_date) {
        this.point_date = point_date;
    }

    public void setSource_data(String source_data) {
        this.source_data = source_data;
    }

    public void setTarget_data(String target_data) {
        this.target_data = target_data;
    }

    public void setHive_table(String Hive_table) {
        this.Hive_table = Hive_table;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {

        return date;
    }
    public String toString() {
        return "job_name="+job_name+"\njob_chineseName="+job_chineseName+"\nuseName="+useName+"\ngroupsName="+groupsName
                +"\nstart="+start+"\nend"+end+"\ntime_interval="+time_interval+"\npoint_date="+point_date+"\ncheckdate="
                +source_data+"\ndependjob="+target_data+"Hive_table="+Hive_table+"\ndescription="+description;
    }

}
