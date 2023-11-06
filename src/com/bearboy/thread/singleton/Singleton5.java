package com.bearboy.thread.singleton;

/**
 * 描述：     懒汉式（线程不安全）（不推荐）
 */
public class Singleton5 {

    private static Singleton5 instance;

    private Singleton5() {

    }

    public static Singleton5 getInstance() {
        if (instance == null) {
            synchronized (Singleton5.class) {
                //new singleton5 可能会被指令重排，导致外部引用一个未经初始化的引用
                instance = new Singleton5();
            }
        }
        return instance;
    }
}
