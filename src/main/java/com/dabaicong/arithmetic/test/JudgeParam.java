package com.dabaicong.arithmetic.test;
import java.util.Scanner;
import java.util.Stack;

public class JudgeParam {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String param = scan.nextLine();
            System.out.println(judege(param));

        }
    }

    public static boolean judege(String value ){

        if (value == null || value.length() < 4){
            return false ;
        }

        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < value.length();) {
            char temp  = value.charAt(i);
            // 遇到$开始判定
            if (temp == '$' ){
                // 下两位没有越界
                if (i+1<=value.length() && i+2<= value.length()){
                    if (value.charAt(i+1)=='{' && (value.charAt(i+2)!='{' && value.charAt(i+2)!='}'&& value.charAt(i+2)!='&')){
                        // 正常情况，value压入栈
                        stack.push(temp+"");
                        // 可以多移动一位
                        i++;
                    }else {
                        return false;
                    }
                }else {
                    // 没有了后两位
                    return false;
                }

            }
            if (temp =='}'){
                if (stack.size()==0){
                    return false;
                }
                String pushValue = stack.pop();
                if (pushValue== null){
                    return false;
                }
            }
            // 继续移动
            i++;
        }
        //  stack中还有值，一定不匹配
        return stack.size() == 0;
    }
}
