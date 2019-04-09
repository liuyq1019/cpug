#!/bin/bash
cd `dirname $0`
CUR_SHELL_DIR=`pwd`
CUR_SHELL_NAME=`basename ${BASH_SOURCE}`
JAR_NAME="viss-cpug.jar"
JAR_PATH=$CUR_SHELL_DIR/$JAR_NAME
#JAVA_MEM_OPTS=" -server -Xms1024m -Xmx1024m -XX:PermSize=128m"
JAVA_MEM_OPTS=""
#SPRING_PROFILES_ACTIV="-Dspring.profiles.active=eureka2"
SPRING_PROFILES_ACTIV=""
LOG_DIR=$CUR_SHELL_DIR/logs
LOG_PATH=$LOG_DIR/${JAR_NAME}.log
echo_help()
{
    echo -e "syntax: sh $CUR_SHELL_NAME start|stop"
}
if [ -z $1 ];then
    echo_help
    exit 1
fi
#if [ ! -d "$LOG_DIR" ];then
    #mkdir "$LOG_DIR"#不创建日志文件目录
#fi
#if [ ! -f "$LOG_PATH" ];then
    #touch "$LOG_DIR"#不创建文件
#fi

if [ "$1" == "start" ];then
# check server
    PIDS=`ps --no-heading -C java -f --width 1000 | grep $JAR_NAME | awk '{print $2}'`
    if [ -n "$PIDS" ]; then
        echo -e "ERROR: The $JAR_NAME already started and the PID is ${PIDS}."
        exit 1
    fi
    # start
    nohup java $JAVA_MEM_OPTS -jar -Dloader.path=.,resources,lib $JAR_PATH > /dev/null 2>&1 &
 
    COUNT=0
    while [ $COUNT -lt 1 ]; do
        sleep 1
        COUNT=`ps  --no-heading -C java -f --width 1000 | grep "$JAR_NAME" | awk '{print $2}' | wc -l`
        if [ $COUNT -gt 0 ]; then
            break
        fi
    done
    PIDS=`ps  --no-heading -C java -f --width 1000 | grep "$JAR_NAME" | awk '{print $2}'`
    echo "${JAR_NAME} Started and the PID is ${PIDS}."
    echo "Starting the $JAR_PATH...a $LOG_DIR $1"
    echo "CPUG Startup SUCCESS ...."
elif [ "$1" == "stop" ];then
 
    PIDS=`ps --no-heading -C java -f --width 1000 | grep $JAR_NAME | awk '{print $2}'`
    if [ -z "$PIDS" ]; then
        echo "ERROR:The $JAR_NAME does not started!"
        exit 1
    fi
    echo -e "Stopping the $JAR_NAME..."
 
    for PID in $PIDS; do
        kill $PID > /dev/null 2>&1
    done
 
    COUNT=0
    while [ $COUNT -lt 1 ]; do
        sleep 1
        COUNT=1
        for PID in $PIDS ; do
            PID_EXIST=`ps --no-heading -p $PID`
            if [ -n "$PID_EXIST" ]; then
                COUNT=0
                break
            fi
        done
    done
 
    echo -e "${JAR_NAME} Stopped and the PID is ${PIDS}."
    echo -e "CPUG Stop success "
else
    echo_help
    exit 1
fi
