package com.mn.socketp1.domain.dto.protocol.infocontent;

import lombok.Data;
import lombok.ToString;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 18:11
 * DESC 用户信息传输装置操作信息 协议8.3.1.10
 */
@Data
@ToString
public class UserOperationInfo {
    private String operationInfoHex;  //操作信息  1字节
    private String operatorNumberHex;  //操作员编号  1字节
    private String operationTimeHex;  //操作的记录时间  6字节
    private String operationTimeMeaning;

    public UserOperationInfo(String operationInfoHex,
                             String operatorNumberHex,
                             String operationTimeHex,
                             String operationTimeMeaning) {
        this.operationInfoHex = operationInfoHex;
        this.operatorNumberHex = operatorNumberHex;
        this.operationTimeHex = operationTimeHex;
        this.operationTimeMeaning = operationTimeMeaning;
    }
}
