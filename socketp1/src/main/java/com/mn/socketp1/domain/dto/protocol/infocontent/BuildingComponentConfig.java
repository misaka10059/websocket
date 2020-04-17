package com.mn.socketp1.domain.dto.protocol.infocontent;

import lombok.Data;
import lombok.ToString;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 17:41
 * DESC 建筑消防设施系统部件配置 协议8.3.1.7
 * 字节数为协议中该类信息所占字节数
 */
@Data
@ToString
public class BuildingComponentConfig {
    private String systemTypeHex;  //系统类型  1字节
    private String systemTypeMeaning;
    private String systemAddressHex;  //系统地址  1字节
    private String componentTypeHex;  //部件类型  1字节
    private String componentTypeMeaning;
    private String componentAddressHex;  //部件地址  4字节
    private String componentNoteHex;  //部件说明  31字节
    private String componentNoteMeaning;

    public BuildingComponentConfig(String systemTypeHex,
                                   String systemTypeMeaning,
                                   String systemAddressHex,
                                   String componentTypeHex,
                                   String componentTypeMeaning,
                                   String componentAddressHex,
                                   String componentNoteHex,
                                   String componentNoteMeaning) {
        this.systemTypeHex = systemTypeHex;
        this.systemTypeMeaning = systemTypeMeaning;
        this.systemAddressHex = systemAddressHex;
        this.componentTypeHex = componentTypeHex;
        this.componentTypeMeaning = componentTypeMeaning;
        this.componentAddressHex = componentAddressHex;
        this.componentNoteHex = componentNoteHex;
        this.componentNoteMeaning = componentNoteMeaning;
    }
}
