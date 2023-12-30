package com.bearboy.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocketServer {
    public static void main(String[] args) {
        try {
            InetSocketAddress address = new InetSocketAddress("localhost",8080);
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(address);
            Socket socket = serverSocket.accept();
            String message ="服务端收到client连接请求";
            socket.getOutputStream().write(message.getBytes());
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
