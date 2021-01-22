package com.dabaicong.arithmetic.greedy;

import java.util.TreeMap;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        String s = "00123";
        System.out.println(Integer.parseInt(s));

    }
}
class Point implements  Comparable{
    int x;
    int y ;

    Point(int x,int y ){
        this.x = x;
        this.y = y ;
    }

    @Override
    public int compareTo(Object o) {
        Point newP = (Point) o ;

        double newDis  = Math.sqrt(newP.x)+Math.sqrt(newP.y);
        double dis  = Math.sqrt(x)+Math.sqrt(y);

        return dis>newDis?1:-1;
    }

    @Override
    public String toString() {
        return "Point[" + x +","+ y +']';
    }
}
