package com.dabaicong.arithmetic.greedy;

public class MaxSumNumber {

    public int maxSubArray(int[] nums) {

        int res = nums[0];
        for(int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array  = new int[]{-9,-1,-2,-2,-3,-4,-1,-2,-1,-5,-3};
        MaxSumNumber maxSumNumber = new MaxSumNumber();
        int value = maxSumNumber.maxSubArray(array);
        System.out.println(value);
    }
}
