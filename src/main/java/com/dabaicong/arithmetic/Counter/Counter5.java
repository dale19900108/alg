package com.dabaicong.arithmetic.Counter;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  Semaphore 信号量，通过信号控制锁,与lock类似
 */
public class Counter5 {
    private static  Integer value = 0 ;
    public static void main(String[] args) {

        final  Semaphore semaphore = new Semaphore(1);

        for (int i = 1; i <= 9; i += 2) {
            int finalI = i;
            new Thread(() -> {
                while (true) {
                    try {
                        semaphore.acquire();
                        value++;
                        System.out.println(value);
                        semaphore.release();
                        Thread.sleep(finalI * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }
}