package com.chuck.concurrent.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 */
public class Problem2 {

    List list = Collections.synchronizedList(new ArrayList<>());
    Object lock = new Object();
    Semaphore putS = new Semaphore(2);
    Semaphore getS = new Semaphore(10);

    public static void main(String[] args) {
        Problem2 problem2 = new Problem2();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                while (true) {
                    problem2.get();
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    problem2.put(new Object());
                }
            }).start();
        }
    }

    public void put(Object o) {

//        synchronized (lock) {
            try {
                putS.acquire();
                list.add(o);
                System.out.println("ThreadName: " + Thread.currentThread().getName() + "添加... Size: " + list.size());
//                lock.notifyAll();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                putS.release();
            }
//        }
    }

    public Object get() {

        Object o = null;

        try {
            getS.acquire();
//            synchronized (lock) {
            synchronized (lock) {
                if (list.size() != 0) {
                    o = list.get(0);
                    list.remove(0);
                    System.out.println("ThreadName: " + Thread.currentThread().getName() + "获取... Size: " + list.size());
                }
            }
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            getS.release();
        }

        return o;
    }
}
