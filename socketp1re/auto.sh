#! /bin/bash
# scp代码到服务器后执行

IP=39.99.208.254
USER=tianli
PASS=4869kenan
LOCATION=/home/service/tran

for service in `ls target`
do
    FILE=target/${service}
    COPY_PATH=$LOCATION
    echo param: FILE $FILE
    echo param: USER $USER
    echo param: PASS $PASS
    echo param: IP $IP
    echo param: COPY_PATH $COPY_PATH
    echo scp -r $FILE $USER@$IP:$COPY_PATH
done
echo finish

