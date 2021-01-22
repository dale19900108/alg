package com.dabaicong.arithmetic.Counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  AtomicInteger 保证多线程安全
 */
public class Counter3 {
    public static void main(String[] args) {

        AtomicInteger count = new AtomicInteger();

        count.set(1);
        for (int i = 1; i <= 9; i += 2) {
            int finalI = i;
            new Thread(() -> {
                while (true) {
                    try {
                        count.addAndGet(1);
                        System.out.println(count);
                        Thread.sleep(finalI * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }
}