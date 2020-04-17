package com.mn.socketp1.domain.dto.protocol.infocontent;

import lombok.Data;
import lombok.ToString;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 18:27
 * DESC 用户信息传输装置系统时间 协议8.3.1.13
 * 字节数为协议中该类信息所占字节数
 */
@Data
@ToString
public class UserSystemTime {
    private String systemTimeHex;  //用户信息传输装置的系统时间  6字节
    private String systemTimeMeaning;

    public UserSystemTime(String systemTimeHex, String systemTimeMeaning) {
        this.systemTimeHex = systemTimeHex;
        this.systemTimeMeaning = systemTimeMeaning;
    }
}
