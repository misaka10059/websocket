var ws = new WebSocket("ws://localhost:9801/my_SXD_Socket");

ws.onopen = function (evt) {
    console.log("Connection open ...");
    ws.send("Hello WebSockets!");
};

ws.onmessage = function (evt) {
    console.log("Received Message: " + evt.data);
    ws.close();
};

ws.onclose = function (evt) {
    console.log("Connection closed.");
};