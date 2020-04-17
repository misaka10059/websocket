package com.mn.socketp1.domain.dto.protocol.infocontent;

import lombok.Data;
import lombok.ToString;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 18:16
 * DESC 用户信息传输装置软件版本 8.3.1.11
 * 字节数为协议中该类信息所占字节数
 */
@Data
@ToString
public class UserSoftwareVersion {
    private String mainSoftwareVersionHex;  //主版本号  1字节
    private String secondSoftwareVersionHex;  //次版本号  1字节

    public UserSoftwareVersion(String mainSoftwareVersionHex,
                               String secondSoftwareVersionHex) {
        this.mainSoftwareVersionHex = mainSoftwareVersionHex;
        this.secondSoftwareVersionHex = secondSoftwareVersionHex;
    }
}
