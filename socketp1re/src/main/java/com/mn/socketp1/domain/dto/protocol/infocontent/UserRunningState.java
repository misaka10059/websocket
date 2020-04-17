package com.mn.socketp1.domain.dto.protocol.infocontent;

import lombok.Data;
import lombok.ToString;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 18:04
 * DESC 用户信息传输装置运行状态 协议8.3.1.9
 * 字节数为协议中该类信息所占字节数
 */
@Data
@ToString
public class UserRunningState {
    private String runningStateHex;  //状态  1字节
    private String stateOccurrenceTimeHex;  //状态发生时间  6字节
    private String stateOccurrenceTimeMeaning;

    public UserRunningState(String runningStateHex, String stateOccurrenceTimeHex, String stateOccurrenceTimeMeaning) {
        this.runningStateHex = runningStateHex;
        this.stateOccurrenceTimeHex = stateOccurrenceTimeHex;
        this.stateOccurrenceTimeMeaning = stateOccurrenceTimeMeaning;
    }
}
