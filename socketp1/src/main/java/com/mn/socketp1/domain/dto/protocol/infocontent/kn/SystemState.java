package com.mn.socketp1.domain.dto.protocol.infocontent.kn;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 10:14
 * DESC 系统状态  8.2.1.1
 */
public enum SystemState {
    SS_01("L", 0, "0", "测试状态"),
    SS_02("L", 0, "1", "正常运行状态"),
    SS_03("L", 1, "0", "无火警"),
    SS_04("L", 1, "1", "火警"),
    SS_05("L", 2, "0", "无故障"),
    SS_06("L", 2, "1", "故障"),
    SS_07("L", 3, "0", "无屏蔽"),
    SS_08("L", 3, "1", "屏蔽"),
    SS_09("L", 4, "0", "无监管"),
    SS_10("L", 4, "1", "监管"),
    SS_11("L", 5, "0", "停止(关闭)"),
    SS_12("L", 5, "1", "启动(开启)"),
    SS_13("L", 6, "0", "无反馈"),
    SS_14("L", 6, "1", "反馈"),
    SS_15("L", 7, "0", "未延时"),
    SS_16("L", 7, "1", "延时状态"),
    SS_17("H", 0, "0", "主电正常"),
    SS_18("H", 0, "1", "主电故障"),
    SS_19("H", 1, "0", "备电正常"),
    SS_20("H", 1, "1", "备电故障"),
    SS_21("H", 2, "0", "总线正常"),
    SS_22("H", 2, "1", "总线故障"),
    SS_23("H", 3, "0", "自动状态"),
    SS_24("H", 3, "1", "手动状态"),
    SS_25("H", 4, "0", "无配置改变"),
    SS_26("H", 4, "1", "配置改变"),
    SS_27("H", 5, "0", "正常"),
    SS_28("H", 5, "1", "复位"),
    SS_29("H", 6, "0", "预留1"),
    SS_30("H", 7, "0", "预留2");

    private String hOrL;  //L表示低位字节，H表示高位字节
    private int bit;  //二进制中第几位，从低位开始计算
    private String state;
    private String meaning;

    SystemState(String hOrL, int bit, String state, String meaning) {
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

    public String gethOrL() {
        return hOrL;
    }

    public void SetHorL(String hOrL) {
        this.hOrL = hOrL;
    }

    public int getBit() {
        return bit;
    }

    public void setBit(int bit) {
        this.bit = bit;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
