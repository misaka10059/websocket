package com.mn.socketp1;

import com.mn.socketp1.service.SocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Socketp1Application {

    public static void main(String[] args) {
        SpringApplication.run(Socketp1Application.class, args);
        SocketServer server = new SocketServer();
        server.talk();
    }
}
