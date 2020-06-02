#文件如果是在windows上编辑的，则其默认格式是dos，那么上传到linux上执行时需要转换为为unix格式，可使用命令：dos2unix filename
#寻找正在运行的进程
pid=`ps aux |grep socketp1re.jar|grep -v grep | awk '{print $2}'`
if [[ ${pid} ]]; then
        echo '(～￣▽￣)～ 程序正在运行，终止程序'
        kill -9 ${pid}
else
        echo '(～￣▽￣)～ 程序已经终止'
fi