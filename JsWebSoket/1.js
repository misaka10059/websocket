var ws = new WebSocket("ws://localhost:8080/msg");
//readyState属性返回实例对象的当前状态，共有四种。
//CONNECTING：值为0，表示正在连接。
//OPEN：值为1，表示连接成功，可以通信了。
//CLOSING：值为2，表示连接正在关闭。
//CLOSED：值为3，表示连接已经关闭，或者打开连接失败
//例如：if (ws.readyState == WebSocket.CONNECTING) { }

//【用于指定连接成功后的回调函数】
ws.onopen = function (evt) {
    console.log("Connection open ...");
    ws.send("Hello WebSockets!");
};
//ws.addEventListener('open', function (event) {
//    ws.send('Hello Server!');
//};

//【用于指定收到服务器数据后的回调函数】
//【服务器数据有可能是文本，也有可能是二进制数据，需要判断】
ws.onmessage = function (event) {
    if (typeof event.data === String) {
        console.log("Received data string");
    }

    if (event.data instanceof ArrayBuffer) {
        var buffer = event.data;
        console.log("Received arraybuffer");
    }
    console.log("Received Message: " + evt.data);
    ws.close();
};

//[【于指定连接关闭后的回调函数。】
ws.onclose = function (evt) {
    console.log("Connection closed.");
};

//发送文本
ws.send("Hello WebSockets!");
//发送Blob数据
var file = document
    .querySelector('input[type="file"]')
    .files[0];
ws.send(file);
//发送ArrayBuffer
var img = canvas_context.getImageData(0, 0, 400, 320);
var binary = new Uint8Array(img.data.length);
for (var i = 0; i < img.data.length; i++) {
    binary[i] = img.data[i];
}
ws.send(binary.buffer);

//webSocket.bufferedAmount
//bufferedAmount属性，表示还有多少字节的二进制数据没有发送出去。它可以用来判断发送是否结束
var data = new ArrayBuffer(10000000);
socket.send(data);

if (socket.bufferedAmount === 0) {
    // 发送完毕
} else {
    // 发送还没结束
}

//webSocket.onerror 用于指定报错时的回调函数

ws.onerror = function (event) {
};

es.addEventListener("error", function (event) {
});