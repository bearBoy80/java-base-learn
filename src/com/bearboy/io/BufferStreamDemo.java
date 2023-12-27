package com.bearboy.io;

import java.io.*;

/**
 * 利用buffer缓存来实现一次读取一行，Buffered Stream 主要是减少jvm调用底层函数次数
 */
public class BufferStreamDemo {
    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader("unicode.txt"));
            writer = new BufferedWriter(new FileWriter("text3-read.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}