package code.stack;

import java.util.Stack;

public class MinStack {
    Stack<Integer> values = new Stack<>();
    Stack<Integer> mins = new Stack<>();

    public void push(Integer i) {
        values.push(i);
        mins.push(mins.empty() ? i : Math.min(mins.peek(), i));
    }

    public Integer pop() {
        mins.pop();
        return values.pop();
    }


    public Integer getMin() {
        return mins.peek();
    }

    public static void main(String[] args) {
        MinStack structure = new MinStack();
        structure.push(3);
        structure.push(5);
        structure.push(4);
        structure.push(2);
        structure.push(6);
        structure.push(1);
        structure.push(7);

        for (int i = 0; i < 7; i++) {
            System.out.printf("%d, %d\n", structure.getMin(), structure.pop());
        }
    }
}
