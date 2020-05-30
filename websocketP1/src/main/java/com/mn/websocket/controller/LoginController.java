package com.mn.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/23 18:33
 * DESC
 */
@Controller
@RequestMapping(value = "/socket")
public class LoginController {
    /**
     * 第一个用户
     */
    @RequestMapping("/chat1")
    public String chat1(HttpServletRequest request) {
        // 假设用户tom登录,存储到session中
        request.getSession().setAttribute("WEBSOCKET_USERNAME", "tom");
        return "chat1";
    }

    /**
     * 第二个用户登录
     */
    @RequestMapping("/chat2")
    public String chat2(HttpServletRequest request) {
        // 假设用户jerry登录,存储到session中
        request.getSession().setAttribute("WEBSOCKET_USERNAME", "jerry");
        return "chat2";
    }

    /**
     * 第三个用户登录
     */
    @RequestMapping("/chat3")
    public String chat3(HttpServletRequest request) {
        // 假设用户jack登录,存储到session中
        request.getSession().setAttribute("WEBSOCKET_USERNAME", "jack");
        return "chat3";
    }
}
