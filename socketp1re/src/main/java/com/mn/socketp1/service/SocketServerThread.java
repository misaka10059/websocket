package com.mn.socketp1.service;

import com.mn.socketp1.common.Number;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/25 11:13
 * DESC socket监听
 */
@Service
public class SocketServerThread implements Runnable {

    @Resource
    private DataParsing dataParsing;

    private Socket socket;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * DATE 2020/4/8 11:43
     * DESC 替换执行方式为线程
     */
    @SneakyThrows
    @Override
    public void run() {
        boolean start1 = false;  //当前字节是否表示为启动字符"@"，false为不是
        boolean start2 = false;  //当前字节是否表示为启动字符"@"，当start1和start2都为true时，表示接收到有效数据
        boolean end = false;  //当前字节是否表示为启动字符"#"，false为不是
        System.out.println("连接客户端地址：" + socket.getRemoteSocketAddress());
        System.out.println("连接时间：" + LocalDateTime.now());
        try {
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());  // 装饰流BufferedReader封装输入流（接收客户端的流）
            DataInputStream dis = new DataInputStream(bis);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            byte[] bytes = new byte[1]; // 一次读取一个byte
            StringBuilder ret = new StringBuilder();
            while (dis.read(bytes) != -1) {  //-1表示没有数据
                if (start1 && start2) {  //确认接收到"@@"启动标识
                    ret.append(bytesToHexString(bytes));  //将字节转换成16进制拼接到ret字符串
                    if (Objects.equals(bytesToHexString(bytes), "23")) {
                        System.out.println("-----结束字符：" + Arrays.toString(bytes));
                    }
                    if (end && Objects.equals(bytesToHexString(bytes), "23") && ret.length() > 8) {  //第二个结束标识置true
                        ret = new StringBuilder(ret.substring(0, ret.length() - 4) + "2323");  //将结尾16进制表示的"##"替换为字符串"##"
                        System.out.println("客户端地址：" + socket.getRemoteSocketAddress());
                        dataParsing.parsing(ret.toString());
                        /*
                         * 确认
                         */
                        String message = ret.toString();
                        String cUnit = message.substring(4, 24) + message.substring(36, 48) + message.substring(24, 36) + "000003";
                        String checksum = Number.getCheckSum(cUnit);
                        String returnMessage = "4040" + message.substring(4, 24) + message.substring(36, 48) + message.substring(24, 36) + "000003" + checksum + "2323";
//                        byte[] confirmation = {64, 64, 1, 0, 1, 1, 35, 58, 9, 26, 9, 12, 56, 91, 1, 0, 0, 0, (byte) 121, 3, 0, 0, 0, 0, 0, 0, 3, (byte) 171, 35, 35};
                        byte[] confirmation = hexToByteArray(returnMessage);
                        System.out.println("确认数据：" + Arrays.toString(confirmation));
                        /*byte[] confirmation = new byte[30];
                        confirmation[0] = 40;
                        confirmation[1] = 40;*/
                        /*
                         * 否认
                         */
                        byte[] deny = {64, 64, 1, 0, 1, 1, 35, 58, 9, 26, 9, 12, 56, 91, 1, 0, 0, 0, (byte) 121, 3, 0, 0, 0, 0, 0, 0, 6, (byte) 171, 35, 35};

                        out.write(confirmation);
                        out.flush();

                        ret = new StringBuilder();
                        start1 = false;
                        start2 = false;
                        end = false;
                        continue;
                    }
                    if (!end && Objects.equals(bytesToHexString(bytes), "23") && ret.length() > 8) {  //第一个结束标识置true,业务流水号中可能会包含2323,因此增加长度判断
                        end = true;
                    }
                }
                if (start1 && Objects.equals(bytesToHexString(bytes), "40") && ret.length() < 4) {  //第二个启动标识置true，输出字符串添加上"@@"
                    start2 = true;
                    ret.append("4040");
                } else {
                    if (!start2)
                        start1 = false;
                }
                if (!start1 && Objects.equals(bytesToHexString(bytes), "40")) {  //第一个启动标识置true
                    start1 = true;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                System.out.println(socket.getRemoteSocketAddress() + "关闭连接");
                System.out.println("关闭时间：" + LocalDateTime.now());
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * DATE 2020/3/25 11:41
     * DESC
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    private static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }
}
