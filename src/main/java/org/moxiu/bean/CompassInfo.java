package org.moxiu.bean;

/**
 * Created by MX-092 on 2017/2/16.
 */
public class CompassInfo {
    private String job_name; //流程名称 ID
    private String job_chineseName; //流程中文名称
    private String useName; //提交用户名
    private String groupsName;//提交用户组
    private String start;//作业开始时间
    private String end; //作业结束时间
    private String time_interval;//作业运行时间间隔
    private String point_date;//状态日期文件
    private String check_date;//监控日期文件
    private String depend_job;//依赖作业
    private String mysql_table;//一个流程使用到的Mysql表的表名。
    private String mysql_description;
    private String description;//项目描述

    public CompassInfo(){}
    public String getJob_name() {
        return job_name;
    }

    public String getJob_chineseName() {
        return job_chineseName;
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

    public String getCheck_date() {
        return check_date;
    }

    public String getMysql_table() {
        return mysql_table;
    }

    public String getMysql_description() {
        return mysql_description;
    }

    public String getDescription() {
        return description;
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

    public void setCheck_date(String check_date) {
        this.check_date = check_date;
    }

    public void setDepend_job(String depend_job) {
        this.depend_job = depend_job;
    }

    public void setMysql_table(String mysql_table) {
        this.mysql_table = mysql_table;
    }

    public void setMysql_description(String mysql_description) {
        this.mysql_description = mysql_description;
    }

    public String getDepend_job() {

        return depend_job;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "job_name="+job_name+"\njob_chineseName="+job_chineseName+"\nuseName="+useName+"\ngroupsName="+groupsName
                +"\nstart="+start+"\nend"+end+"\ntime_interval="+time_interval+"\npoint_date="+point_date+"\ncheckdate="
                +check_date+"\ndepend_job"+depend_job+"\nmysql_table="+mysql_table+"\nmysql_description="+mysql_description+"\ndescription="+description;
    }
}
