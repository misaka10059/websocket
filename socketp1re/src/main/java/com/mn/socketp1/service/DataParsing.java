package com.mn.socketp1.service;

import com.mn.socketp1.common.Number;
import com.mn.socketp1.config.Offset;
import com.mn.socketp1.domain.dto.data.Message;
import com.mn.socketp1.domain.dto.protocol.AppDataUnit;
import com.mn.socketp1.domain.dto.protocol.infocontent.kn.ControlUnitCommandByteType;
import com.mn.socketp1.domain.dto.protocol.infocontent.kn.TypeIdentifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/25 15:22
 * DESC 协议数据解析
 */
@Service
public class DataParsing {

    private AppDataUnit dataUnit = new AppDataUnit();

    /**
     * DATE 2020/3/25 15:25
     * DESC 接收的字节流数据组成如下：起始符("@@")+控制单元+应用数据单元+校验和+结束符("##")
     * 应用数据单元可以为空，若不为空，则内容由应用数据单元开始的类型标识符和信息体数目决定，
     * 应用数据单元中的信息体的内容由类型标识符决定，个数由信息体数目决定
     */
    void parsing(String message) {
        System.out.println("***************************************************************");
        System.out.println("**********************接收有效数据，开始解析**********************");
        System.out.println("***************************************************************");
        System.out.println("接收时间：" + LocalDateTime.now());
        System.out.println("需要解析的十六进制字节流为:" + message);
        /*
         *          ———————————————————————————————————
         * 数据格式：|起始符|控制单元|应用单元|校验和|结束符|
         *          ———————————————————————————————————
         *
         * 获取除应用数据单元外的其他数据的16进制字符串：启动符、控制单元、校验和、结束符
         *
         *              ————————————————————————————————————————————————————————————————
         * 控制单元格式：|业务流水号|协议版本号|时间标签|源地址|目的地址|应用数据单元长度|命令字节|
         *              ————————————————————————————————————————————————————————————————
         *
         */
//      String startIdentifier = message.substring(Offset.startIdentifierStart, Offset.startIdentifierEnd);
        String controlUnitHex = message.substring(Offset.controlUnitStart, Offset.controlUnitEnd);  //控制单元16进制数据
        String businessSerialNumberHex = message.substring(Offset.businessSerialNumberStart, Offset.businessSerialNumberEnd);  //业务流水号16进制数据
        String protocolVersionNumberHex = message.substring(Offset.protocolVersionNumberStart, Offset.protocolVersionNumberEnd);  //协议版本号16进制数据
        String timeLabelHex = message.substring(Offset.timeLabelStart, Offset.timeLabelEnd);  //时间标签16进制数据
        String sourceAddressHex = message.substring(Offset.sourceAddressStart, Offset.sourceAddressEnd);  //源地址16进制数据
        String destinationAddressHex = message.substring(Offset.destinationAddressStart, Offset.destinationAddressEnd);  //目的地址16进制数据
        String appDataUnitLengthHex = message.substring(Offset.appDataUnitLengthStart, Offset.appDataUnitLengthEnd);  //应用数据单元长度16进制数据
        String commandByteHex = message.substring(Offset.commandByteStart, Offset.commandByteEnd);  //命令字节16进制数据

        int appDataUnitLength = Number.getIntValue(appDataUnitLengthHex);
//        System.out.println("old message:" + message);
        String judgeMessage = message.substring(Offset.controlUnitEnd);
//        System.out.println("judgeMessage:" + judgeMessage);
//        System.out.println("judgeMessageLength:" + judgeMessage.length());
//        System.out.println("appDataUnitLength:" + appDataUnitLength);
        /*
         * 解决在校验和为"23"时,数据缺失的问题
         * 接收程序中是根据某一字节的16进制字符串是否是"23"来判断是否是结束标志的,因此若校验和的值是"23",那么有效字符串将会缺少一个"23",在此进行判断
         * 如果从"应用数据单元"开始的数据的长度+2等于通过"应用数据单元长度标识"计算出来的值+"校验和"和"结束标识"的值,并且应用数据单元紧接着的两个字符是"23",那么就可以判断是上面所说的情况
         */
        if (judgeMessage.length() + 2 == appDataUnitLength * 2 + 6) {
            if (judgeMessage.substring(appDataUnitLength * 2, appDataUnitLength * 2 + 2).equals("23")) {
                message += "23";
            }
        }
//        System.out.println("new message:" + message);
        int endIdentifierStart = message.lastIndexOf("2323");  //寻找结束符"##"起始位置，16进制为"2323"
        int checkSumStart = endIdentifierStart - 2;  //计算校验和的起始位置，校验和占一个字节，16进制为2个字符
        String checkSumHex = message.substring(checkSumStart, endIdentifierStart);  //校验和16进制数据

        String checkSum = Number.getCheckSum(message.substring(4, message.length() - 6));
        if (!checkSum.equals(checkSumHex)) {
            System.out.println("原始checkSum:" + checkSumHex);
            System.out.println("计算校验和:" + checkSum);
            System.out.println("校验和不一致");
        }

        /*
         *                 —————————————————————————————————————
         * 应用数据单元格式：|类型标识|信息体数目|信息体1|···|信息体n|
         *                 —————————————————————————————————————
         * 应用数据单元内容可能为空，不为空时信息体可能为1~n个
         */
        String appDataUnitHex = "";  //应用数据单元
        String typeIdentifierHex = "";  //类型标识
        String infoObjectsNumberHex = "";  //信息体数目
        if (Offset.controlUnitEnd != checkSumStart) {  //应用数据单元不为空时
            appDataUnitHex = message.substring(Offset.controlUnitEnd, checkSumStart);  //应用数据单元16进制数据
            typeIdentifierHex = appDataUnitHex.substring(0, 2);  //类型标识16进制数据
            infoObjectsNumberHex = appDataUnitHex.substring(2, 4);  //信息体数目16进制数据
            TypeIdentifier typeIdentifier = TypeIdentifier.getTypeIdentifier(typeIdentifierHex);  //获取类型标识
            if (typeIdentifier != null) {
//                throw new ServiceException(501, ExceptionCode.TYPE_IDENTIFIER_501);
                dataUnit.dataUnitSeparation(sourceAddressHex, appDataUnitHex, typeIdentifier);  //解析应用数据单元内容
            } else {
                System.out.println("解析typeIdentifier没有与库中符合选项，为null");
            }
//            dataUnit.dataUnitSeparation(appDataUnitHex, typeIdentifier);  //解析应用数据单元内容
        }
        Message data = new Message(
                controlUnitHex,
                appDataUnitHex,
                checkSumHex,
                businessSerialNumberHex,
                protocolVersionNumberHex,
                timeLabelHex,
                dataUnit.getTimeMeaning(timeLabelHex),
                sourceAddressHex,
                destinationAddressHex,
                appDataUnitLengthHex,
//                String.valueOf(Integer.parseInt(appDataUnitLengthHex, 16)),
                String.valueOf(Number.getIntValue(appDataUnitLengthHex)),
                commandByteHex,
                ControlUnitCommandByteType.getContent(commandByteHex),
                typeIdentifierHex,
                infoObjectsNumberHex);
        System.out.println(data.toString());
        System.out.println();
    }
}
