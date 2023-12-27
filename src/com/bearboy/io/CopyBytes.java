package com.bearboy.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 通过FileInputStream 来实现8bit数据读取，FileOutputStream来实现8bit数据写入
 * 如果要实现java 类型读取，需要通过DataInputStream/DataOutputStream
 */
public class CopyBytes {
    public static void main(String[] args) {
        try( FileInputStream in = new FileInputStream("copybytes.txt");
         FileOutputStream out = new FileOutputStream("text1.txt")){
            int c;
            while ((c = in.read()) != -1) {
                System.out.println((char)c);
                out.write(c);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
