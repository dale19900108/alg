package com.dabaicong.arithmetic.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Num {

    public static void main(String[] args) {

        int[] array = new int[]{1,2,3,4,5};
        int[] result = sum(array);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[]  sum(int[] array){

        if (array== null|| array.length==0){
            return new int[]{};
        }

        int preSum = 0 ;
        int[] sumArray = new int[array.length];
        for (int i =0 ;i <array.length;i++) {
            sumArray[i]= preSum+array[i];
            preSum =sumArray[i];
        }
        return sumArray ;

    }
}
