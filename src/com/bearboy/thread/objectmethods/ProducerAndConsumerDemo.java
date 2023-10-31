package com.bearboy.thread.objectmethods;

import java.util.LinkedList;

/**
 * 通过wait/object来实现生产消费者模式
 * 生产者负责往队列里面放数据，消费者负责消费，如果队列满了，生产者就停止消费；如果消费者发现队列为空就停止消费
 */
public class ProducerAndConsumerDemo {
    public static void main(String[] args) {
        ObjectStore store = new ObjectStore(10);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                store.pull("index" + i);
            }
        });
        Thread consumer = new Thread(() -> {
            int i = 0;
            while (i < 100) {
                store.take();
                i++;
            }
        });
        producer.start();
        consumer.start();
    }
}

class ObjectStore {
    public static final Object lock = new Object();
    private LinkedList<String> store = new LinkedList<>();
    private int maxSize;

    public ObjectStore(int maxSize) {
        this.maxSize = maxSize;
    }

    public void take() {
        synchronized (lock) {
            while (store.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("消费到：" + store.poll());
            lock.notify();
        }
    }

    public void pull(String data) {
        synchronized (lock) {
            while (store.size() == maxSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("生产：" + data);
            store.add(data);
            lock.notify();
        }
    }

}

