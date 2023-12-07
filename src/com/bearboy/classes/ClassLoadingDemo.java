package com.bearboy.classes;


import java.io.*;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 * 实现自定义classloader，实现类隔离效果
 * 不同classloader 加载同一个user类，这个user类是不想等的。
 */
public class ClassLoadingDemo {

    public static void main(String[] args) throws ClassNotFoundException {

        // 当前 main 线程 ClassLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 加载某个 Class 对象
        System.out.println( ClassLoadingDemo.class.getProtectionDomain().getCodeSource().getLocation());
        String className = "com.bearboy.classes.User";
        String classFileName = className.replace('.', '/').concat(".class");
        String classPath = System.getProperty("user.dir") + "/out/production/java-base-learn";
        // User.class 类文件的绝对路径
        File classFile = new File(classPath, classFileName);

        // ClassLoader 也是对象，也会被 GC 管理
        MyClassLoader myClassLoader = new MyClassLoader();
        // .class 文件变为字节流 byte[]，再定义 Class 对象
        Class<?> userClass = myClassLoader.defineClass(className, classFile);

        System.out.println("当前类对象：" + userClass);
        Stream.of(userClass.getDeclaredFields()).forEach(field -> {
            System.out.println("当前字段信息：" + field);
        });

        Class<?> userClassFromThreadContextClassLoader = classLoader.loadClass(className);
        // User.class 被 MyClassLoader 加载后，是否与线程上下文加载的 User.class 对象是否一致？
        // 这个现象能够解释 Spring spring-boot-devtools 模块 Class!=Class 问题
        System.out.println("userClass == userClassFromThreadContextClassLoader ? " + (userClass == userClassFromThreadContextClassLoader));

        // 重新替换掉线程上下文 ClassLoader
        // myClassLoader -> Thread.currentThread().getContextClassLoader()
        Thread.currentThread().setContextClassLoader(myClassLoader);
        // 老的线程上下文 ClassLoader 是 MyClassLoader 的 parent，由于双亲委派，即使 MyClassLoader 重新调用
        // loadClass(String) 方法，也不会重新加载。因为之前classLoader已经加载过user类了，具体可以loadClass看下里面的代码
        Class<?> userClassFromMyClassLoader = classLoader.loadClass(className);
        System.out.println("userClass == userClassFromMyClassLoader ? " + (userClass == userClassFromMyClassLoader));

        // 已加载 Class 是如何实现，目标方法： java.lang.ClassLoader.findLoadedClass0
        System.out.println("userClassFromThreadContextClassLoader == userClassFromMyClassLoader ? " + (userClassFromThreadContextClassLoader == userClassFromMyClassLoader));
    }

    static class MyClassLoader extends ClassLoader {

        public MyClassLoader() {
            // 当前线程上下文 ClassLoader 作为 Parent
            super(Thread.currentThread().getContextClassLoader());
        }

        // 文件 -> 定义某个 Class
        public Class<?> defineClass(String name, File classFile) {
            // File classFile -> byte[]
            byte[] bytes = loadBytes(classFile);
            // 利用 ClassLoader defineClass 方法来定义 Class
            // 可用于动态加载
            return super.defineClass(name, bytes, 0, bytes.length);

        }

        private byte[] loadBytes(File classFile) {
            byte[] bytes = new byte[(int) classFile.length()];
            try {
                Files.newInputStream(classFile.toPath()).read(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return bytes;
        }
    }
}