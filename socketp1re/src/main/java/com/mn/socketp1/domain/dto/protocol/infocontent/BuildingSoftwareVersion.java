package com.mn.socketp1.domain.dto.protocol.infocontent;

import lombok.Data;
import lombok.ToString;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 16:49
 * DESC 建筑消防设施软件版本 协议8.3.1.5
 * 字节数为协议中该类信息所占字节数
 */
@Data
@ToString
public class BuildingSoftwareVersion {
    private String systemTypeHex;  //系统类型  1字节
    private String systemTypeMeaning;
    private String systemAddressHex;  //系统地址  1字节
    private String softwareMainVersionNumberHex;  //软件主版本号  1字节
    private String softwareSecondVersionNumberHex;  //软件次版本号  1字节

    public BuildingSoftwareVersion(String systemTypeHex,
                                   String systemTypeMeaning,
                                   String systemAddressHex,
                                   String softwareMainVersionNumberHex,
                                   String softwareSecondVersionNumberHex) {
        this.systemTypeHex = systemTypeHex;
        this.systemTypeMeaning = systemTypeMeaning;
        this.systemAddressHex = systemAddressHex;
        this.softwareMainVersionNumberHex = softwareMainVersionNumberHex;
        this.softwareSecondVersionNumberHex = softwareSecondVersionNumberHex;
    }
}
