package com.mn.websocket.main;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/25 8:38
 * DESC
 */
public class Client {
    public static void main(String args[]) throws Exception {
        //为了简单起见，所有的异常都直接往外抛
        String host = "127.0.0.1";  //要连接的服务端IP地址
        int port = 9302;   //要连接的服务端对应的监听端口
        //与服务端建立连接
        Socket client = new Socket(host, port);
        //建立连接后就可以往服务端写数据了
        Writer writer = new OutputStreamWriter(client.getOutputStream());
        writer.write("Hello Server.");
        writer.flush();//写完后要记得flush
        writer.close();
        client.close();
    }
}
