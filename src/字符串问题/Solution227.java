package 字符串问题;

import java.util.*;

public class Solution227 {
    public static int calculate(String s) {
        //表达式用栈
        Deque<Integer> stack = new ArrayDeque<Integer>();
        char preSign = '+';
        int num = 0;
        int n = s.length();
        for(int i = 0; i < n; i++){
            if(Character.isDigit(s.charAt(i))){
                num = num * 10 + s.charAt(i) - '0';
            }

            if(!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1){
                switch(preSign){
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                }

                preSign = s.charAt(i);
                num = 0;
            }
        }

        int ans = 0;
        while(!stack.isEmpty()){
            ans += stack.pop();
        }

        return ans;
    }

    public static void main(String[] args){
        String s = "3+2*2";
        int ans = calculate(s);
        System.out.println(ans);
    }
}
