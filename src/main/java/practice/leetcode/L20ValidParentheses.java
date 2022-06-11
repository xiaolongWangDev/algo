package practice.leetcode;

import java.util.Stack;

public class L20ValidParentheses {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (var c : s.toCharArray()) {
            if (c == '}' || c == ']' || c == ')') {
                if (stack.isEmpty()) return false;
                if( c == '}' && stack.pop() != '{') return false;
                if( c == ']' && stack.pop() != '[') return false;
                if( c == ')' && stack.pop() != '(') return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
    }
}
