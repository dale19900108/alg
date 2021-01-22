package com.dabaicong.arithmetic.Counter;

/**
 * synchronized 共享对象操作。
 */
public class Counter1 {
    public static void main(String[] args) {

        CountNumber countNumber = new CountNumber(1);

        for (int i = 1; i <= 9; i += 2) {
            int finalI = i;
            new Thread(() -> {
                while (true) {
                    int value = countNumber.add();
                    System.out.println(value);
                    try {
                        Thread.sleep(finalI * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }
}

class CountNumber {
    int value;

    CountNumber(int value) {
        this.value = value;
    }

    public synchronized Integer add() {
        value++;
        return value;
    }
}

