package com.dabaicong.arithmetic.test;

import com.dabaicong.arithmetic.skipList.SkipList;


public class SkipListTest {

    public static void main(String[] args) {
        SkipList list = new SkipList();
//
//        for (int i = 0; i < 10; i++) {
//            int randNumber = (int) (Math.random() * 10)+1;
//            list.add(randNumber);
//            list.print();
//        }
         // 随机插入 6/10/8/7/3/10
        list.add(6);
        list.add(10);
        list.add(8);
        list.add(7);
        list.add(3);
        System.out.println(list.search(22));
        System.out.println(list.search(2));

        list.print();
        System.out.println("22222222222222222222222");
        list.add(10);
        list.add(10);
        list.add(10);
        list.print();

    }
}
