#!/bin/bash
# 定义变量
# 要运行的jar包路径，加不加引号都行。 注意：等号两边 不能 有空格，否则会提示command找不到
JAR_NAME=$(find . -maxdepth 1 -name "eco-tourism-webapi-*-SNAPSHOT.jar" | head -n 1)
ENV=dev
# CONFIG_PATH=/opt/combine-order/application-cg.yml
 
 
# 如果输入格式不对，给出提示！
tips() {
	echo ""
	echo "WARNING!!!......Tips, please use command: sh auto_deploy.sh [start|stop|restart|status].   For example: sh auto_deploy.sh start  "
	echo ""
	exit 1
}
 
 
# 启动方法
start() {
        # 重新获取一下pid，因为其它操作如stop会导致pid的状态更新
	pid=`ps -ef | grep 'eco-tourism-webapi' | grep java | awk '{print $2}'`
	echo "service start pid= ${pid}"
        # -z 表示如果$pid为空时执行
	if [ -z "$pid" ]; then
#        nohup java -jar $JAR_NAME  --spring.config.location=$CONFIG_PATH > /dev/null 2>&1 &
#        nohup java -jar -Xms128m -Xmx1024m $JAR_NAME  --spring.config.location=$CONFIG_PATH >/dev/null 2>&1 &
	nohup java -jar -Xms128m -Xmx1024m $JAR_NAME --server.port=10800 >nohup.log &
        pid=`ps -ef | grep $JAR_NAME | grep -v grep | awk '{print $2}'`
		echo "end pid = ${pid}"
        echo "Service ${JAR_NAME} is starting！pid=${pid}"
	else
		echo ""
		echo "Service ${JAR_NAME} is already running,it's pid = ${pid}. If necessary, please use command: sh auto_deploy.sh restart."
		echo ""
	fi
}
 
# 停止方法
stop() {
		# 重新获取一下pid，因为其它操作如start会导致pid的状态更新
	pid=`ps -ef | grep 'eco-tourism-webapi' | grep -v grep | awk '{print $2}'`
        # -z 表示如果$pid为空时执行。 注意：每个命令和变量之间一定要前后加空格，否则会提示command找不到
	if [ -z "$pid" ]; then
		echo ""
        echo "Service ${JAR_NAME} is not running! It's not necessary to stop it!"
		echo ""
	else
		kill -9 $pid
		echo ""
		echo "Service stop successfully！pid:${pid} which has been killed forcibly!"
		echo ""
	fi
}
 
# 输出运行状态方法
status() {
        # 重新获取一下pid，因为其它操作如stop、restart、start等会导致pid的状态更新
	pid=`ps -ef | grep 'eco-tourism-webapi' | grep -v grep | awk '{print $2}'`
        # -z 表示如果$pid为空时执行。注意：每个命令和变量之间一定要前后加空格，否则会提示command找不到
	if [ -z "$pid" ];then
		echo ""
        echo "Service ${JAR_NAME} is not running!"
		echo ""
	else
		echo ""
        echo "Service ${JAR_NAME} is running. It's pid=${pid}"
		echo ""
	fi
}
 
# 重启方法
restart() {
	echo ""
	echo ".............................Restarting.............................."
	echo "....................................................................."
		# 重新获取一下pid，因为其它操作如start会导致pid的状态更新
		ps -ef | grep $JAR_NAME
	pid=`ps -ef | grep $JAR_NAME | grep java | awk '{print $2}'`
        # -z 表示如果$pid为空时执行。 注意：每个命令和变量之间一定要前后加空格，否则会提示command找不到
        echo "now it's pid= ${pid}"
	if [ ! -z "$pid" ]; then
		kill -9 $pid
		start
     else start
	fi
	echo "....................Restart successfully！..........................."
}

#restart() {
#	stop
#	start
#}
 
# 根据输入参数执行对应方法，不输入则执行tips提示方法
case "$1" in
   "start")
     start
     ;;
   "stop")
     stop
     ;;
   "status")
     status
     ;;
   "restart")
     restart
     ;;
   *)
     tips
     ;;
esac
