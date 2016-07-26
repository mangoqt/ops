#!/bin/bash

content=$1

JAVACMD="/usr/local/jdk1.6.43/bin/java"

cd /data/xxtMonitor

cp=.
for jar in `ls ./lib/*.jar`
do
  cp="$cp:""$jar"
done

nohup $JAVACMD -Dfile.encoding=UTF-8  -classpath $cp com.qt.core.mail.SendMailMain $content  2>&1 >> logs/sendMail.log &
