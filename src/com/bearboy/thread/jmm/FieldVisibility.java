package com.bearboy.thread.jmm;

/**
 * 描述：     演示可见性带来的问题
 * 可见性带来的问题：
 * a =3,b=3
 *  a=1 b=2
 *  a =3 b=2
 */
public class FieldVisibility {

     volatile int a = 1;
     int b = 2;

    private void change() {
        b = a;
        a = 3;
    }


    private void print() {
        System.out.println("b=" + b + ";a=" + a);
    }

    public static void main(String[] args) {
        while (true) {
            FieldVisibility test = new FieldVisibility();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();
        }

    }


}
