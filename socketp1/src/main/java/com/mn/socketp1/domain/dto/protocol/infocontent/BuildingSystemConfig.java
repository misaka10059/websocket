package com.mn.socketp1.domain.dto.protocol.infocontent;

import lombok.Data;
import lombok.ToString;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 17:02
 * DESC 建筑消防设施系统配置 协议8.3.1.6
 * 字节数为协议中该类信息所占字节数
 */
@Data
@ToString
public class BuildingSystemConfig {
    private String systemTypeHex;  //系统类型  1字节
    private String systemTypeMeaning;
    private String systemAddressHex;  //系统地址  1字节
    private String systemDescriptionLengthHex;  //系统说明长度  不定
    private String systemDescriptionHex;  //系统说明  1字节
    private String systemDescriptionMeaning;

    public BuildingSystemConfig(String systemTypeHex,
                                String systemTypeMeaning,
                                String systemAddressHex,
                                String systemDescriptionLengthHex,
                                String systemDescriptionHex,
                                String systemDescriptionMeaning) {
        this.systemTypeHex = systemTypeHex;
        this.systemTypeMeaning = systemTypeMeaning;
        this.systemAddressHex = systemAddressHex;
        this.systemDescriptionLengthHex = systemDescriptionLengthHex;
        this.systemDescriptionHex = systemDescriptionHex;
        this.systemDescriptionMeaning = systemDescriptionMeaning;
    }
}
