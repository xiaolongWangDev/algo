package practice.zuosbook.chapter1;

import java.util.Comparator;
import java.util.Stack;

public class StackWithGetMin<T> {
    private final Comparator<T> comparator;
    private final Stack<T> data = new Stack<>();
    private final Stack<T> minItems = new Stack<>();

    public StackWithGetMin(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void push(T o) {
        if (minItems.isEmpty() || comparator.compare(o, minItems.peek()) < 0) {
            minItems.add(o);
        } else {
            minItems.add(minItems.peek());
        }
        data.add(o);
    }

    public T pop() {
        minItems.pop();
        return data.pop();
    }

    public T peek() {
        return data.peek();
    }

    public T getMin() {
        return minItems.peek();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    public static void main(String[] args) {
        StackWithGetMin<Integer> testStack = new StackWithGetMin<>(Integer::compareTo);
        testStack.push(3);
        testStack.push(4);
        testStack.push(5);
        testStack.push(1);
        testStack.push(2);
        testStack.push(1);

        dfPrint(testStack);
    }

    public static void dfPrint(StackWithGetMin<Integer> stack){
        if(!stack.isEmpty()){
            Integer min = stack.getMin();
            Integer val = stack.pop();
            dfPrint(stack);
            System.out.printf("%2$d - %1$d\n", min, val);
        }
    }
}
