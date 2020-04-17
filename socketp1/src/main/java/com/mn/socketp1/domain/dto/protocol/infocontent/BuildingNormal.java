package com.mn.socketp1.domain.dto.protocol.infocontent;

import lombok.Data;
import lombok.ToString;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 19:09
 * DESC
 */
@Data
@ToString
public class BuildingNormal {
    private String systemTypeHex;  //系统类型  1个字节
    private String systemTypeMeaning;
    private String systemAddressHex;  //系统地址  1个字节
    private String componentAddressHex;  //部件地址  4字节

    public BuildingNormal(String systemTypeHex,
                          String systemTypeMeaning,
                          String systemAddressHex,
                          String componentAddressHex) {
        this.systemTypeHex = systemTypeHex;
        this.systemTypeMeaning = systemTypeMeaning;
        this.systemAddressHex = systemAddressHex;
        this.componentAddressHex = componentAddressHex;
    }

    public BuildingNormal(String systemTypeHex, String systemTypeMeaning, String systemAddressHex) {
        this.systemTypeHex = systemTypeHex;
        this.systemTypeMeaning = systemTypeMeaning;
        this.systemAddressHex = systemAddressHex;
        this.componentAddressHex = "";
    }
}
