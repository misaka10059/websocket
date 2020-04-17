package com.mn.socketp1.config;

import lombok.Data;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/25 15:27
 * DESC
 */
@Data
public class Offset {
    public static final int startIdentifierStart = 0;
    public static final int startIdentifierEnd = 2*2;
    public static final int controlUnitStart = 2*2;
    public static final int controlUnitEnd = 27*2;
    //    public static final int appDataUnitStart = 27;
//    public static final int appDataUnitEnd = ;
//    public static final int checkSumStart = ;
//    public static final int checkSumEnd = ;
//    public static final int endIdentifierStart = ;
//    public static final int endIdentifierEnd = ;
    public static final int businessSerialNumberStart = 2*2;
    public static final int businessSerialNumberEnd = 4*2;
    public static final int protocolVersionNumberStart = 4*2;
    public static final int protocolVersionNumberEnd = 6*2;
    public static final int timeLabelStart = 6*2;
    public static final int timeLabelEnd = 12*2;
    public static final int sourceAddressStart = 12*2;
    public static final int sourceAddressEnd = 18*2;
    public static final int destinationAddressStart = 18*2;
    public static final int destinationAddressEnd = 24*2;
    public static final int appDataUnitLengthStart = 24*2;
    public static final int appDataUnitLengthEnd = 26*2;
    public static final int commandByteStart = 26*2;
    public static final int commandByteEnd = 27*2;
}
