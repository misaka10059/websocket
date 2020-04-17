package com.mn.socketp1.domain.dto.data;

import lombok.Data;
import lombok.ToString;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/25 17:24
 * DESC
 */
@Data
@ToString
public class Message {

    private String controlUnitHex;  //控制单元

    private String appDataUnitHex;  //应用数据单元

    private String checkSumHex;  //校验和

    /*
     * ControlUnit包含以下部分
     */
    private String businessSerialNumberHex;  //业务流水号

    private String protocolVersionNumberHex;  //协议版本号

    private String timeLabelHex;  //时间标签

    private String timeLabelMeaning;

    private String sourceAddressHex;  //源地址

    private String destinationAddressHex;  //目的地址

    private String appDataUnitLengthHex;  //应用数据单元长度

    private String appDataUnitLengthMeaning;

    private String commandByteHex;  //命令字节

    private String commandByteMeaning;

    /*
     *AppDataUnit包含以下部分
     */
    private String typeIdentifierHex;  //类型标识

    private String infoObjectsNumberHex;  //信息对象数目

    public Message(String controlUnitHex,
                   String appDataUnitHex,
                   String checkSumHex,
                   String businessSerialNumberHex,
                   String protocolVersionNumberHex,
                   String timeLabelHex,
                   String timeLabelMeaning,
                   String sourceAddressHex,
                   String destinationAddressHex,
                   String appDataUnitLengthHex,
                   String appDataUnitLengthMeaning,
                   String commandByteHex,
                   String commandByteMeaning,
                   String typeIdentifierHex,
                   String infoObjectsNumberHex) {
        this.controlUnitHex = controlUnitHex;
        this.appDataUnitHex = appDataUnitHex;
        this.checkSumHex = checkSumHex;
        this.businessSerialNumberHex = businessSerialNumberHex;
        this.protocolVersionNumberHex = protocolVersionNumberHex;
        this.timeLabelHex = timeLabelHex;
        this.timeLabelMeaning = timeLabelMeaning;
        this.sourceAddressHex = sourceAddressHex;
        this.destinationAddressHex = destinationAddressHex;
        this.appDataUnitLengthHex = appDataUnitLengthHex;
        this.appDataUnitLengthMeaning = appDataUnitLengthMeaning;
        this.commandByteHex = commandByteHex;
        this.commandByteMeaning = commandByteMeaning;
        this.typeIdentifierHex = typeIdentifierHex;
        this.infoObjectsNumberHex = infoObjectsNumberHex;
    }
}
