package com.mn.socketp1.domain.dto.protocol.infocontent.kn;

import lombok.Getter;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 16:23
 * DESC 建筑消防设施操作标志  协议8.2.1.4
 */
@Getter
public enum BuildingOperationIdentifier {
    BOI_01(0, "0", "无操作"),
    BOI_02(0, "1", "复位"),
    BOI_03(1, "0", "无操作"),
    BOI_04(1, "1", "消音"),
    BOI_05(2, "0", "无操作"),
    BOI_06(2, "1", "手动报警"),
    BOI_07(3, "0", "无操作"),
    BOI_08(3, "1", "警情消除"),
    BOI_09(4, "0", "无操作"),
    BOI_10(4, "1", "自检"),
    BOI_11(5, "0", "无操作"),
    BOI_12(5, "1", "确认"),
    BOI_13(6, "0", "无操作"),
    BOI_14(6, "1", "测试"),
    BOI_15(7, "0", "预留");

    private int bit;
    private String state;
    private String meaning;

    BuildingOperationIdentifier(int bit, String state, String meaning) {
        this.bit = bit;
        this.state = state;
        this.meaning = meaning;
    }

    public static String getMeaning(int bit, String state) {
        for (BuildingOperationIdentifier buildingOperationIdentifier : BuildingOperationIdentifier.values()) {
            if (buildingOperationIdentifier.getBit() == bit && buildingOperationIdentifier.getState().equals(state)) {
                return buildingOperationIdentifier.getMeaning();
            }
        }
        return "bit和state指示的数据不存在";
    }
}
