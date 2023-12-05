package com.bearboy.atomic;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * atomic 包下所有工具类，只能保证单个原子性
 * AtomicLongFieldUpdater 要update字段必须是volatile、public
 * AtomicLongFieldUpdater 主要针对需某一个类某一个字段要原子操作的时候使用，比如在多线程，共享某一个class的状态字段
 */
public class AtomicLongFiledUpdaterDemo {

    private static final AtomicLongFieldUpdater<User>  fieldUpdater =
            AtomicLongFieldUpdater.newUpdater(User.class,"age");

    public static void main(String[] args) {
        User user = new User(2L, "demo");
        fieldUpdater.addAndGet(user,5);
        System.out.println(fieldUpdater.get(user));
    }
}

class User implements Serializable {
    public volatile long age;
    private String name;

    public User(long age, String name) {
        this.age = age;
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

