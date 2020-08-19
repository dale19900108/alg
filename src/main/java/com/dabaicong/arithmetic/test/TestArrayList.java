package com.dabaicong.arithmetic.test;

import java.util.ArrayList;

/**
 * 测试arraylist的bug
 *
 * 触发条件是，new一个list后，直接add没问题，
 * 指定数组下标的add/set就会有异常。
 *
 * 并且，arraylist的siz是实际数据容量。不是array数组的实际大小。
 * 所以，初始化指定容量，只是避免了扩容，size仍然是0
 */
public class TestArrayList {


    private  static void  initSizeBug(){
//        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        System.out.println(list.size());
//        for (int i = 0; i < 10; i++) {
//            list.add(i,i+1);
//        }
        list.add(1);
        System.out.println(list.size());
        list.add(2,3);
        System.out.println(list.size());
        list.set(3, 3);
        System.out.println(list.size());
    }

    public static void main(String[] args) {
        initSizeBug();
    }
}
