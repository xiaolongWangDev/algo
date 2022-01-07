package stack;

import java.util.Stack;

public class Problem_SortElementsInStack {
    public void sort(Stack<Integer> input) {
        Stack<Integer> buffer = new Stack<>();

        while (!input.isEmpty()) {
            Integer element = input.pop();
            while (!buffer.isEmpty() && element > buffer.peek()) {
                input.push(buffer.pop());
            }
            buffer.push(element);
        }

        while (!buffer.isEmpty()) {
            input.push(buffer.pop());
        }
    }

    public static void main(String[] args) {
        Problem_SortElementsInStack p = new Problem_SortElementsInStack();
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(2);
        stack.push(3);
        stack.push(6);
        stack.push(5);
        p.sort(stack);
        stack.forEach(System.out::println);
    }
}
