value=`ps aux | grep -v grep | grep socketp1re.jar | awk '{print $2}'`
if [[ ${value} ]]; then
        echo '(～￣▽￣)～ 程序正在运行';
        echo `ps aux | grep -v grep | grep socketp1re.jar`;
else
        echo '(～￣▽￣)～ 程序未运行';
fi