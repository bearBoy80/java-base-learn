package com.bearboy.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 演示FileChannel 使用
 */
public class FileChannelDemo {
    public static void main(String[] args) {
        try {
            //读取scanx.txt
            RandomAccessFile file = new RandomAccessFile("./scanx.txt", "rw");
            //创建一个filechannel
            FileChannel channel = file.getChannel();
            long length = file.length();
            //创建一个bytebuffer
            ByteBuffer buffer = ByteBuffer.allocate((int) length);
            //将数据读到buffer
            channel.read(buffer);
            //调整position指向
            buffer.flip();
            StringBuilder builder = new StringBuilder();
            while (buffer.hasRemaining()) {
                builder.append((char)buffer.get());
            }
            System.out.println(builder);
            //重置mark\position 开始重写读取buffer
            buffer.rewind();
            //做标记
            buffer.mark();
            //获取buffer中的一个byte
            System.out.println("读取第一个字节的数据 " + (char)buffer.get());
            System.out.println("读取第二个字节的数据 " +(char)buffer.get());
            //将position指向到之前到mark标记的位置
            buffer.reset();
            System.out.println("回到mark标记前的position "+(char)buffer.get());
            file.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
