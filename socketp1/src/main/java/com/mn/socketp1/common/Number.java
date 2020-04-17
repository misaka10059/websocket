package com.mn.socketp1.common;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/26 10:47
 * DESC
 */
public class Number {

    /**
     * DATE 2020/3/26 10:49
     * DESC 16进制字符串转2进制字符串，高位位数不足补零
     */
    public static String HexStringToBinString(String hexString) {
        int i = Integer.parseInt(hexString, 16);
        StringBuilder binStr = new StringBuilder(Integer.toBinaryString(i));
        System.out.println("binStr:" + binStr);
        while (binStr.length() < 8) {
            binStr.insert(0, "0");
        }
        System.out.println("binStr:" + binStr);
        return binStr.toString();
    }
}
