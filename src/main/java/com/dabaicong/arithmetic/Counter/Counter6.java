package com.dabaicong.arithmetic.Counter;

import lombok.SneakyThrows;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  阻塞队列。
 */
public class Counter6 {

    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(1);
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
        Producer producer1 = new Producer(queue,count,1);
        Producer producer3 = new Producer(queue,count,3);
        Producer producer5 = new Producer(queue,count,5);
        Producer producer7 = new Producer(queue,count,7);
        Producer producer9 = new Producer(queue,count,9);

    }
}

class Producer implements Runnable{

    private LinkedBlockingQueue<Integer> queue;
    private AtomicInteger count;
    private Integer sleepTime ;

    public Producer(LinkedBlockingQueue<Integer> queue,AtomicInteger count,Integer sleepTime) {
        this.queue =queue;
        this.count =count;
        this.sleepTime = sleepTime;
    }

    @SneakyThrows
    @Override
    public void run() {
        queue.offer(count.addAndGet(1));
        Thread.sleep(sleepTime*100);
    }
}

class Consumer implements Runnable{

    private LinkedBlockingQueue<Integer> queue;

    public Consumer(LinkedBlockingQueue<Integer> queue) {
        this.queue =queue;
    }
    @Override
    public void run() {
        while (true){
            Integer value = queue.poll();
            System.out.println(value);
        }
    }
}