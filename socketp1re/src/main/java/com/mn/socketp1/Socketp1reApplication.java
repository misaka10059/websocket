package com.mn.socketp1;

import com.mn.socketp1.component.SpringUtil;
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
        SocketServerThread socketServerThread = SpringUtil.getBean(SocketServerThread.class);
        try {
            ServerSocket serverSocket = new ServerSocket(9301);
            while (true) {
                System.out.println("监听端口9301");
                Socket socket = serverSocket.accept();
                socketServerThread.setSocket(socket);
                new Thread(socketServerThread).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
