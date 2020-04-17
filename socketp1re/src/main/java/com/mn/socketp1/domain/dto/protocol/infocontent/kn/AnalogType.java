package com.mn.socketp1.domain.dto.protocol.infocontent.kn;

import lombok.Getter;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 15:21
 * DESC 建筑消防设施部件模拟量值  协议8.2.1.3
 */
@Getter
public enum AnalogType {
    AT_01("未用", "", "00"),
    AT_02("事件计数", "件", "01"),
    AT_03("高度", "m", "02"),
    AT_04("温度", "摄氏度", "03"),
    AT_05("压力", "MPa", "04"),
    AT_06("压力", "KPa", "05"),
    AT_07("气体浓度", "%LEL", "06"),
    AT_08("时间", "s", "07"),
    AT_09("电压", "V", "08"),
    AT_10("电流", "A", "09"),
    AT_11("流量", "L/s", "0a"),
    AT_12("风量", "立方米/每分钟", "0b"),
    AT_13("风速", "m/s", "0c"),
    AT_14("预留", "", "0d-7f"),
    AT_15("用户自定义", "", "80-ff");

    private String meaning;  //含义
    private String unit;  //单位
    private String hex;  //协议中16进制的表示

    AnalogType(String meaning, String unit, String hex) {
        this.meaning = meaning;
        this.unit = unit;
        this.hex = hex;
    }

    public static String getMeaning(String hex) {
        for (AnalogType analogType : AnalogType.values()) {
            if (analogType.getHex().equals(hex)) {
                return analogType.getMeaning();
            }
        }
        return "hex指示的数据不存在";
    }
}
