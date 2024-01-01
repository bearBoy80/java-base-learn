package com.bearboy.network.nio;

import com.bearboy.network.Chat;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * 通过NIO来实现socketServer
 */
public class NioBlockingEchoServer {
    public static void main(String[] args) {
        try {
            //创建一个serversocket channel
            ServerSocketChannel serverchannel =ServerSocketChannel.open();
            serverchannel.bind(new InetSocketAddress("localhost",8080));
            SocketChannel sc = serverchannel.accept();
            NioChat chat = new NioChat("客户端", "你已经成功连到我。我们可以开始对话了。", sc);
            chat.chatting();
            sc.close();
            serverchannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
