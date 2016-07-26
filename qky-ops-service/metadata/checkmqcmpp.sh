#!/bin/bash

tail -15000 /data/mqcmpp/nohup.out > /data/mqcmpp/log/nohup.txt

d1=`date -d "-35 minute" +%H:%M`
n1=`sed -n '/^'${d1}'/=' /data/mqcmpp/log/nohup.txt | head -1`
#echo $d1 $n1
if [ "$n1" != "" ];then
   n= expr 15000 - $n1
   echo $n
   tail -` expr 15000 - $n1` /data/mqcmpp/log/nohup.txt | grep 下行短信响应
   if [ $? -ne 0 ];then
      sh /data/xxtMonitor/sendMail.sh 74mqcmpp
      echo "send mail"
   fi
else
   sh /data/xxtMonitor/sendMail.sh 74mqcmpp
   echo "send mail"
fi