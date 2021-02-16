package com.chuck.concurrent.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class Problem2_Sync<T> {

    List<T> list = new ArrayList<>();
    private static final int MAX_SIZE = 10;
    private Semaphore producerSemaphore = new Semaphore(2);
    private Semaphore consumerSemaphore = new Semaphore(10);

    public static void main(String[] args) {
        Problem2_Sync problem2_sync = new Problem2_Sync();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                while (true) {
                    problem2_sync.get();
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    problem2_sync.put(new Object());
                }
            }).start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("数量: " + problem2_sync.getCount());
            }
        }).start();
    }

    public void put(T t) {

        try {
            producerSemaphore.acquire();
            synchronized (this) {
                while (list.size() == MAX_SIZE) {
                    wait();
                }
                list.add(t);
                System.out.println("ThreadName: " + Thread.currentThread().getName() + "添加... Size: " + list.size());
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            producerSemaphore.release();
        }
    }

    public T get() {

        T o = null;
        try {
            consumerSemaphore.acquire();
            synchronized (this) {
                while (list.size() == 0) {
                    wait();
                }
                o = list.remove(0);
                System.out.println("ThreadName: " + Thread.currentThread().getName() + "获取... Size: " + list.size());
                Thread.sleep(1000);
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consumerSemaphore.release();
        }

        return o;
    }

    public synchronized int getCount() {

        return list.size();
    }
}
