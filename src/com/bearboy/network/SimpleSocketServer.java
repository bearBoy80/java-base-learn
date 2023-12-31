package com.bearboy.network;

import sun.nio.ch.sctp.SctpNet;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleSocketServer {
    public static void main(String[] args) {
        try {
            InetSocketAddress address = new InetSocketAddress("localhost",8080);
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(address);
            Socket socket = serverSocket.accept();
            System.out.println("接收到客户端连接请求");
            String message ="服务端收到client连接请求\n";
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(message.getBytes());
            outputStream.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner console = new Scanner(System.in);
            while (true){
                message = reader.readLine();
                if ("bye".equals(message)){
                    break;
                }else {
                    System.out.println("接收到客户端的message数据:" + message);
                }
                System.out.println("请输入数据要发送给client的数据:");
                String input= console.nextLine() + "\n";
                outputStream.write(input.getBytes());
                outputStream.flush();
                //如果控制台输入bye 退出程序
                if (input.contains("bye")){
                    break;
                }
            }
            outputStream.close();
            console.close();
            socket.close();
            System.out.println("server关闭成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
