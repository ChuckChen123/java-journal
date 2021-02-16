package com.chuck.concurrent.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 */
public class Problem1 {

//    volatile List list = new ArrayList();
    volatile List list = Collections.synchronizedList(new ArrayList<>());

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Problem1 problem1 = new Problem1();

        new Thread(() -> {
           for (int i=0; i<10; i++) {
               System.out.println("add " + (i+1));
               problem1.add(new Object());
               try {
                   Thread.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }).start();

        new Thread(() -> {
            while (true) {
                if (problem1.size() == 5) {
                    System.out.println("已经满五个了！");
                    break;
                }
            }
        }).start();
    }
}
