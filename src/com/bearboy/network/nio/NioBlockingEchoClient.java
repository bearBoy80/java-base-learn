package com.bearboy.network.nio;

import com.bearboy.network.Chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioBlockingEchoClient {
    public static void main(String[] args) {
        SocketChannel channel = null;
        try {
            channel = SocketChannel.open();
            channel.connect(new InetSocketAddress("localhost", 8080));
            NioChat chat = new NioChat("服务器",null,channel);
            chat.chatting();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
