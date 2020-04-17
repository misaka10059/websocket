package com.mn.websocket.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * AUTHOR MisakaNetwork
 * DATE 2020/3/24 18:22
 * DESC
 */
public class HandlerThread {
    /*private Socket socket;

    public HandlerThread(Socket client) {
        socket = client;
        new Thread(this).start();
    }

    public void run() {
        try {
            // 读取客户端数据
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientInputStr = input.readLine();//这里要注意和客户端输出流的写方法对应,否则会抛 EOFException
            // 处理客户端数据
            System.out.println("客户端发过来的内容:" + clientInputStr);

            // 向客户端回复信息
//                PrintStream out = new PrintStream(socket.getOutputStream());
//                System.out.print("请输入:\t");
            // 发送键盘输入的一行
//                String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
//                out.println(s);

//                out.close();
            input.close();
        } catch (Exception e) {
            System.out.println("服务器 run 异常: " + e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    socket = null;
                    System.out.println("服务端 finally 异常:" + e.getMessage());
                }
            }
        }
    }*/
}
