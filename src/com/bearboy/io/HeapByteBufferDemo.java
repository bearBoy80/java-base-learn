package com.bearboy.io;

import java.nio.ByteBuffer;

/**
 * 使用缓冲区（Buffer）读取和写入数据通常遵循以下4个步骤：
 * 将数据写入缓冲区（Write data into the Buffer）
 * 调用buffer.flip()方法
 * 从缓冲区读取数据（Read data out of the Buffer）
 * 调用buffer.clear()或buffer.compact()方法
 */
public class HeapByteBufferDemo {
    public static void main(String[] args) {
        ByteBuffer buffer =  ByteBuffer.allocate(100);
        //往buffer 放入数据
        buffer.put((byte)73);
        buffer.put((byte)74);
        buffer.put((byte)75);
        buffer.put((byte)76);
        buffer.put((byte)77);
        buffer.putInt(67);
        for (int i=9;i<100;i++){
            buffer.put((byte)(i));
        }
        //调整position/limit指向
        buffer.flip();
        //循环读取buffer数据
        while (buffer.hasRemaining()){
            System.out.println((char)buffer.get());
        }
        //buffer.rewind();
        //可以通过index 直接读取数据
        System.out.println(buffer.getInt(5));
        //clear 不是清除buffer数据，而是调整position、limit
        buffer.clear();
        System.out.println((char)buffer.get());
        //将缓冲区中的未读取数据向缓冲区的开头移动。
        //将缓冲区的位置（position）设置为移动后未读取数据的末尾。
        //将缓冲区的界限（limit）设置为缓冲区的容量，以准备进行写入操作。
        ByteBuffer buffer1 = buffer.compact();
        System.out.println((char)buffer1.get());

    }
}
