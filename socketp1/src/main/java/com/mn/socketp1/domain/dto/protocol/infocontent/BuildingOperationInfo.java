package com.mn.socketp1.domain.dto.protocol.infocontent;

import lombok.Data;
import lombok.ToString;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 16:15
 * DESC 建筑消防设施操作信息 协议8.3.1.4
 * 字节数为协议中该类信息所占字节数
 */
@Data
@ToString
public class BuildingOperationInfo {
    private String systemTypeHex;  //系统类型  1字节
    private String systemTypeMeaning;
    private String systemAddressHex;  //系统地址  1字节
    private String operationIdentifierHex;  //操作标志  1字节
    private String operatorNumberHex;  //操作员编号  1字节
    private String timeLabelHex;  //操作的记录时间  6字节
    private String timeLabelMeaning;

    public BuildingOperationInfo(String systemTypeHex,
                                 String systemTypeMeaning,
                                 String systemAddressHex,
                                 String operationIdentifierHex,
                                 String operatorNumberHex,
                                 String timeLabelHex,
                                 String timeLabelMeaning) {
        this.systemTypeHex = systemTypeHex;
        this.systemTypeMeaning = systemTypeMeaning;
        this.systemAddressHex = systemAddressHex;
        this.operationIdentifierHex = operationIdentifierHex;
        this.operatorNumberHex = operatorNumberHex;
        this.timeLabelHex = timeLabelHex;
        this.timeLabelMeaning = timeLabelMeaning;
    }
}
