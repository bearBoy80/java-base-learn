package com.bearboy.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioNoBlockingServer {
    public static void main(String[] args) {
        ServerSocketChannel serverchannel = null;
        boolean active = true;
        try {
            //创建一个serversocket channel
            serverchannel =ServerSocketChannel.open();
            serverchannel.bind(new InetSocketAddress("localhost",8080));
            //设置不阻塞当前线程
            serverchannel.configureBlocking(false);
            while (active){
                SocketChannel sc = serverchannel.accept();
                if (sc != null){
                    sc.configureBlocking(false);
                    System.out.println("收到来自客户端的连接"+ sc.getRemoteAddress());
                    NioNoBlockingChat chat = new NioNoBlockingChat("客户端", "你已经成功连到我。我们可以开始对话了。", sc);
                    chat.chatting();
                    sc.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (serverchannel != null){
                try {
                    serverchannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
