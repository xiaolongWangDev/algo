package tobeorganized.stack;

import java.util.LinkedList;
import java.util.Queue;

public class QueueMadeStack<T> {
    private final Queue<T> a = new LinkedList<>();
    private final Queue<T> b = new LinkedList<>();
    private Queue<T> active = a;

    public void push(T element) {
        active.add(element);
    }

    public boolean isEmpty() {
        return active.isEmpty();
    }

    public T peek() {
        if (active.isEmpty()) throw new IllegalArgumentException();
        Queue<T> buff = active == a ? b : a;
        T result = null;
        while (!active.isEmpty()) {
            result = active.poll();
            buff.add(result);
        }
        active = buff;
        return result;
    }

    public T pop() {
        if (active.isEmpty()) throw new IllegalArgumentException();
        Queue<T> buff = active == a ? b : a;
        while (active.size() != 1) {
            buff.add(active.poll());
        }
        T result = active.poll();
        active = buff;
        return result;
    }

    public static void main(String[] args) {
        QueueMadeStack<Integer> structure = new QueueMadeStack<>();
        structure.push(1);
        structure.push(2);
        structure.push(3);
        structure.push(4);
        structure.push(5);

        System.out.println(structure.peek());
        System.out.println(structure.pop());
        System.out.println(structure.pop());
        System.out.println(structure.pop());
        System.out.println(structure.peek());
        structure.push(6);
        System.out.println(structure.peek());
        structure.push(7);
        System.out.println(structure.pop());
        System.out.println(structure.pop());
        System.out.println(structure.pop());
        System.out.println(structure.pop());
    }
}
