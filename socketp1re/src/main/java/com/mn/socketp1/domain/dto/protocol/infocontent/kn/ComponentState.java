package com.mn.socketp1.domain.dto.protocol.infocontent.kn;

import lombok.Getter;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/27 11:12
 * DESC 部件状态  协议8.2.1.2
 */
@Getter
public enum ComponentState {
    CS_01("L", 0, "0", "测试状态"),
    CS_02("L", 0, "1", "正常运行状态"),
    CS_03("L", 1, "0", "无火警"),
    CS_04("L", 1, "1", "火警"),
    CS_05("L", 2, "0", "无故障"),
    CS_06("L", 2, "1", "故障"),
    CS_07("L", 3, "0", "无屏蔽"),
    CS_08("L", 3, "1", "屏蔽"),
    CS_09("L", 4, "0", "无监管"),
    CS_10("L", 4, "1", "监管"),
    CS_11("L", 5, "0", "停止(关闭)"),
    CS_12("L", 5, "1", "启动(开启)"),
    CS_13("L", 6, "0", "无反馈"),
    CS_14("L", 6, "1", "反馈"),
    CS_15("L", 7, "0", "未延时"),
    CS_16("L", 7, "1", "延时状态"),
    CS_17("H", 0, "0", "电源正常"),
    CS_18("H", 0, "1", "电源故障"),
    CS_19("H", 1, "0", "预留1"),
    CS_20("H", 2, "0", "预留2"),
    SS_21("H", 3, "0", "预留3"),
    SS_22("H", 4, "0", "预留4"),
    SS_23("H", 5, "0", "预留5"),
    SS_24("H", 6, "0", "预留6"),
    SS_25("H", 7, "0", "预留7");

    private String hOrL;  //L表示低位字节，H表示高位字节
    private int bit;  //二进制中第几位，从低位开始计算
    private String state;
    private String meaning;

    ComponentState(String hOrL, int bit, String state, String meaning) {
        this.hOrL = hOrL;
        this.bit = bit;
        this.state = state;
        this.meaning = meaning;
    }

    public static String getMeaning(String horL, int bit, String state) {
        for (SystemState systemState : SystemState.values()) {
            if (systemState.gethOrL().equals(horL) && systemState.getBit() == bit && systemState.getState().equals(state)) {
                return systemState.getMeaning();
            }
        }
        return "bit和state指示的数据不存在";
    }
}
