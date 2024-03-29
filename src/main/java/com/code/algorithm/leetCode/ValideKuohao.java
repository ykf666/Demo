package com.code.algorithm.leetCode;

import java.util.Stack;

/**
 * Created by yankefei on 2018/12/12.
 * <p>
 * 有效的括号
 */
public class ValideKuohao {

    public static boolean isValide(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.pop();
                if (c == ')' && topChar != '(') return false;
                if (c == '}' && topChar != '{') return false;
                if (c == ']' && topChar != '[') return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValide("{[()]}"));
        System.out.println(isValide("({[}])"));
    }

}
