package com.mn.socketp1.domain.dto.protocol.infocontent;

import lombok.Data;
import lombok.ToString;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 15:18
 * DESC 建筑消防设施部件模拟量值 协议8.3.1.3
 * 字节数为协议中该类信息所占字节数
 */
@Data
@ToString
public class BuildingComponentAnalog {
    private String systemTypeHex;  //系统类型  1字节
    private String systemTypeMeaning;
    private String systemAddressHex;  //系统地址  1字节
    private String componentTypeHex;  //部件类型  1字节
    private String componentTypeMeaning;
    private String componentAddressHex;  //部件地址  4字节
    private String analogTypeHex;  //模拟量类型  1字节
    private String analogTypeMeaning;
    private String analogValueHex;  //模拟量值  2字节
    private int analogValueMeaning;
    private String timeLabelHex;  //模拟量值的采样时间  6字节
    private String timeLabelMeaning;

    public BuildingComponentAnalog(String systemTypeHex,
                                   String systemTypeMeaning,
                                   String systemAddressHex,
                                   String componentTypeHex,
                                   String componentTypeMeaning,
                                   String componentAddressHex,
                                   String analogTypeHex,
                                   String analogTypeMeaning,
                                   String analogValueHex,
                                   int analogValueMeaning,
                                   String timeLabelHex,
                                   String timeLabelMeaning) {
        this.systemTypeHex = systemTypeHex;
        this.systemTypeMeaning = systemTypeMeaning;
        this.systemAddressHex = systemAddressHex;
        this.componentTypeHex = componentTypeHex;
        this.componentTypeMeaning = componentTypeMeaning;
        this.componentAddressHex = componentAddressHex;
        this.analogTypeHex = analogTypeHex;
        this.analogTypeMeaning = analogTypeMeaning;
        this.analogValueHex = analogValueHex;
        this.analogValueMeaning = analogValueMeaning;
        this.timeLabelHex = timeLabelHex;
        this.timeLabelMeaning = timeLabelMeaning;
    }
}
