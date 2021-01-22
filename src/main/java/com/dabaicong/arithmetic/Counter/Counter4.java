package com.dabaicong.arithmetic.Counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * single线程池，只有一个线程运行
 */
public class Counter4 {
    private static Integer value = 0 ;
    public static void main(String[] args) {


        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 9; i += 2) {
            int finalI = i;
            executor.submit(new Thread(() -> {
                while (true) {
                    try {
                        value++;
                        System.out.println(value);
                        Thread.sleep(finalI * 100);
                    } catch (InterruptedException  e) {
                        e.printStackTrace();
                    }
                }
            }));

        }
    }
}