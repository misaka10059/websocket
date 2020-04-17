package com.mn.socketp1.domain.dto.protocol.infocontent.kn;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 13:58
 * DESC 系统类型  协议8.2.1.1
 */
public enum SystemType {
    ST_01("通用", "00"),
    ST_02("火灾报警系统", "01"),
    ST_03("预留1", "02-09"),
    ST_04("消防联动控制器", "0a"),
    ST_05("消火栓系统", "0b"),
    ST_06("自动喷水灭火系统", "0c"),
    ST_07("气体灭火系统", "0d"),
    ST_08("水喷雾灭火系统(泵启动方式)", "0e"),
    ST_09("水喷雾灭火系统(压力容器启动方式)", "0f"),
    ST_10("泡沫灭火系统", "10"),
    ST_11("干粉灭火系统", "11"),
    ST_12("防烟排烟系统", "12"),
    ST_13("防火门及卷帘系统", "13"),
    ST_14("消防电梯", "14"),
    ST_15("消防应急广播", "15"),
    ST_16("消防应急照明和疏散指示系统", "16"),
    ST_17("消防电源", "17"),
    ST_18("消防电话", "18"),
    ST_19("预留2", "19-7f"),
    ST_20("用户自定义", "80-ff");

    private String meaning;
    private String hex;

    SystemType(String meaning, String hex) {
        this.meaning = meaning;  //含义
        this.hex = hex;  //协议中16进制的表示
    }

    public static String getMeaning(String hex) {
        for (SystemType systemType : SystemType.values()) {
            if (systemType.getHex().equals(hex)) {
                return systemType.getMeaning();
            }
        }
        return "hex指示的数据不存在";
    }

    public String getMeaning() {
        return meaning;
    }

    public void SetMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }
}
