#!/bin/bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_91.jdk/Contents/Home
echo $JAVA_HOME
export PATH=$JAVA_HOME:$PATH
echo $PATH

#env |sort >/tmp/my_cron_env.txt
b=$(env | sort)
echo $b
java -jar /tmp/JavaSDKHelloWorld.jar
