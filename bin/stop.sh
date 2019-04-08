#!/bin/sh
APP_NAME=mycpug

tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${cpugtpid} ]; then
    echo 'Stop Process...'
    kill -15 $cpugtpid
fi
sleep 5
tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${cpugtpid} ]; then
    echo 'Kill Process!'
    kill -9 $cpugtpid
else
    echo 'Stop Success!'
fi