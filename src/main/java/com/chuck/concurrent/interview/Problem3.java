package com.chuck.concurrent.interview;

/**
 * 要求用线程顺序打印A1B2C3...Z26
 */
public class Problem3 {

    private static volatile int index = 0;
    private static final String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {

        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                while (index < str.length()) {
                    System.out.print(str.charAt(index));
                    try {
                        o.notify();
                        if ((index) < str.length()) {
                            o.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (o) {
                while (index < str.length()) {
                    System.out.print(index + 1);
                    index++;
                    try {
                        o.notify();
                        if ((index) < str.length()) {
                            o.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
