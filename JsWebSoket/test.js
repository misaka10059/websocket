var websocket = null;
    if('WebSocket' in window){
        websocket = new WebSocket("ws://localhost:8085/websocket/test");
    }
 
    websocket.onopen = function(){
        console.log("连接成功");
    }	
 
    websocket.onclose = function(){
        console.log("退出连接");
    }
 
    websocket.onmessage = function (event){
        console.log("收到消息"+event.data);
    }
 
    websocket.onerror = function(){
        console.log("连接出错");
    }
 
    window.onbeforeunload = function () {
        websocket.close(num);
    }