package com.mn.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/24 11:23
 * DESC
 */
@Slf4j
@RestController
@RequestMapping(value = "/data")
public class DataController {
    @RequestMapping(value = "receive", method = RequestMethod.POST)
    public void receiveData(@RequestParam("message") String message) {
        log.info(message);
    }
}
