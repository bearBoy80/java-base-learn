package com.bearboy.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class NioNoBlockingClient {
    public static void main(String[] args) {
        SocketChannel channel = null;
        try {
            channel = SocketChannel.open();
            channel.connect(new InetSocketAddress("localhost", 8080));
            channel.configureBlocking(false);
            NioNoBlockingChat chat = new NioNoBlockingChat("服务器",null,channel);
            chat.chatting();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (channel != null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
