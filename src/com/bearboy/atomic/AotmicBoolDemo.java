package com.bearboy.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AotmicBoolDemo {
    public static void main(String[] args) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        atomicBoolean.compareAndSet(true,true);
        System.out.println(atomicBoolean.get());
    }
}
