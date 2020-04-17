package com.mn.socketp1.domain.dto.protocol.infocontent.kn;

import lombok.Getter;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/25 14:27
 * DESC 控制单元命令字节  协议6.6
 */
@Getter
public enum ControlUnitCommandByteType {
    CUCBT_01("预留1", "01"),
    CUCBT_02("控制命令", "02"),
    CUCBT_03("发送数据", "03"),
    CUCBT_04("确认", "04"),
    CUCBT_05("请求", "04"),
    CUCBT_06("应答", "05"),
    CUCBT_07("否认", "06"),
    CUCBT_08("预留2", "07-7f"),
    CUCBT_09("用户自定义", "80-ff");

    private String content;
    private String hex;

    ControlUnitCommandByteType(String content, String hex) {
        this.content = content;
        this.hex = hex;
    }

    public static String getContent(String hex) {
        for (ControlUnitCommandByteType commandByteType : ControlUnitCommandByteType.values()) {
            if (commandByteType.getHex().equals(hex)) {
                return commandByteType.getContent();
            }
        }
        return null;
    }
}
