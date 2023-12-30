package com.bearboy.network;

import java.io.*;
import java.net.Socket;

public class SimpleSocketClient {
    public static void main(String[] args)  {
        try {
            Socket socket = new Socket("localhost",8080);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //读取服务器端数据
            System.out.println(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
