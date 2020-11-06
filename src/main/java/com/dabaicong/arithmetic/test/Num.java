package com.dabaicong.arithmetic.test;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.*;

public class Num {

    public static void main(String[] args) {

    }

    public static void testArray() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(9);
        list.add(6);
        list.remove(new Integer(6));
        System.out.println(list);
    }

}
