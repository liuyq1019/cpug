#!/bin/sh

rm -f cpugtpid

nohup java -jar -Dloader.path=.,resources,lib cpug-3.6.1.1.jar > /dev/null 2>&1 &

echo $! > cpugtpid

echo Start Success!