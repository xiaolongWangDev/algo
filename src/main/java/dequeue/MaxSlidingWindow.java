package dequeue;

import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

public class MaxSlidingWindow<T> {
    private int left = -1;
    private int right = -1;
    private final Deque<T> deque = new LinkedList<>();
    private final T[] data;
    private final Comparator<T> comparator;

    public MaxSlidingWindow(T[] data, Comparator<T> comparator) {
        this.data = data;
        this.comparator = comparator;
    }

    public void increaseLeftTo(int n) {
        increaseLeft(n - left);
    }

    public void increaseRightTo(int n) {
        increaseRight(n - right);
    }

    private void increaseLeft(int n) {
        for (int i = 0; i < n; i++) {
            increaseLeft();
        }
    }

    private void increaseRight(int n) {
        for (int i = 0; i < n; i++) {
            increaseRight();
        }
    }

    public void increaseRight() {
        if (right < data.length) {
            right++;
            T dataToEnQueue = data[right];
            T last = deque.peekLast();
            while (!deque.isEmpty() && comparator.compare(last, dataToEnQueue) <= 0) {
                deque.pollLast();
                last = deque.peekLast();
            }
            deque.addLast(dataToEnQueue);
        }
    }

    public void increaseLeft() {
        if (left == -1) {
            left++;
            return;
        }
        if (left < right) {
            T dataToDeQueue = data[left++];
            if (dataToDeQueue == deque.peekFirst()) {
                deque.pollFirst();
            }
        }
    }

    public T getMax() {
        return deque.peekFirst();
    }

    public static void main(String[] args) {
        MaxSlidingWindow<Integer> algo = new MaxSlidingWindow<>(new Integer[]{6, 5, 4, 3, 5, 7}, Comparator.comparingInt(o -> o));
        algo.increaseRightTo(3);
        System.out.println(algo.getMax());
        algo.increaseLeftTo(1);
        System.out.println(algo.getMax());
        algo.increaseLeftTo(2);
        System.out.println(algo.getMax());
        algo.increaseRightTo(4);
        System.out.println(algo.getMax());
        algo.increaseLeftTo(3);
        System.out.println(algo.getMax());
        algo.increaseLeftTo(4);
        System.out.println(algo.getMax());
        algo.increaseRightTo(5);
        System.out.println(algo.getMax());
    }
}
