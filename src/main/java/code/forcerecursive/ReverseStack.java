package code.forcerecursive;

import java.util.Stack;

public class ReverseStack {

    public void reverse(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            Integer bottom = getBottom(stack);
            reverse(stack);
            stack.push(bottom);
        }
    }

    public Integer getBottom(Stack<Integer> stack) {
        Integer current = stack.pop();
        if (stack.isEmpty()) {
            return current;
        } else {
            Integer result = getBottom(stack);
            stack.push(current);
            return result;
        }
    }

    public static void main(String[] args) {
        ReverseStack algo = new ReverseStack();
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        algo.reverse(stack);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
