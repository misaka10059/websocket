package com.mn.socketp1.domain.dto.protocol.infocontent.kn;

import lombok.Getter;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/31 9:30
 * DESC 用户信息传输装置操作标志  协议8.2.1.9
 */
@Getter
public enum UserOperationIdentifier {
    UOI_01(0, "0", "无操作"),
    UOI_02(0, "1", "复位"),
    UOI_03(1, "0", "无操作"),
    UOI_04(1, "1", "消音"),
    UOI_05(2, "0", "无操作"),
    UOI_06(2, "1", "手动报警"),
    UOI_07(3, "0", "无操作"),
    UOI_08(3, "1", "警情消除"),
    UOI_09(4, "0", "无操作"),
    UOI_10(4, "1", "自检"),
    UOI_11(5, "0", "无操作"),
    UOI_12(5, "1", "查岗应答"),
    UOI_13(6, "0", "无操作"),
    UOI_14(6, "1", "测试"),
    UOI_15(7, "0", "预留");

    private int bit;
    private String state;
    private String meaning;

    UserOperationIdentifier(int bit, String state, String meaning) {
        this.bit = bit;
        this.state = state;
        this.meaning = meaning;
    }

    public static String getMeaning(int bit, String state) {
        for (UserOperationIdentifier userOperationIdentifier : UserOperationIdentifier.values()) {
            if (userOperationIdentifier.getBit() == bit && userOperationIdentifier.getState().equals(state)) {
                return userOperationIdentifier.getMeaning();
            }
        }
        return "bit和state指示的数据不存在";
    }
}
