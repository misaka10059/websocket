package com.mn.socketp1.domain.dto.protocol.infocontent;

import lombok.Data;
import lombok.ToString;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 14:17
 * DESC 建筑消防设施部件状态 协议8.3.1.2
 * 字节数为协议中该类信息所占字节数
 */
@Data
@ToString
public class BuildingComponentState {
    private String systemTypeHex;  //系统类型  1个字节
    private String systemTypeMeaning;
    private String systemAddressHex;  //系统地址  1个字节
    private String componentTypeHex;  //部件类型  1字节
    private String componentTypeMeaning;
    private String componentAddressHex;  //部件地址  4字节
    private String areaCode;  //区号
    private String positionCode;  //位号
    private String componentStateHex;  //部件状态 2字节
    private String componentNoteHex;  //部件说明  31字节
    private String componentNoteMeaning;
    private String timeLabelHex;  //状态发生时间  6字节
    private String timeLabelMeaning;

    public BuildingComponentState(String systemTypeHex,
                                  String systemTypeMeaning,
                                  String systemAddressHex,
                                  String componentTypeHex,
                                  String componentTypeMeaning,
                                  String componentAddressHex,
                                  String areaCode,
                                  String positionCode,
                                  String componentStateHex,
                                  String componentNoteHex,
                                  String componentNoteMeaning,
                                  String timeLabelHex,
                                  String timeLabelMeaning) {
        this.systemTypeHex = systemTypeHex;
        this.systemTypeMeaning = systemTypeMeaning;
        this.systemAddressHex = systemAddressHex;
        this.componentTypeHex = componentTypeHex;
        this.componentTypeMeaning = componentTypeMeaning;
        this.componentAddressHex = componentAddressHex;
        this.areaCode = areaCode;
        this.positionCode = positionCode;
        this.componentStateHex = componentStateHex;
        this.componentNoteHex = componentNoteHex;
        this.componentNoteMeaning = componentNoteMeaning;
        this.timeLabelHex = timeLabelHex;
        this.timeLabelMeaning = timeLabelMeaning;
    }
}
