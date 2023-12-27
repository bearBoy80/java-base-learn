package com.bearboy.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Formatter;
import java.util.Scanner;

/**
 * 从文件中读取特定类型比如Long/int/double等
 */
public class ScanDemo {
    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("scanx.txt"));
            scanner.useDelimiter(",\\s*");
            while (scanner.hasNext()){
                System.out.println(scanner.next());
            }
            String input = "1 fish 2 fish red fish blue fish";
            //自定义分隔符
            scanner = new Scanner(input).useDelimiter("\\s*fish\\s*");
            System.out.println(scanner.nextInt());
            System.out.println(scanner.nextInt());
            System.out.println(scanner.next());
            System.out.println(scanner.next());
            Formatter formatter = new Formatter();
            System.out.println(formatter.format("%s",input));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if (scanner != null){
                scanner.close();
            }
        }
    }
}
