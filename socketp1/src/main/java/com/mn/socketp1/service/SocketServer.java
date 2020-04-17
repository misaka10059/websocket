package com.mn.socketp1.service;

import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/25 11:13
 * DESC socket监听
 */
@Component
public class SocketServer {

    private DataParsing dataParsing = new DataParsing();

    private ServerSocket server;

    private int port = 9301;

    /**
     * DATE 2020/3/25 11:41
     * DESC
     */
    public SocketServer() {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * DATE 2020/3/25 11:41
     * DESC
     */
    public void talk() {
        System.out.println("监控端口：" + port);
        Socket socket = null;
        boolean start1 = false;  //当前字节是否表示为启动字符"@"，false为不是
        boolean start2 = false;  //当前字节是否表示为启动字符"@"，当start1和start2都为true时，表示接收到有效数据
        boolean end = false;  //当前字节是否表示为启动字符"#"，false为不是
        while (true) {
            try {
                socket = server.accept();  // 阻塞等待，每接收到一个请求就创建一个新的连接实例
                System.out.println("连接客户端地址：" + socket.getRemoteSocketAddress());
                BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());  // 装饰流BufferedReader封装输入流（接收客户端的流）
                DataInputStream dis = new DataInputStream(bis);
                byte[] bytes = new byte[1]; // 一次读取一个byte
                String ret = "";
                while (dis.read(bytes) != -1) {  //-1表示没有数据
                    if (start1 && start2) {  //确认接收到"@@"启动标识
                        ret += bytesToHexString(bytes);  //将字节转换成16进制拼接到ret字符串
                        if (end && Objects.equals(bytesToHexString(bytes), "23")) {  //第二个结束标识置true
                            ret = ret.substring(0, ret.length() - 4) + "2323";
                            doSomething(ret);  //将结尾16进制表示的"##"替换为字符串"##"
                            dataParsing.parsing(ret);
                            ret = "";
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
                        ret += "4040";
                    }
                    if (!start1 && Objects.equals(bytesToHexString(bytes), "40")) {  //第一个启动标识置true
                        start1 = true;
                    }
                    /*if (dis.available() == 0) { //一个请求
//                        doSomething(ret);
                    }*/
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    assert socket != null;
                    socket.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * DATE 2020/3/25 11:41
     * DESC
     */
    private static void doSomething(String ret) {
        System.out.println(ret);
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

    /*
     * DATE 2020/3/25 11:41
     * DESC
     */
    /*public static String BytesHexString(byte[] b) {
        StringBuilder ret = new StringBuilder();
        for (byte aB : b) {
            String hex = Integer.toHexString(aB & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret.append(hex.toUpperCase());
        }
        return ret.toString();
    }*/


    /**
     * DATE 2020/3/25 11:41
     * DESC
     */
    public static void main(String[] args) {
        SocketServer server = new SocketServer();
        server.talk();
    }
}
