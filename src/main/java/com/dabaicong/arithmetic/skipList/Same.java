package com.dabaicong.arithmetic.skipList;

import java.util.*;

public class Same {

    public static void main(String[] args) {
        String value = "aab";
        List<List<String>> result = new ArrayList<>();
        if (value == null || value.length() == 0) {
            return;
        }

        Stack<String> stack = new Stack<>();
        dfs(value, 0, value.length(), stack, result);
        for (List<String> re : result) {
            System.out.println(re);
        }
    }

    /**
     *  递归方法
     */
    private static void dfs(String value, int start, int length, Stack<String> stack, List<List<String>> result) {
        // 递归终止条件，走到了长度
        if (start == length) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = start; i < length; i++) {

            // 如果不是回文
            if (!check(value, start, i)) {
                continue;
            }
            // 是的话，加栈
            stack.push(value.substring(start, i + 1));
            dfs(value, i + 1, length, stack, result);
            // 调用结束，恢复现场。
            stack.pop();
        }
    }

    /**
     * 判定是否是回文数
     */
    private static boolean check(String value, int left, int right) {
        do{
            if (value.charAt(left) != value.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }while (left < right);
        
        return true;
    }
}


