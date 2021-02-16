package com.chuck.concurrent.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * 
 * @author chuck
 * 
 */
public class ProblemTwoCondition<T> {

    private List<T> list = new ArrayList<>();
    private static final int MAX_SIZE = 10;
    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    /**
     * 用来限制生产者，同一时间只能有两个生产者能生产
     */
    private Semaphore producerSemaphore = new Semaphore(2);
    /**
     * 用来限制消费者，同一时间只能有10个消费者消费
     */
    private Semaphore consumerSemaphore = new Semaphore(10);

    public static void main(String[] args) {
        ProblemTwoCondition<Object> problemTwoCondition = new ProblemTwoCondition<>();

        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                while (true) {
                	problemTwoCondition.get();
                }
            }).start();
        }

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                while (true) {
                	problemTwoCondition.put(new Object());
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

                System.out.println("数量: " + problemTwoCondition.getCount());
            }
        }).start();
    }

    /**
     * 消费者——获取
     * @return
     */
    public T get() {

        T t = null;
        try {
            consumerSemaphore.acquire();
            while(!lock.tryLock()) {
        		Thread.sleep(100);
        	}
            while (list.isEmpty()) {
                consumer.await();
            }
            t = list.remove(0);
            System.out.println("ThreadName: " + Thread.currentThread().getName() + "获取... Size: " + list.size());
            Thread.sleep(50);
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            consumerSemaphore.release();
        }

        return t;
    }

    /**
     * 生产者——新增
     * @param t
     */
    public void put(T t) {

        try {
            producerSemaphore.acquire();
            while(!lock.tryLock()) {
        		Thread.sleep(200);
        	}
            while (list.size() == MAX_SIZE) {
                producer.await();
            }
            list.add(t);
            System.out.println("ThreadName: " + Thread.currentThread().getName() + "添加... Size: " + list.size());
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            producerSemaphore.release();
        }
    }

    /**
     * 获取容器的size
     * @return
     */
    public int getCount() {

        try {
        	while(!lock.tryLock()) {
        		Thread.sleep(300);
        	}
            return list.size();
        } catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
            lock.unlock();
        }
		return 0;
    }
}
