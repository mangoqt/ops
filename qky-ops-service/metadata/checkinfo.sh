#!/bin/bash
  count=`find /data/xxtMonitor/logs -type f -mmin -10 | grep info.log | wc -l`
  if [ $count -lt 1 ];then
    sh /data/xxtMonitor/sendMail.sh xxtmontior_is_down
  fi
