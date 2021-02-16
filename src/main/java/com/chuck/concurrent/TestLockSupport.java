package com.chuck.concurrent;

import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
           for (int i=0; i<10; i++) {
               System.out.println(i);
               if (i == 5) {
                   LockSupport.park();
               }

               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        thread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(thread);
    }
}
