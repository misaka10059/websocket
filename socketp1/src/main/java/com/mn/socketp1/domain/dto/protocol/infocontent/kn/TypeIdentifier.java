package com.mn.socketp1.domain.dto.protocol.infocontent.kn;

import lombok.Getter;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/25 16:42
 * DESC 类型标识  协议8.1.1.1
 */
@Getter
public enum TypeIdentifier {
    TI_01("预留1", "00", -2),
    TI_02("上传建筑消防设施系统状态", "01", 10),
    TI_03("上传建筑消防设施部件运行状态", "02", 46),
    TI_04("上传建筑消防设施部件模拟量值", "03", 16),
    TI_05("上传建筑消防设施操作信息", "04", 10),
    TI_06("上传建筑消防设施软件版本", "05", 4),  //无时间标签
    TI_07("上传建筑消防设施系统配置情况", "06", -1),  //无时间标签
    TI_08("上传建筑消防设施部件配置情况", "07", 38),  //无时间标签
    TI_09("上传建筑消防设施系统时间", "08", 8),
    TI_10("预留(建筑消防设施信息)", "09-14", -2),  //十进制9-20
    TI_11("上传用户信息传输装置运行状态", "15", 7),
    TI_12("预留2", "16", -2),
    TI_13("预留3", "17", -2),
    TI_14("上传用户信息传输装置操作信息", "18", 8),
    TI_15("上传用户信息传输装置软件版本", "19", 2),  //无时间标签
    TI_16("上传用户信息传输装置配置情况", "1a", -1),  //无时间标签
    TI_17("预留4", "1b", -2),
    TI_18("上传用户信息传输装置系统时间", "1c", 6),
    TI_19("预留(用户信息传输装置信息)", "1d-28", -2), //十进制29-40
    TI_20("预留(控制信息)", "29-3c", -2),  //十进制41-60
    TI_21("读建筑消防设施系统状态", "3d", 2),  //无时间标签
    TI_22("读建筑消防设施部件运行状态", "3e", 6),  //无时间标签
    TI_23("读建筑消防设施部件模拟量值", "3f", 6),  //无时间标签
    TI_24("读建筑消防设施操作信息", "40", 9),  //无时间标签
    TI_25("读建筑消防设施软件版本", "41", 2),  //无时间标签
    TI_26("读建筑消防设施系统配置情况", "42", 2),  //无时间标签
    TI_27("读建筑消防设施部件配置情况", "43", 6),  //无时间标签
    TI_28("读建筑消防设施系统时间", "44", 2),  //无时间标签
    TI_29("预留5", "45-50", -2),
    TI_30("读用户信息传输装置运行状态", "51", 1),  //无时间标签
    TI_31("预留6", "52", -2),
    TI_32("预留7", "53", -2),
    TI_33("读用户信息传输装置操作信息记录", "54", 7),
    TI_34("读用户信息传输装置软件版本", "55", 1),  //无时间标签
    TI_35("读用户信息传输装置配置情况", "56", 1),  //无时间标签
    TI_36("预留8", "57", -2),
    TI_37("读用户信息传输装置系统时间", "58", 1),  //无时间标签
    TI_38("初始化用户信息传输装置", "59", 1),  //无时间标签
    TI_39("同步用户信息传输装置时钟", "5a", 6),
    TI_40("查岗命令", "5b", 1),  //无时间标签
    TI_41("预留9", "5c-7f", -2),
    TI_42("用户自定义", "80-fe", -2);

    private String content;  //类型标识内容
    private String hex;  //协议中16进制的表示
    private int dataLength;  //此类型单个信息体字节数，包含所有字段和时间标签,预留均为-2，不确定长度均为-1

    TypeIdentifier(String content, String hex, int dataLength) {
        this.content = content;
        this.hex = hex;
        this.dataLength = dataLength;
    }

    public static TypeIdentifier getTypeIdentifier(String hex) {
        for (TypeIdentifier typeIdentifier : TypeIdentifier.values()) {
            if (typeIdentifier.getHex().equals(hex)) {
                return typeIdentifier;
            }
        }
        return null;
    }

    public static String getContent(String hex) {
        for (TypeIdentifier typeIdentifier : TypeIdentifier.values()) {
            if (typeIdentifier.getHex().equals(hex)) {
                return typeIdentifier.getContent();
            }
        }
        return null;
    }
}
