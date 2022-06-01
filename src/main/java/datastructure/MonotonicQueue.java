package datastructure;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

/**
 * A data structure that a user can add elements in.
 * The user can only observe the extreme value.
 * The following features are guaranteed:
 * 1. after adding an element, the extreme will not decrease (not necessarily numerically!!)
 * 2. after evicting the extreme, the extreme will decrease (not necessarily numerically!!)
 * <p>
 * Under the hood, at any time:
 * The elements in the deque are monotonic.
 * And they maintain the order that they were added in.
 * <p>
 * A monotonic queue does not keep all added elements.
 */
public class MonotonicQueue<T> {
    /**
     * This is a double-ended queue because we need to be able to remove elements from both ends.
     * The most extreme value is kept to the left
     */
    private final Deque<T> baseQueue = new LinkedList<>();
    private final Comparator<T> comparator;
    private final boolean monotonicDescending;

    public MonotonicQueue(Comparator<T> comparator, boolean monotonicDescending) {
        this.comparator = comparator;
        this.monotonicDescending = monotonicDescending;
    }

    private boolean isNotMonotonic(T prevVal, T currentVal) {
        if (monotonicDescending) {
            return comparator.compare(prevVal, currentVal) <= 0;
        } else {
            return comparator.compare(prevVal, currentVal) >= 0;
        }
    }

    /**
     * add the given value to the queue. evict all existing value that violates the monotonicity
     *
     * @param value the new value
     */
    public void add(T value) {
        while (!baseQueue.isEmpty() && isNotMonotonic(baseQueue.getLast(), value)) {
            baseQueue.pollLast();
        }
        baseQueue.addLast(value);
    }

    public T peekExtreme() {
        if (baseQueue.isEmpty()) {
            throw new RuntimeException("Nothing to peek when the queue is empty");
        }
        return baseQueue.peekFirst();
    }

    public T popExtreme() {
        if (baseQueue.isEmpty()) {
            throw new RuntimeException("Nothing to pop when the queue is empty");
        }
        return baseQueue.pollFirst();
    }

    public boolean isEmpty() {
        return baseQueue.isEmpty();
    }

}
