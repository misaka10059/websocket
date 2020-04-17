package com.mn.websocket.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/25 8:56
 * DESC
 */
public class ServerE {
    //服务器
    public static void testServer() {
        //创建一个服务器
        System.out.println("等待客户端连接。。。");
//        PrintWriter pwtoclien = null;
//        Scanner keybordscanner = null;
        Scanner inScanner = null;
        ServerSocket ss = null;
        while (true) {
            try {
                System.out.println(1);
                ss = new ServerSocket(9302);
                //创建一个接收连接客户端的对象
                Socket socket = ss.accept();
                System.out.println(socket.getInetAddress() + "已成功连接到此台服务器上。");
                //字符输出流
//                pwtoclien = new PrintWriter(socket.getOutputStream());
//                pwtoclien.println("已成功连接到远程服务器！" + "\t" + "请您先发言。");
//                pwtoclien.flush();
//                keybordscanner = new Scanner(System.in);
                inScanner = new Scanner(socket.getInputStream());
                //阻塞等待客户端发送消息过来
                while (true) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GBK"));
                    StringBuilder sb = new StringBuilder();
                    String temp;
//                    int index;
                    while (!(temp = br.readLine()).equalsIgnoreCase("##")) {
                        System.out.println("temp:" + temp);
                        int index = temp.indexOf("@@");
                        int end = temp.indexOf("##");
                        if (index != -1 && end != -1) {//遇到eof时就结束接收
                            sb.append(temp.substring(index, end + 2));
                            break;
                        }
                        sb.append(temp);
                    }
                    System.out.println("客户端:" + sb);
                }
                /*while (inScanner.hasNext()) {
                    String indata = inScanner.next();
                    System.out.println("客户端：" + indata);
//                System.out.print("我(服务端)：");
//                String keyborddata = keybordscanner.nextLine();
//                System.out.println("我(服务端)："+keyborddata);
//                pwtoclien.println(keyborddata);
//                pwtoclien.flush();
                }*/

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
//                pwtoclien.close();
//                keybordscanner.close();
                inScanner.close();
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        testServer();
    }
}
