#!/bin/bash
mv /data/xxtMonitor/upfile/check.txt /data/xxtMonitor/upfile/check.txt`date +%Y%m%d`
touch /data/xxtMonitor/upfile/check.txt
for ip in 69 70 71 72 74 75 76 77 78 147 148 149 150 151 152 153 154 155 156 157 158 159 160 161 162 163 164 165 166 167 168 169 170 179 180 181 182 183 184 185 186 187 188 189 190 191 192 193 194 195 196 197
do
sip=192.168.210.$ip
cpu=`/usr/bin/ssh 192.168.210.$ip "/usr/bin/top -b -n1" | grep ' [SRZN]  ' |sort -nr  +8 -9 | head -1 | awk '{print $9}'`
load=`/usr/bin/ssh 192.168.210.$ip "/usr/bin/top -b -n1" | grep -i "load average" | awk '{print substr($0,index($0,"average")+8,5)}'`
disk=`/usr/bin/ssh 192.168.210.$ip /bin/df -P -h | grep '[0-99].%\|100%' | awk '{if(NR==1) print $(NF-1)}'`
swap=`/usr/bin/ssh 192.168.210.$ip "/usr/bin/top -b -n1" | grep -i Swap: | awk '{print $4}'`
weblogic_virt=`/usr/bin/ssh 192.168.210.$ip /bin/ps aux | grep weblogic | grep java | awk  '{print $5}' | sort -nr | head -1 `
jboss_virt=`/usr/bin/ssh 192.168.210.$ip /bin/ps aux | grep jboss | grep java | awk  '{print $5}' | sort -nr | head -1 `
tomcat_virt=`/usr/bin/ssh 192.168.210.$ip /bin/ps aux | grep tomcat | grep java | awk  '{print $5}' | sort -nr | head -1 `
threads=`/usr/bin/ssh 192.168.210.$ip /usr/bin/top -b n1 | head | grep Tasks | awk '{print $2}'`
memory=`/usr/bin/ssh 192.168.210.$ip /usr/bin/free -m |grep Mem|cut -d ',' -f 1|awk '{print $2}'`
used=`/usr/bin/ssh 192.168.210.$ip /usr/bin/free -m |grep buffers/cache|cut -d ',' -f 1|awk '{print $3}'`
using=`echo "scale=4;{($used / $memory) * 100}" | bc`
echo $sip,$cpu,$load,$disk,$swap,$weblogic_virt,$jboss_virt,$threads,$memory MB,$using%  >> /data/xxtMonitor/upfile/check.txt
done