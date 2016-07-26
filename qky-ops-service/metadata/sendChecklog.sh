#!/bin/bash

JAVACMD="/usr/local/jdk1.6.43/bin/java"

basedir=/data/xxtMonitor

cd $basedir 

cp=.
for jar in `ls ./lib/*.jar`
do
  cp="$cp:""$jar"
done

nohup $JAVACMD  -classpath $cp com.qt.inspection.CheckServiceMain -Dfile.encoding=UTF-8 2>&1 >> logs/sendChecklog.log &
