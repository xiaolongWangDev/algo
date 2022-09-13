package practice.other;

import java.util.Arrays;
import java.util.Stack;

public class SmallestValueLargeThanCurrentOnTheLeft {
    public static int[] solve(int[] data) {
        int[] res = new int[data.length];
        Stack<Integer> minTopStack = new Stack<>();
        for (int i = 0; i < data.length; i++) {
            while (!minTopStack.isEmpty() && data[minTopStack.peek()] <= data[i]) {
                minTopStack.pop();
            }
            res[i] = minTopStack.isEmpty() ? -1 : data[minTopStack.peek()];
            minTopStack.push(i);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solve(new int[]{3, 2, 5, 1, 8, 1, 2})));
    }
}
