package com.mn.socketp1;

import com.mn.socketp1.service.SocketServerThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
public class Socketp1reApplication {

    public static void main(String[] args) {
        SpringApplication.run(Socketp1reApplication.class, args);
        try {
            ServerSocket serverSocket = new ServerSocket(9301);
            Socket socket;
            System.out.println("监听端口9301");
            while (true) {
                socket = serverSocket.accept();
                /* 是否过滤客户端的ip */
                /*if(!IpList.containsIp(socket.getRemoteSocketAddress().toString())){
                    socket.close();
                   continue;
                }*/
                SocketServerThread thread = new SocketServerThread(socket);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
