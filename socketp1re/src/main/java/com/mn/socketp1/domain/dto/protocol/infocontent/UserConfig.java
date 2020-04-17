package com.mn.socketp1.domain.dto.protocol.infocontent;

import lombok.Data;
import lombok.ToString;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 18:23
 * DESC 上传用户信息传输装置配置 协议8.3.1.12
 * 字节数为协议中该类信息所占字节数
 */
@Data
@ToString
public class UserConfig {
    private String configNoteLengthHex;  //配置说明长度  1字节
    private String configNoteLengthMeaning;
    private String configNoteHex;  //配置说明  L字节
    private String configNoteMeaning;

    public UserConfig(String configNoteLengthHex,
                      String configNoteLengthMeaning,
                      String configNoteHex,
                      String configNoteMeaning) {
        this.configNoteLengthHex = configNoteLengthHex;
        this.configNoteLengthMeaning = configNoteLengthMeaning;
        this.configNoteHex = configNoteHex;
        this.configNoteMeaning = configNoteMeaning;
    }
}
