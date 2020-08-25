package com.dabaicong.arithmetic.test;

import com.dabaicong.arithmetic.skipList.SkipList;


public class SkipListTest {

    public static void main(String[] args) {
        SkipList list = new SkipList();

        for (int i = 0; i < 10; i++) {
            int randNumber = (int) (Math.random() * 10)+1;
            list.add(randNumber);
            list.print();
        }

    }
}
