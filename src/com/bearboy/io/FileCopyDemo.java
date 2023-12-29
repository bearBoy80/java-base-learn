package com.bearboy.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

/**
 * 实现文件copy的三种方式
 */
public class FileCopyDemo {
    public static void main(String[] args) {
        File source = new File("./scanx.txt");
        File des = new File("./scanx-copy.txt");
        try {
            copyByInputStream(source, des);
            copyFromFileSystem(source,new File("./scanx-copy-01.txt"));
            zeroCopy(source,new File("./scanx-copy-02.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void copyByInputStream(File source, File des) throws IOException {
        FileInputStream inputStream = new FileInputStream(source);
        FileOutputStream outputStream = new FileOutputStream(des);
        byte[] bytes = new byte[10];
        int lengthRead;
        while ((lengthRead = inputStream.read(bytes)) > 0) {
            outputStream.write(bytes,0,lengthRead);
            outputStream.flush();
        }
        inputStream.close();
        outputStream.close();
    }
    static void copyFromFileSystem(File source, File des) throws Exception {
        Path copied = des.toPath();
        Path originalPath = source.toPath();
        Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
    }
    static void zeroCopy(File source,File des) throws IOException {
       FileChannel channel =  FileChannel.open(source.toPath());
             channel.transferTo(0,source.length(),
                FileChannel.open(des.toPath(), StandardOpenOption.CREATE,StandardOpenOption.WRITE));
       channel.close();
    }

}
