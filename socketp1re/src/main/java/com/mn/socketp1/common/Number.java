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

    /**
     * DATE 2020/4/9 16:56
     * DESC 求指定字符串的算术校验和
     * 每8位二进制数相加求和，最终结果保留低8位二进制数
     */
    public static String getCheckSum(String checkString) {
        int checkSumInt = 0;
        for (int i = 0; i < checkString.length(); i = i + 2) {
            checkSumInt += Integer.parseInt(checkString.substring(i, i + 2), 16);  //求和
        }
        String checkSumBin = Integer.toBinaryString(checkSumInt); //转换成二进制字符串
        if (checkSumBin.length() > 8) {
            checkSumBin = checkSumBin.substring(checkSumBin.length() - 8);  //保留低8位
        }
        checkSumInt = Integer.parseInt(checkSumBin, 2);  //二进制字符串转10进制数
        String checkSumHex = Integer.toHexString(checkSumInt);  //10进制数转16进制字符串
        if (checkSumHex.length() < 2) {
            checkSumHex = "0" + checkSumHex;
        }
        return checkSumHex;
    }

    public static void main(String[] args) {
        /*String checkString = "010001ff001e0a0104140100000000000200000000000800021c01001e0a010414";
        System.out.println(getCheckSum(checkString));*/
    }
}
