package com.bearboy.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 利用Files来创建文件并写入数据
 */
public class FilesDemo {
    public static void main(String[] args) {
        try {
            Path path = Paths.get("./files.id");
            if (!Files.exists(path)){
                Files.createFile(path);
            }else {
                //如果文件是普通文件，则返回 true；如果文件不存在，不是普通文件，或者无法确定文件是否为普通文件，则返回 false。
                System.out.println(Files.isRegularFile(path));
            }
            BufferedWriter writer= Files.newBufferedWriter(path, Charset.defaultCharset());
            writer.write("1222");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
