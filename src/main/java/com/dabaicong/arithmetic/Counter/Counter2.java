package com.dabaicong.arithmetic.Counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock 方式实现，加锁，释放。保证线程安全
 */
public class Counter2 {
    private static Integer value = 0;

    public static void main(String[] args) {


        Lock lock = new ReentrantLock(true);

        for (int i = 1; i <= 9; i += 2) {
            int finalI = i;
            new Thread(() -> {
                while (true) {
                    try {
                        lock.lock();
                        value++;
                        System.out.println(value);
                        Thread.sleep(finalI * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }).start();
        }
    }
}