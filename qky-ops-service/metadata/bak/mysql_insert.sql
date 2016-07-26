Insert into SUP_MON_SCRIPT
   (ERROR_CODE, TYPE, MONITOR_CONTENT, SCRIPT, COMPAREPARAM, WARN_MSG, PHONES, ENABLE)
 Values
   ('XXT_CMPP_137_101','cmpp', '1', 'www.baidu.com', '=', 'cmpp错误', '13512719456', 1);
   
--ssh 192.168.205.57 uptime | awk -F"," '{print $(NF-1)}' | awk -F
INSERT INTO SUP_MON_SCRIPT
   (ERROR_CODE, TYPE, MONITOR_CONTENT, SCRIPT, COMPAREPARAM, WARN_MSG, PHONES, ENABLE)
 VALUES
   ('XXT_DB_57_101','db', '1', 'ssh 192.168.205.57 uptime | awk -F"," \'{print $(NF-1)}\' | awk -F"." \'{print $1}\'', '>', 'DB1负载>50', '13512719456', 1);
INSERT INTO SUP_MON_SCRIPT
   (ERROR_CODE, TYPE, MONITOR_CONTENT, SCRIPT, COMPAREPARAM, WARN_MSG, PHONES, ENABLE)
 VALUES
   ('XXT_DB_58_101','db', '1', 'ssh 192.168.205.58 uptime | awk -F"," \'{print $(NF-1)}\' | awk -F"." \'{print $1}\'', '>', 'DB2负载>50', '13512719456', 1);
   
--磁盘空间
INSERT INTO SUP_MON_SCRIPT(ERROR_CODE,TYPE, MONITOR_CONTENT, SCRIPT, COMPAREPARAM, WARN_INTERVAL, WARN_MSG, PHONES, ENABLE)
 VALUES ('XXT_DISK_147_101','disk', '85', 'ssh 192.168.210.147 df -h | awk -F" " ''{ if(NR>1) print $5}'' | grep -E ''%'' | sort | tail -1 | sed s/%//', '<', 3000,'147disk', '13512719456', 1);

Insert into SUP_MON_MQ
   (ACCOUNT, PASSWORD, HOST, PORT, MONITOR_CONTENT, 
    QUEUE_NAMES, COMPAREPARAM, WARN_MSG, PHONES, ENABLE)
 Values
   ('guest', 'IIx82ndd', '192.168.210.179', 5672, '1000', 
    'sms-cmpp-061', '<=', '积压>=1000', '13512719456', 1);
    
    
    Insert into SUP_MON_SQL
   (ERROR_CODE,TYPE, MONITOR_CONTENT, sql1, COMPAREPARAM, WARN_INTERVAL, 
    WARN_MSG, PHONES, ENABLE)
 Values
   ('XXT_MQ_TEST_101','test', '1', 'select 1 from dual', '>', 100, 
    'sql 测试', '135127194569', 1);
    
    
insert into SUP_MON_REDIS
         (ERROR_CODE,type,monitor_content,monitor_key,host,port,conn_time_out,compareparam,warn_interval,warn_msg,remark,phones,ENABLE)
   values('XXT_REDIS_121_101', 'redis', '1', 'sup_monitor', '192.168.1.121',6379,5000, '=',100,'121redis异常', '','13512719456',1)

