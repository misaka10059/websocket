package com.mn.socketp1.domain.dto.protocol.infocontent;

import lombok.Data;
import lombok.ToString;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 17:56
 * DESC 建筑消防设施系统时间 协议8.3.1.8
 * 字节数为协议中该类信息所占字节数
 */
@Data
@ToString
public class BuildingSystemTime {
    private String systemTypeHex;  //系统类型  1个字节
    private String systemTypeMeaning;
    private String systemAddressHex;  //系统地址  1个字节
    private String timeLabelHex;  //建筑消防设施的系统时间  6字节
    private String timeLabelMeaning;

    public BuildingSystemTime(String systemTypeHex,
                              String systemTypeMeaning,
                              String systemAddressHex,
                              String timeLabelHex,
                              String timeLabelMeaning) {
        this.systemTypeHex = systemTypeHex;
        this.systemTypeMeaning = systemTypeMeaning;
        this.systemAddressHex = systemAddressHex;
        this.timeLabelHex = timeLabelHex;
        this.timeLabelMeaning = timeLabelMeaning;
    }
}
