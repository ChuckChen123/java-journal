package com.chuck.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁: 共享锁 + 排他锁
 * @author chuck
 */
public class TestReadWriteLock {

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock) throws InterruptedException {
        try {
        	if (lock.tryLock()) {
        		Thread.sleep(1000);
                System.out.println("read over!");
        	}
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock) throws InterruptedException {
        try {
        	if (lock.tryLock()) {
        		Thread.sleep(1000);
                System.out.println("write over!");
        	}
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int i=0; i<10; i++) {
            new Thread(() -> {
                try {
                    read(readLock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i=0; i<10; i++) {
            new Thread(() -> {
                try {
                    write(writeLock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
