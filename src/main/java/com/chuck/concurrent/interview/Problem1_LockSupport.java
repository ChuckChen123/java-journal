package com.chuck.concurrent.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 */
public class Problem1_LockSupport {

    List list = new ArrayList();
//    volatile List list = Collections.synchronizedList(new ArrayList<>());

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {

        Problem1_LockSupport problem1_lockSupport = new Problem1_LockSupport();

        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("add " + (i + 1));
                problem1_lockSupport.add(new Object());
                if ((i + 1) == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });

        t2 = new Thread(() -> {
//            if (problem1_lockSupport.size() != 5) {
                LockSupport.park();
//            }
            System.out.println("已经满五个了！");
            LockSupport.unpark(t1);
        });

        t2.start();
        t1.start();
    }
}
