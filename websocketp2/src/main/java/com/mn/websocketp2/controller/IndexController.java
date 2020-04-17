package com.mn.websocketp2.controller;

import com.mn.websocketp2.service.SocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/24 16:01
 * DESC
 */
@RestController
@RequestMapping("/websocket")
public class IndexController {

    @Autowired
    private SocketClient socketClient;

    @GetMapping("/sendMessage")
    public String sendMessage(String message) {
        socketClient.groupSending(message);
        return message;
    }
}
