#!/bin/bash

JAVACMD="/usr/local/jdk1.6.0_45/bin/java"

cd /data/xxtMonitor
if [ -f xxtMonitor.pid ]
then
  echo "kill old xxtMonitor process.."
  kill -9 `cat xxtMonitor.pid`
  echo "process killed"
fi
sleep 5
echo "start xxtMonitor process"
echo "restart time:" `date` >> logs/restart.log 

cp=.
for jar in `ls ./lib/*.jar`
do
  cp="$cp:""$jar"
done

nohup $JAVACMD  -classpath $cp com.qt.xxtmonitor.XxtMonitor -Dfile.encoding=UTF-8 2>&1 >> logs/$0.log &
echo $! > xxtMonitor.pid 
