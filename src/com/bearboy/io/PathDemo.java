package com.bearboy.io;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 演示通过path获取user.dir 相关目录信息
 */
public class PathDemo {
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);
        Path path = Paths.get(userDir);
        System.out.format("toString: %s%n", path);
        System.out.format("getFileName: %s%n", path.getFileName());
        System.out.format("getName(0): %s%n", path.getName(0));
        System.out.format("getNameCount: %d%n", path.getNameCount());
        System.out.format("subpath(0,2): %s%n", path.subpath(0,2));
        System.out.format("getParent: %s%n", path.getParent());
        System.out.format("getRoot: %s%n", path.getRoot());
        //resolve 实现路径的拼接，类似join
        System.out.println(path.resolve("src").getNameCount());

        System.out.println(path.relativize(path.resolve("src")));
    }
}
