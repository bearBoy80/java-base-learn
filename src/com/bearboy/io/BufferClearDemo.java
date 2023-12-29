package com.bearboy.io;

import java.nio.CharBuffer;

public class BufferClearDemo {
    public static void main(String[] args) {
        CharBuffer buffer = CharBuffer.allocate(10);
        buffer.put('H');
        buffer.put('e');
        buffer.put('l');
        buffer.put('l');
        buffer.put('o');

        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        System.out.println();

        buffer.clear();
        System.out.println("After clear - position: " + buffer.position() + ", limit: " + buffer.limit());

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