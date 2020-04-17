package com.mn.socketp1.service;

import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/25 11:13
 * DESC socket监听
 */
@Service
public class SocketServerThread extends Thread {

    private DataParsing dataParsing = new DataParsing();

    private Socket socket = null;

    public SocketServerThread() {
    }

    /**
     * DATE 2020/4/8 11:38
     * DESC
     */
    public SocketServerThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * DATE 2020/4/8 11:43
     * DESC 替换之前程序为线程启动
     */
    @Override
    public void run() {
        boolean start1 = false;  //当前字节是否表示为启动字符"@"，false为不是
        boolean start2 = false;  //当前字节是否表示为启动字符"@"，当start1和start2都为true时，表示接收到有效数据
        boolean end = false;  //当前字节是否表示为启动字符"#"，false为不是
        System.out.println("连接客户端地址：" + socket.getRemoteSocketAddress());
        System.out.println("连接时间："+ LocalDateTime.now());
        try {
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());  // 装饰流BufferedReader封装输入流（接收客户端的流）
            DataInputStream dis = new DataInputStream(bis);
            byte[] bytes = new byte[1]; // 一次读取一个byte
            StringBuilder ret = new StringBuilder();
            while (dis.read(bytes) != -1) {  //-1表示没有数据
                if (start1 && start2) {  //确认接收到"@@"启动标识
                    ret.append(bytesToHexString(bytes));  //将字节转换成16进制拼接到ret字符串
                    if (end && Objects.equals(bytesToHexString(bytes), "23")) {  //第二个结束标识置true
                        ret = new StringBuilder(ret.substring(0, ret.length() - 4) + "2323");  //将结尾16进制表示的"##"替换为字符串"##"
                        dataParsing.parsing(ret.toString());
                        ret = new StringBuilder();
                        start1 = false;
                        start2 = false;
                        end = false;
                        continue;
                    }
                    if (!end && Objects.equals(bytesToHexString(bytes), "23")) {  //第一个结束标识置true
                        end = true;
                    }
                }
                if (start1 && !start2 && Objects.equals(bytesToHexString(bytes), "40")) {  //第二个启动标识置true，输出字符串添加上"@@"
                    start2 = true;
                    ret.append("4040");
                }
                if (!start1 && Objects.equals(bytesToHexString(bytes), "40")) {  //第一个启动标识置true
                    start1 = true;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (socket != null) {
                    System.out.println(socket.getRemoteSocketAddress() + "关闭连接");
                    System.out.println("关闭时间："+ LocalDateTime.now());
                    socket.close();
                }
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
}
