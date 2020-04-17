package com.mn.websocket.main;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/25 10:14
 * DESC
 */
public class ServerF {
    private ServerSocket server;
    private int port = 9302;

    public ServerF() {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
        }
    }

    public void talk() {
        System.out.println("监控端口：" + port);
        Socket socket = null;
        boolean start1 = false;
        boolean start2 = false;
        boolean end1 = false;
        boolean end2 = false;
        while (true) {
            try {
                // 阻塞等待，每接收到一个请求就创建一个新的连接实例
                socket = server.accept();
                System.out.println("连接客户端地址：" + socket.getRemoteSocketAddress());

                // 装饰流BufferedReader封装输入流（接收客户端的流）
                BufferedInputStream bis = new BufferedInputStream(
                        socket.getInputStream());

                DataInputStream dis = new DataInputStream(bis);
                byte[] bytes = new byte[1]; // 一次读取一个byte
                String ret = "";
                while (dis.read(bytes) != -1) {
                    System.out.println("hex:" + bytesToHexString(bytes));
                    if (start1 && start2) {
                        ret += bytesToHexString(bytes);
                        if (end1 && bytesToHexString(bytes).equals("23")) {
                            doSomething(ret + "##");
                            ret = "";
                            start1 = false;
                            start2 = false;
                            end1 = false;
                            continue;
                        }
                        if (!end1 && bytesToHexString(bytes).equals("23")) {
                            end1 = true;
                        }
                    }
                    if (start1 && !start2 && bytesToHexString(bytes).equals("40")) {
                        start2 = true;
                        ret += "@@";
                    }
                    if (!start1 && bytesToHexString(bytes).equals("40")) {
                        start1 = true;
                    }
                    if (dis.available() == 0) { //一个请求
                        doSomething(ret);
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    public static void doSomething(String ret) {
        System.out.println(ret);
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String BytesHexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    public static void main(String[] args) {
        ServerF server = new ServerF();
        server.talk();
    }
}
