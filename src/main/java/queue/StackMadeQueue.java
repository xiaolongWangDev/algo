package queue;

import java.util.Stack;

public class StackMadeQueue<T> {
    private final Stack<T> a = new Stack<>();
    private final Stack<T> b = new Stack<>();

    public void add(T element) {
        a.add(element);
    }

    public T peek() {
        if (a.isEmpty() && b.empty()) throw new IllegalArgumentException();
        if (b.isEmpty()) {
            while (!a.isEmpty()) {
                b.push(a.pop());
            }
        }
        return b.peek();
    }

    public T poll() {
        if (a.isEmpty() && b.empty()) throw new IllegalArgumentException();
        if (b.isEmpty()) {
            while (!a.isEmpty()) {
                b.push(a.pop());
            }
        }
        return b.pop();
    }

    public static void main(String[] args) {
        StackMadeQueue<Integer> structure = new StackMadeQueue<>();
        structure.add(1);
        structure.add(2);
        structure.add(3);
        structure.add(4);
        structure.add(5);
        System.out.println(structure.poll());
        System.out.println(structure.poll());
        structure.add(6);
        structure.add(7);
        System.out.println(structure.peek());
        System.out.println(structure.poll());
        System.out.println(structure.poll());
        System.out.println(structure.poll());
        System.out.println(structure.poll());
        System.out.println(structure.poll());
    }
}
