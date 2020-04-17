package com.mn.socketp1.domain.dto.protocol;

import com.mn.socketp1.common.Number;
import com.mn.socketp1.domain.dto.protocol.infocontent.*;
import com.mn.socketp1.domain.dto.protocol.infocontent.kn.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 9:40
 * DESC
 */
@Component
public class AppDataUnit {

    public static void main(String[] args) {


    }

    /**
     * DATE 2020/3/27 15:43
     * DESC 16进制转2进制有符号数转10进制数
     */
    private int getIntValue(String hexStr) {
        if (hexStr.length() != 4) {
//            throw new ServiceException(502, ExceptionCode.HEX_STR_502);
            System.out.println("字符串不是两字节");
            return 0;
        }
        String hexStringL = Number.HexStringToBinString(hexStr.substring(0, 2)); //低字节
        String hexStringH = Number.HexStringToBinString(hexStr.substring(2));  //高字节
        String s = hexStringH + hexStringL;
        System.out.println("s:" + s);
        int value = 0;
        switch (s.substring(0, 1)) {
            case "0":
                value = Integer.parseInt(s, 2);
                break;
            case "1":
                value = -(Integer.parseInt(s, 2) & 0x7fff);
                break;
        }
        return value;
    }

    /**
     * DATE 2020/3/27 14:08
     * DESC 解析GB 18030-2005编码的16进制字符串为中文
     */
    private String getChineseString(String hexStr) {
        byte[] baKeyword = new byte[hexStr.length() / 2];
        String chineseStr = "";
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(hexStr.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            chineseStr = new String(baKeyword, "GB18030");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return chineseStr;
    }

    /**
     * DATE 2020/3/27 10:34
     * DESC 解析系统状态
     */
    private void getSystemStateMeaning(String hexStr) {
        if (hexStr.length() != 4) {
//            throw new ServiceException(502, ExceptionCode.HEX_STR_502);
            System.out.println("字符串不是两字节");
            return;
        }
        List<String> list = new ArrayList<>();
        String hexStr1 = hexStr.substring(0, 2);  //低位字节
        System.out.println("hexStr1:" + hexStr1);
        String hexStr2 = hexStr.substring(2);  //高位字节
        System.out.println("hexStr2:" + hexStr2);
        String binStr1 = Number.HexStringToBinString(hexStr1);
        System.out.println("binStr1:" + binStr1);
        String binStr2 = Number.HexStringToBinString(hexStr2);
        System.out.println("binStr2:" + binStr2);

        for (int i = 0; i < binStr1.length(); i++) {
            list.add(SystemState.getMeaning("L", i, binStr1.substring(binStr1.length() - 1 - i, binStr1.length() - i)));
        }
        for (int i = 0; i < binStr2.length(); i++) {
            list.add(SystemState.getMeaning("H", i, binStr2.substring(binStr2.length() - 1 - i, binStr2.length() - i)));
        }
        appPrint(list);
    }

    /**
     * DATE 2020/3/27 10:43
     * DESC 解析部件状态
     */
    private void getComponentStateMeaning(String hexStr) {
        if (hexStr.length() != 4) {
//            throw new ServiceException(502, ExceptionCode.HEX_STR_502);
            System.out.println("字符串不是两字节");
            return;
        }
        List<String> list = new ArrayList<>();
        String hexStr1 = hexStr.substring(0, 2);  //低位字节
        System.out.println("hexStr1:" + hexStr1);
        String hexStr2 = hexStr.substring(2);  //高位字节
        System.out.println("hexStr2:" + hexStr2);
        String binStr1 = Number.HexStringToBinString(hexStr1);
        System.out.println("binStr1:" + binStr1);
        String binStr2 = Number.HexStringToBinString(hexStr2);
        System.out.println("binStr2:" + binStr2);
        for (int i = 0; i < binStr1.length(); i++) {
            list.add(ComponentState.getMeaning("L", i, binStr1.substring(binStr1.length() - 1 - i, binStr1.length() - i)));
        }
        for (int i = 0; i < binStr2.length(); i++) {
            list.add(ComponentState.getMeaning("H", i, binStr2.substring(binStr2.length() - 1 - i, binStr2.length() - i)));
        }
        appPrint(list);
    }

    /**
     * DATE 2020/3/27 10:58
     * DESC 解析时间
     */
    public String getTimeMeaning(String timeLabelHex) {
        int sec = Integer.parseInt(timeLabelHex.substring(0, 2), 16);
        sec = judgeTimeRange(sec, 0, 59, "秒");
        int min = Integer.parseInt(timeLabelHex.substring(2, 4), 16);
        min = judgeTimeRange(min, 0, 59, "分");
        int hour = Integer.parseInt(timeLabelHex.substring(4, 6), 16);
        hour = judgeTimeRange(hour, 0, 23, "时");
        int day = Integer.parseInt(timeLabelHex.substring(6, 8), 16);
        day = judgeTimeRange(day, 1, 31, "日");
        int month = Integer.parseInt(timeLabelHex.substring(8, 10), 16);
        month = judgeTimeRange(month, 1, 12, "月");
        int year = Integer.parseInt(timeLabelHex.substring(10, 12), 16);
        year = judgeTimeRange(year, 0, 99, "年");
        return year + "年" + month + "月" + day + "日" + hour + "时" + min + "分" + sec + "秒";
    }

    /**
     * DATE 2020/4/17 9:37
     * DESC 判断timeValue代表的值是否超出范围
     * timeValue为需要判断的时间值，可以是秒、时、分、日、月、年
     * min表示最小的值、max表示最大的值 举例：如果传入的是秒，min为0、max为59，则表示timeValue不能小于0也不能大于59
     * name表示传入值的名称，如"秒"、"时"、"分"、"日"、"月"、"年"
     * 超出范围返回-1
     * 否则返回原值
     */
    private int judgeTimeRange(int timeValue, int min, int max, String name) {
        if (timeValue < min || timeValue > max) {
            System.out.println(name + "，时间范围错误");
            return -1;
        }
        return timeValue;
    }

    /**
     * DATE 2020/3/30 18:16
     * DESC 解析消防设备操作标识
     */
    private void getBuildingOperationIdentifierMeaning(String operationIdentifierHex) {
        String binStr = Number.HexStringToBinString(operationIdentifierHex);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < binStr.length(); i++) {
            list.add(BuildingOperationIdentifier.getMeaning(i, binStr.substring(binStr.length() - 1 - i, binStr.length() - i)));
        }
        appPrint(list);
    }

    /**
     * DATE 2020/3/31 9:38
     * DESC 解析用户信息传输装置操作标识
     */
    private void getUserOperationIdentifierMeaning(String operationIdentifierHex) {
        String binStr = Number.HexStringToBinString(operationIdentifierHex);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < binStr.length(); i++) {
            list.add(UserOperationIdentifier.getMeaning(i, binStr.substring(binStr.length() - 1 - i, binStr.length() - i)));
        }
        appPrint(list);
    }

    /**
     * DATE 2020/3/30 18:43
     * DESC 解析运行状态
     */
    private void getRunningState(String runningStateHex) {
        String binStr = Number.HexStringToBinString(runningStateHex);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < binStr.length(); i++) {
            list.add(RunningState.getMeaning(i, binStr.substring(binStr.length() - 1 - i, binStr.length() - i)));
        }
        appPrint(list);
    }

    /*
     * DATE 2020/3/31 10:03
     * DESC
     */
    /*private List<BuildingNormal> getBuildNormal(String dataUnit, int length, int number) {
        List<BuildingNormal> buildingNormalList = new ArrayList<>();
        System.out.println("AppDataUnit-171-BuildingNormal:" + length);
        if (length == 2) {
            for (int i = 0; i < number; i++) {
                String systemType = dataUnit.substring(4 + i * length * 2, 6 + i * length * 2);
                String systemAddress = dataUnit.substring(6 + i * length * 2, 8 + i * length * 2);
                String systemTypeMeaning = SystemType.getMeaning(systemType);
                buildingNormalList.add(
                        new BuildingNormal(
                                systemType,
                                systemTypeMeaning,
                                systemAddress));
            }
        } else {
            for (int i = 0; i < number; i++) {
                String systemType = dataUnit.substring(4 + i * length * 2, 6 + i * length * 2);
                String systemAddress = dataUnit.substring(6 + i * length * 2, 8 + i * length * 2);
                String componentAddress = dataUnit.substring(8 + i * length * 2, 16 + i * length * 2);
                String systemTypeMeaning = SystemType.getMeaning(systemType);
                buildingNormalList.add(
                        new BuildingNormal(
                                systemType,
                                systemTypeMeaning,
                                systemAddress,
                                componentAddress));
            }
        }
        return buildingNormalList;
    }*/

    /**
     * DATE 2020/3/25 10:59
     * DESC 解析应用数据单元内容
     */
    public void dataUnitSeparation(String dataUnitHex, TypeIdentifier typeIdentifier) {
        System.out.println("应用数据单元类型标识为:" + typeIdentifier.getContent() + " 16进制:" + typeIdentifier.getHex());
        String infoObjectsNumber = dataUnitHex.substring(2, 4);
        int length = typeIdentifier.getDataLength();  //此类型标识的信息体的单个信息体的字节数
        int number = Integer.parseInt(infoObjectsNumber, 16);  //信息体个数的10进制
        System.out.println("infoObjectsNumber(信息体数目):" + number);
        if (!typeIdentifier.getHex().equals("06") && !typeIdentifier.getHex().equals("1a") && 4 + number * length * 2 != dataUnitHex.length()) {
            System.out.println("计算长度与数据长度不一致");
        }
        switch (typeIdentifier.getHex()) {
            case "01":
                List<BuildingSystemState> buildingSystemStateList01 = new ArrayList<>();  //协议规定number不大于102
                for (int i = 0; i < number; i++) {
                    /*分割16进制字符串*/
                    String systemType01 = dataUnitHex.substring(4 + i * length * 2, 6 + i * length * 2);
                    String systemAddress01 = dataUnitHex.substring(6 + i * length * 2, 8 + i * length * 2);
                    String systemState01 = dataUnitHex.substring(8 + i * length * 2, 12 + i * length * 2);
                    String timeLabel01 = dataUnitHex.substring(12 + i * length * 2, 24 + i * length * 2);
                    /*获取中文含义*/
                    String systemTypeMeaning01 = SystemType.getMeaning(systemType01);
                    getSystemStateMeaning(systemState01);
                    String timeLabelMeaning01 = getTimeMeaning(timeLabel01);
                    buildingSystemStateList01.add(
                            new BuildingSystemState(
                                    systemType01,
                                    systemTypeMeaning01,
                                    systemAddress01,
                                    systemState01,
                                    timeLabel01,
                                    timeLabelMeaning01));
                }
                appPrint(buildingSystemStateList01);  //打印内容
                break;
            case "02":
                List<BuildingComponentState> buildingComponentStateList02 = new ArrayList<>();  //协议规定number不大于22
                for (int i = 0; i < number; i++) {
                    /*分割16进制字符串*/
                    String systemType02 = dataUnitHex.substring(4 + i * length * 2, 6 + i * length * 2);
                    String systemAddress02 = dataUnitHex.substring(6 + i * length * 2, 8 + i * length * 2);
                    String componentType02 = dataUnitHex.substring(8 + i * length * 2, 10 + i * length * 2);
                    String componentAddress02 = dataUnitHex.substring(10 + i * length * 2, 18 + i * length * 2);
                    String componentState02 = dataUnitHex.substring(18 + i * length * 2, 22 + i * length * 2);
                    String componentNote02 = dataUnitHex.substring(22 + i * length * 2, 84 + i * length * 2);
                    String timeLabel02 = dataUnitHex.substring(84 + i * length * 2, 96 + i * length * 2);
                    /*获取中文含义*/
                    String systemTypeMeaning02 = SystemType.getMeaning(systemType02);
                    String componentTypeMeaning02 = ComponentType.getMeaning(componentType02);
                    String componentNoteMeaning02 = getChineseString(componentNote02);
                    getComponentStateMeaning(componentState02);
                    String timeLabelMeaning02 = getTimeMeaning(timeLabel02);
                    buildingComponentStateList02.add(
                            new BuildingComponentState(
                                    systemType02,
                                    systemTypeMeaning02,
                                    systemAddress02,
                                    componentType02,
                                    componentTypeMeaning02,
                                    componentAddress02,
                                    componentState02,
                                    componentNote02,
                                    componentNoteMeaning02,
                                    timeLabel02,
                                    timeLabelMeaning02));
                }
                appPrint(buildingComponentStateList02);  //打印内容
                break;
            case "03":
                List<BuildingComponentAnalog> buildingComponentAnalogList03 = new ArrayList<>();  //协议规定number不大于63
                for (int i = 0; i < number; i++) {
                    /*分割16进制字符串*/
                    String systemType03 = dataUnitHex.substring(4 + i * length * 2, 6 + i * length * 2);
                    String systemAddress03 = dataUnitHex.substring(6 + i * length * 2, 8 + i * length * 2);
                    String componentType03 = dataUnitHex.substring(8 + i * length * 2, 10 + i * length * 2);
                    String componentAddress03 = dataUnitHex.substring(10 + i * length * 2, 18 + i * length * 2);
                    String analogType03 = dataUnitHex.substring(18 + i * length * 2, 20 + i * length * 2);
                    String analogValue03 = dataUnitHex.substring(20 + i * length * 2, 24 + i * length * 2);
                    String timeLabel03 = dataUnitHex.substring(24 + i * length * 2, 36 + i * length * 2);
                    /*获取中文含义*/
                    String systemTypeMeaning03 = SystemType.getMeaning(systemType03);
                    String componentTypeMeaning03 = ComponentType.getMeaning(componentType03);
                    String analogTypeMeaning03 = AnalogType.getMeaning(analogType03);
                    int analogValueMeaning03 = getIntValue(analogValue03);
                    String timeLabelMeaning03 = getTimeMeaning(timeLabel03);
                    buildingComponentAnalogList03.add(
                            new BuildingComponentAnalog(
                                    systemType03,
                                    systemTypeMeaning03,
                                    systemAddress03,
                                    componentType03,
                                    componentTypeMeaning03,
                                    componentAddress03,
                                    analogType03,
                                    analogTypeMeaning03,
                                    analogValue03,
                                    analogValueMeaning03,
                                    timeLabel03,
                                    timeLabelMeaning03));
                }
                appPrint(buildingComponentAnalogList03);
                break;
            case "04":
                /*int length04 = 3;
                for (int i = 0; i < number; i++) {
                    Integer.parseInt(dataUnitHex.substring(2+length04,2+length04+1))
                }*/
                List<BuildingOperationInfo> buildingOperationInfoList04 = new ArrayList<>();  //协议规定number不大于102
                for (int i = 0; i < number; i++) {
                    /*分割16进制字符串*/
                    String systemType04 = dataUnitHex.substring(4 + i * length * 2, 6 + i * length * 2);
                    String systemAddress04 = dataUnitHex.substring(6 + i * length * 2, 8 + i * length * 2);
                    String operationIdentifier04 = dataUnitHex.substring(8 + i * length * 2, 10 + i * length * 2);
                    String operatorNumber04 = dataUnitHex.substring(10 + i * length * 2, 12 + i * length * 2);
                    String timeLabel04 = dataUnitHex.substring(12 + i * length * 2, 24 + i * length * 2);
                    /*获取中文含义*/
                    String systemTypeMeaning04 = SystemType.getMeaning(systemType04);
                    getBuildingOperationIdentifierMeaning(operationIdentifier04);
                    String timeLabelMeaning04 = getTimeMeaning(timeLabel04);
                    buildingOperationInfoList04.add(
                            new BuildingOperationInfo(
                                    systemType04,
                                    systemTypeMeaning04,
                                    systemAddress04,
                                    operationIdentifier04,
                                    operatorNumber04,
                                    timeLabel04,
                                    timeLabelMeaning04));
                }
                appPrint(buildingOperationInfoList04);
                break;
            case "05":
                /*分割16进制字符串*/
                String systemType05 = dataUnitHex.substring(4, 6);
                String systemAddress05 = dataUnitHex.substring(6, 8);
                String softwareMainVersionNumber05 = dataUnitHex.substring(8, 10);
                String softwareSecondVersionNumber05 = dataUnitHex.substring(10, 12);
                /*获取中文含义*/
                String systemTypeMeaning05 = SystemType.getMeaning(systemType05);
                BuildingSoftwareVersion buildingSoftwareVersion05 = new BuildingSoftwareVersion(
                        systemType05,
                        systemTypeMeaning05,
                        systemAddress05,
                        softwareMainVersionNumber05,
                        softwareSecondVersionNumber05);
                appPrint(buildingSoftwareVersion05);
                break;
            case "06":
                List<BuildingSystemConfig> buildingSystemConfigList06 = new ArrayList<>();  //协议规定number不大于3
                int dataStart = 4;
                for (int i = 0; i < number; i++) {
                    /*分割16进制字符串*/
                    String systemType06 = dataUnitHex.substring(dataStart, dataStart + 2);
                    String systemAddress06 = dataUnitHex.substring(dataStart + 2, dataStart + 4);
                    String systemDescriptionLength06 = dataUnitHex.substring(dataStart + 4, dataStart + 6);
                    String systemDescription06 = dataUnitHex.substring(dataStart + 6, dataStart + 6 + 2 * Integer.parseInt(systemDescriptionLength06, 16));
                    /*获取中文含义*/
                    String systemTypeMeaning06 = SystemType.getMeaning(systemType06);
                    String systemDescriptionMeaning06 = "";
                    if (systemDescription06.length() != 0) {
                        getChineseString(systemDescription06);
                    }
                    buildingSystemConfigList06.add(
                            new BuildingSystemConfig(
                                    systemType06,
                                    systemTypeMeaning06,
                                    systemAddress06,
                                    systemDescriptionLength06,
                                    systemDescription06,
                                    systemDescriptionMeaning06));
                    dataStart = dataStart + 6 + Integer.parseInt(systemDescriptionLength06, 16) * 2;
                }
                appPrint(buildingSystemConfigList06);
                break;
            case "07":
                List<BuildingComponentConfig> buildingComponentConfigList07 = new ArrayList<>();  //协议规定number不大于26
                for (int i = 0; i < number; i++) {
                    /*分割16进制字符串*/
                    String systemType07 = dataUnitHex.substring(4 + i * length * 2, 6 + i * length * 2);
                    String systemAddress07 = dataUnitHex.substring(6 + i * length * 2, 8 + i * length * 2);
                    String componentType07 = dataUnitHex.substring(8 + i * length * 2, 10 + i * length * 2);
                    String componentAddress07 = dataUnitHex.substring(10 + i * length * 2, 18 + i * length * 2);
                    String componentNote07 = dataUnitHex.substring(18 + i * length * 2, 80 + i * length * 2);
                    /*获取中文含义*/
                    String systemTypeMeaning07 = SystemType.getMeaning(systemType07);
                    String componentTypeMeaning07 = ComponentType.getMeaning(componentType07);
                    String componentNoteMeaning07 = getChineseString(componentNote07);
                    buildingComponentConfigList07.add(
                            new BuildingComponentConfig(
                                    systemType07,
                                    systemTypeMeaning07,
                                    systemAddress07,
                                    componentType07,
                                    componentTypeMeaning07,
                                    componentAddress07,
                                    componentNote07,
                                    componentNoteMeaning07));
                }
                appPrint(buildingComponentConfigList07);
                break;
            case "08":
                /*分割16进制字符串*/
                String systemType08 = dataUnitHex.substring(4, 6);
                String systemAddress08 = dataUnitHex.substring(6, 8);
                String timeLabel08 = dataUnitHex.substring(8, 20);
                /*获取中文含义*/
                String systemTypeMeaning08 = SystemType.getMeaning(systemType08);
                String timeLabelMeaning08 = getTimeMeaning(timeLabel08);
                BuildingSystemTime buildingSystemTime08 = new BuildingSystemTime(
                        systemType08,
                        systemTypeMeaning08,
                        systemAddress08,
                        timeLabel08,
                        timeLabelMeaning08);
                appPrint(buildingSystemTime08);
                break;
            case "15":
                /*分割16进制字符串*/
                String runningState15 = dataUnitHex.substring(4, 6);
                String stateOccurrenceTime15 = dataUnitHex.substring(6, 18);
                /*获取中文含义*/
                getRunningState(runningState15);
                String stateOccurrenceTimeMeaning15 = getTimeMeaning(stateOccurrenceTime15);
                UserRunningState userRunningState15 = new UserRunningState(
                        runningState15,
                        stateOccurrenceTime15,
                        stateOccurrenceTimeMeaning15);
                appPrint(userRunningState15);
                break;
            case "18":
                /*分割16进制字符串*/
                List<UserOperationInfo> userOperationInfoList18 = new ArrayList<>();  //协议规定number不大于127
                for (int i = 0; i < number; i++) {
                    String operationInfo18 = dataUnitHex.substring(4 + i * length * 2, 6 + i * length * 2);
                    String operatorNumber18 = dataUnitHex.substring(6 + i * length * 2, 8 + i * length * 2);
                    String operationTime18 = dataUnitHex.substring(8 + i * length * 2, 20 + i * length * 2);
                    /*获取中文含义*/
                    getUserOperationIdentifierMeaning(operationInfo18);
                    String operationTimeMeaning18 = getTimeMeaning(operationTime18);
                    userOperationInfoList18.add(
                            new UserOperationInfo(
                                    operationInfo18,
                                    operatorNumber18,
                                    operationTime18,
                                    operationTimeMeaning18));
                }
                appPrint(userOperationInfoList18);
                break;
            case "19":
                /*分割16进制字符串*/
                String mainSoftwareVersion19 = dataUnitHex.substring(4, 6);
                String secondSoftwareVersion19 = dataUnitHex.substring(6, 8);
                UserSoftwareVersion userSoftwareVersion19 = new UserSoftwareVersion(
                        mainSoftwareVersion19,
                        secondSoftwareVersion19);
                appPrint(userSoftwareVersion19);
                break;
            case "1a":
                /*分割16进制字符串*/
                String configNoteLength1a = dataUnitHex.substring(4, 6);
                String configNote1a = dataUnitHex.substring(6);
                /*获取中文含义*/
                String configNoteLengthMeaning1a = String.valueOf(Integer.parseInt(configNoteLength1a, 16));
                String configNoteMeaning1a = "";
                if (configNote1a.length() != 0) {
                    getChineseString(configNote1a);
                }
                UserConfig userConfig1a = new UserConfig(
                        configNoteLength1a,
                        configNoteLengthMeaning1a,
                        configNote1a,
                        configNoteMeaning1a);
                appPrint(userConfig1a);
                break;
            case "1c":
                /*分割16进制字符串*/
                String systemTime1c = dataUnitHex.substring(4, 16);
                /*获取中文含义*/
                String systemTimeMeaning1c = getTimeMeaning(systemTime1c);
                UserSystemTime userSystemTime1c = new UserSystemTime(systemTime1c, systemTimeMeaning1c);
                appPrint(userSystemTime1c);
                break;
            /*----------------------------------------------------------------------------------------*/
            /*
             * 以下为下行数据，即由监控中心向用户传输装置发送数据用以查询
             */
            /*case "3d":
                System.out.println("应用数据单元数据类型为3d");
                List<BuildingNormal> buildingNormalList3d = getBuildNormal(dataUnit, length, number);

                appPrint(buildingNormalList3d);
                break;
            case "3e":
                System.out.println("应用数据单元数据类型为3e");
                List<BuildingNormal> buildingNormalList3e = getBuildNormal(dataUnit, length, number);

                appPrint(buildingNormalList3e);
                break;
            case "3f":
                System.out.println("应用数据单元数据类型为3f");
                List<BuildingNormal> buildingNormalList3f = getBuildNormal(dataUnit, length, number);

                appPrint(buildingNormalList3f);
                break;
            case "40":
                System.out.println("应用数据单元数据类型为40");
                String systemType40 = dataUnit.substring(4, 6);
                String systemAddress40 = dataUnit.substring(6, 8);
                String operationInfoCount40 = dataUnit.substring(8, 10);
                String startTime40 = dataUnit.substring(10, 22);

                String systemTypeMeaning40 = SystemType.getMeaning(startTime40);
                String operationInfoCountMeaning40 = String.valueOf(Integer.parseInt(operationInfoCount40, 16));
                System.out.println("-----------输出应用数据单元数据-----------");
                System.out.println(systemType40);
                System.out.println(systemAddress40);
                System.out.println(operationInfoCount40);
                System.out.println(startTime40);
                System.out.println("-----------输出应用数据单元数据-----------");
                break;
            case "41":
                System.out.println("应用数据单元数据类型为41");
                List<BuildingSoftwareVersion> buildingSoftwareVersionList41 = new ArrayList<>();
                for (int i = 0; i < number; i++) {
                    String systemType41 = dataUnit.substring(4 + i * length * 2, 6 + i * length * 2);
                    String systemAddress41 = dataUnit.substring(6 + i * length * 2, 8 + i * length * 2);
                    buildingSoftwareVersionList41.add(new BuildingSoftwareVersion(systemType41, systemAddress41));
                }
                appPrint(buildingSoftwareVersionList41);
                break;
            case "42":
                System.out.println("应用数据单元数据类型为42");
                List<BuildingNormal> buildingNormalList42 = new ArrayList<>();
                for (int i = 0; i < number; i++) {
                    String systemType42 = dataUnit.substring(4 + i * length * 2, 6 + i * length * 2);
                    String systemAddress42 = dataUnit.substring(6 + i * length * 2, 8 + i * length * 2);
                    buildingNormalList42.add(new BuildingNormal(systemType42, systemAddress42));
                }
                appPrint(buildingNormalList42);
                break;
            case "43":
                System.out.println("应用数据单元数据类型为43");
                List<BuildingNormal> buildingNormalList43 = new ArrayList<>();
                for (int i = 0; i < number; i++) {
                    String systemType43 = dataUnit.substring(4 + i * length * 2, 6 + i * length * 2);
                    String systemAddress43 = dataUnit.substring(6 + i * length * 2, 8 + i * length * 2);
                    String componentAddress43 = dataUnit.substring(8 + i * length * 2, 16 + i * length * 2);
                    buildingNormalList43.add(new BuildingNormal(systemType43, systemAddress43, componentAddress43));
                }
                appPrint(buildingNormalList43);
                break;
            case "44":
                System.out.println("应用数据单元数据类型为44");
                String systemType44 = dataUnit.substring(4, 6);
                String systemAddress44 = dataUnit.substring(6, 8);
                BuildingNormal buildingNormal44 = new BuildingNormal(systemType44, systemAddress44);
                appPrint(buildingNormal44);
                break;
            case "51":
                System.out.println("应用数据单元数据类型为51");
                String reserved51 = dataUnit.substring(4);
                appPrint(reserved51);
                break;
            case "54":
                System.out.println("应用数据单元数据类型为54");
                String operationInfoCount54 = dataUnit.substring(4, 6);
                String startTime54 = dataUnit.substring(6, 18);
                System.out.println("-----------输出应用数据单元数据-----------");
                System.out.println(operationInfoCount54);
                System.out.println(startTime54);
                System.out.println("-----------输出应用数据单元数据-----------");
                break;
            case "55":
                System.out.println("应用数据单元数据类型为55");
                String reserved55 = dataUnit.substring(4);
                appPrint(reserved55);
                break;
            case "56":
                System.out.println("应用数据单元数据类型为56");
                String reserved56 = dataUnit.substring(4);
                appPrint(reserved56);
                break;
            case "58":
                System.out.println("应用数据单元数据类型为58");
                String reserved58 = dataUnit.substring(4);
                appPrint(reserved58);
                break;
            case "59":
                System.out.println("应用数据单元数据类型为59");
                String reserved59 = dataUnit.substring(4);
                appPrint(reserved59);
                break;
            case "5a":
                System.out.println("应用数据单元数据类型为5a");
                String systemTime5a = dataUnit.substring(4, 16);
                UserSystemTime userSystemTime5a = new UserSystemTime(systemTime5a);
                appPrint(userSystemTime5a);
                break;
            case "5b":
                System.out.println("应用数据单元数据类型为5b");
                String inspectSentries5b = dataUnit.substring(4, 6);
                appPrint(inspectSentries5b);
                break;*/
            default:
                System.out.println("typeIdentifier表示的内容与已设置类型不符");
        }
    }

    private <T> void appPrint(List<T> tList) {
        System.out.println("-----------输出应用数据单元数据-----------");
        for (T t : tList) {
            System.out.println(t.toString());
        }
        System.out.println("-----------输出应用数据单元数据-----------");
    }

    private <T> void appPrint(T t) {
        System.out.println("-----------输出应用数据单元数据-----------");
        System.out.println(t.toString());
        System.out.println("-----------输出应用数据单元数据-----------");
    }
}
