package com.bearboy.network;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SimpleSocketClient {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Socket socket = new Socket("localhost", 8080);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String message = reader.readLine();
                System.out.println("接收到server 发送的消息：" + message);
                if ("bye".equals(message)) {
                    //接收到server端发送的bye后，关闭程序
                    break;
                }
                System.out.println("请输入数据要发送给server的数据:");
                String str = scanner.nextLine() + "\n";
                socket.getOutputStream().write(str.getBytes());
                socket.getOutputStream().flush();
                //如果控制台输入bye 退出程序
                if (str.contains("bye")){
                    break;
                }
            }
            socket.close();
            reader.close();
            scanner.close();
            System.out.println("client 关闭成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
