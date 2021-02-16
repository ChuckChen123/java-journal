package com.chuck.concurrent;

/**
 * ReentrantLock     VS synchronized
 * CAS               VS sync
 * trylock           VS 不支持
 * lockInterupptibly VS 不支持
 * 公平和非公平锁       VS 非公平锁
 */
public class TestReentrantLock {



    static class Thread1 extends Thread {

        @Override
        public void run() {
            super.run();
        }
    }

    static class Thread2 extends Thread {

        @Override
        public void run() {
            super.run();
        }
    }
}
