package datastructure;

import helper.ExtremeType;

import java.util.Comparator;
import java.util.Stack;


/**
 * The extreme value is kept on the top of the stack.
 * Every time a new value is added, it'll evict values from the top of the stack until the monotonicity is satisfied.
 *
 * It's used to get the nearest value that's larger/smaller than a number at given index.
 *
 * iterator direction | extreme type | usage
 *        <-               MIN         larger than ith number on the right side
 *        <-               MAX         smaller than ith number on the right side
 *        ->               MIN         larger than ith number on the left side
 *        ->               MAX         smaller than ith number on the left side
 */
public class MonotonicStack<T> {
    private final Stack<T> baseStack = new Stack<>();
    private final Comparator<T> comparator;
    private final ExtremeType type;

    public MonotonicStack(Comparator<T> comparator, ExtremeType type) {
        this.comparator = comparator;
        this.type = type;
    }

    private boolean shouldEvict(T oldVal, T newVal) {
        if(ExtremeType.MAX == type) {
            return comparator.compare(oldVal, newVal) >= 0;
        } else {
            return comparator.compare(oldVal, newVal) <= 0;
        }
    }

    public void add(T val) {
        evictInvalid(val);
        baseStack.push(val);
    }

    public void evictInvalid(T val) {
        while(!baseStack.isEmpty() && shouldEvict(baseStack.peek(), val)) {
            baseStack.pop();
        }
    }

    public T peekExtreme() {
        if (baseStack.isEmpty()) {
            throw new RuntimeException("Nothing to peek when the stack is empty");
        }
        return baseStack.peek();
    }

    public T popExtreme() {
        if (baseStack.isEmpty()) {
            throw new RuntimeException("Nothing to pop when the stack is empty");
        }
        return baseStack.pop();
    }

    public boolean isEmpty() {
        return baseStack.isEmpty();
    }


}
