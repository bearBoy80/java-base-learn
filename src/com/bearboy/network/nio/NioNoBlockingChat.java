package com.bearboy.network.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import static com.bearboy.network.SimpleServer.BYE;


public class NioNoBlockingChat {

    private String from;
    private String greetings;
    private SocketChannel channel;

    public NioNoBlockingChat(String from, String greetings, SocketChannel channel) {
        this.from = from;
        this.greetings = greetings;
        this.channel = channel;
    }

    public void chatting() throws IOException {
        Scanner in = new Scanner(System.in);

        try {
            System.out.println("Socket连接成功！建立输入输出");
            if (greetings != null) {
                ByteBuffer buffer = ByteBuffer.wrap(("你好，" + from + "。" + greetings).getBytes());
                channel.write(buffer);
            }
            ByteBuffer buffer = ByteBuffer.allocate(200);

            while (true) {
                buffer.clear();
                //判断channel连接是否关闭
                if (!channel.isOpen()){
                    System.out.println("chanel连接已关闭");
                    break;
                }
                //不停的取读取channel的信息
                int length = channel.read(buffer);
                if (length > 0){
                    String line = new String(buffer.array(), 0, length);
                    if (line.trim().equalsIgnoreCase(BYE)) {
                        System.out.println("对方要求断开连接");
                        //清空buffer内容为0
                        cleanBufferData(buffer, 200);
                        buffer.put("bye".getBytes());
                        buffer.flip();
                        channel.write(buffer);
                        break;
                    } else {
                        System.out.println("来自\"" + from + "\"的消息：" + line);
                    }
                    buffer.clear();
                    if ("服务器".equals(from)) {
                        System.out.println("客户端开始输入数据------");
                    } else {
                        System.out.println("服务器开始输入数据------");
                    }
                    line = in.nextLine();
                    //清空buffer内容为0
                    cleanBufferData(buffer, 200);
                    //将数据放入到buffer里面
                    buffer.put(line.getBytes());
                    buffer.flip();
                    channel.write(buffer);
                    System.out.println("数据写入完成:" + line);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("当前chanel聊天结束");
    }

    void cleanBufferData(ByteBuffer buffer, int count) {
        buffer.clear();
        for (int i = 0; i < count; i++) {
            buffer.put((byte) 0);
        }
        buffer.rewind();
    }
}
