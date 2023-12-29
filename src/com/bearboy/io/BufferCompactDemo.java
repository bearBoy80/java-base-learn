package com.bearboy.io;

import java.nio.CharBuffer;

public class BufferCompactDemo {
    public static void main(String[] args) {
        CharBuffer buffer = CharBuffer.allocate(10);
        buffer.put('H');
        buffer.put('e');
        buffer.put('l');
        buffer.put('l');
        buffer.put('o');

        buffer.flip();
        System.out.println("Before compact - position: " + buffer.position() + ", limit: " + buffer.limit());

        buffer.compact();
        System.out.println("After compact - position: " + buffer.position() + ", limit: " + buffer.limit());

        buffer.put('W');
        buffer.put('o');
        buffer.put('r');
        buffer.put('l');
        buffer.put('d');

        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
    }
}