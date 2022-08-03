package practice.zuosbook.chapter1;

import java.util.Comparator;
import java.util.Stack;

public class StackMadeQueue<T> {
    private final Stack<T> first = new Stack<>();
    private final Stack<T> second = new Stack<>();

    // move all elements from first to second
    private void firstToSecond() {
        while (!first.isEmpty()) second.push(first.pop());
    }

    public void push(T o) {
        first.add(o);
    }

    // the following methods will fall back to Stack's exception when empty
    public T peek(){
        if(!second.isEmpty()) return second.peek();
        firstToSecond();
        return second.peek();
    }

    public T poll(){
        if(!second.isEmpty()) return second.pop();
        firstToSecond();
        return second.pop();
    }

    public static void main(String[] args) {
        StackMadeQueue<Integer> queue = new StackMadeQueue<>();
        queue.push(1);
        queue.push(2);
        assert 1 == queue.poll();
        assert 2 == queue.peek();
        queue.push(3);
        queue.push(4);
        assert 2 == queue.peek();
        assert 2 == queue.poll();
        assert 3 == queue.peek();
        assert 3 == queue.poll();
        assert 4 == queue.poll();
    }
}
