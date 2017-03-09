## 项目描述
>本项目用于实现对于Oozie流程的整理,在Oozie流程部署的文件夹中，使用prop 方法读去每个文件夹中的job.properties 和 chinese.properties.最后将各个流程的配置信息存储到mysql中，供前端展示。

**job.properties**
>job.properties配置文件为Oozie流程自带的配置参数。里面配置了job_name,start_time,end_time.等参数。

**chinese.properties**
>chinese.properties为我自己配置的文件，里面存储了项目的中文名称，中文描述，本项目依赖的流程和最后import的mysql数据库名称。
