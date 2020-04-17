echo "Hello World"

function pause()
{
　　if [ "x$1" != "x" ] ;then
　　　　echo $1
　　fi
　　if [ $enable_pause -eq 1 ];then
　　　　echo "Press any key to continue!"
　　char=`get_char`
　　fi
}
pause "pause"