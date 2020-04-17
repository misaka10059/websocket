package com.mn.socketp1.domain.dto.protocol.infocontent.kn;

import lombok.Getter;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/31 9:14
 * DESC 用户信息传输装置运行状态  协议8.2.1.8
 */
@Getter
public enum RunningState {
    RS_01(0, "0", "测试状态"),
    RS_02(0, "1", "正常监视状态"),
    RS_03(1, "0", "无火警"),
    RS_04(1, "1", "火警"),
    RS_05(2, "0", "无故障"),
    RS_06(2, "1", "故障"),
    RS_07(3, "0", "主电正常"),
    RS_08(3, "1", "主电故障"),
    RS_09(4, "0", "备电正常"),
    RS_10(4, "1", "备电故障"),
    RS_11(5, "0", "通信信道正常"),
    RS_12(5, "1", "与监控中心通信信道故障"),
    RS_13(6, "0", "监测连接线路正常"),
    RS_14(6, "1", "监测连接线路故障"),
    RS_15(7, "0", "预留");

    private int bit;  //二进制中第几位，从低位开始计算
    private String state;
    private String meaning;

    RunningState(int bit, String state, String meaning) {
        this.bit = bit;
        this.state = state;
        this.meaning = meaning;
    }

    public static String getMeaning(int bit, String state) {
        for (RunningState runningState : RunningState.values()) {
            if (runningState.getBit() == bit && runningState.getState().equals(state)) {
                return runningState.getMeaning();
            }
        }
        return "bit和state指示的数据不存在";
    }
}
