package com.chuck.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 信号量: 还剩余能执行，不剩余不能执行
 * 控制同时执行的线程数量（限流）
 */
public class TestSemaphore {

    public static void main(String[] args) {
//        Semaphore s = new Semaphore(1);
        Semaphore s = new Semaphore(1, true);

        new Thread(() -> {
           try {
               // 阻塞，信号量减1
               s.acquire();
               System.out.println("T1 running...");
               Thread.sleep(1000);
               System.out.println("T1 running...");
           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               // 释放信号量，信号量加1
               s.release();
           }
        }).start();

        new Thread(() -> {
            try {
                // 阻塞，信号量减1
                s.acquire();
                System.out.println("T2 running...");
                Thread.sleep(1000);
                System.out.println("T2 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放信号量，信号量加1
                s.release();
            }
        }).start();
    }
}
