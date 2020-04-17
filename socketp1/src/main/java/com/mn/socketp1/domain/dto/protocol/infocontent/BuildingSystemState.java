package com.mn.socketp1.domain.dto.protocol.infocontent;

import lombok.Data;
import lombok.ToString;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 10:09
 * DESC 建筑消防设施系统状态 协议8.3.1.1
 * 字节数为协议中该类信息所占字节数
 */
@Data
@ToString
public class BuildingSystemState {
    private String systemTypeHex;  //系统类型  1个字节
    private String systemTypeMeaning;
    private String systemAddressHex;  //系统地址  1个字节
    private String systemStateHex;  //系统状态  2个字节
    private String timeLabelHex;  //状态发生时间  6字节
    private String timeLabelMeaning;

    public BuildingSystemState(String systemTypeHex,
                               String systemTypeMeaning,
                               String systemAddressHex,
                               String systemStateHex,
                               String timeLabelHex,
                               String timeLabelMeaning) {
        this.setSystemTypeHex(systemTypeHex);
        this.setSystemTypeMeaning(systemTypeMeaning);
        this.setSystemAddressHex(systemAddressHex);
        this.setSystemStateHex(systemStateHex);
        this.setTimeLabelHex(timeLabelHex);
        this.setTimeLabelMeaning(timeLabelMeaning);
    }
}
