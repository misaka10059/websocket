package com.mn.websocketp3.controller;

import com.mn.websocketp3.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/24 16:20
 * DESC
 */
@Controller
public class IndexController {
    @Autowired
    private WebSocketServer webSocketServer;

    @RequestMapping("/send.do")
    public ModelAndView send() {
        ModelAndView modelAndView = new ModelAndView("/send.html");
        webSocketServer.sendMsgToAll("Hello World");
        return modelAndView;
    }
}
