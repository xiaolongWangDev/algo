package tobeorganized.stack;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NearestGreater {
    private static class NearestGreaterResult {
        Integer leftGreaterIndex;
        Integer rightGreaterIndex;

        public NearestGreaterResult(Integer leftGreaterIndex, Integer rightGreaterIndex) {
            this.leftGreaterIndex = leftGreaterIndex;
            this.rightGreaterIndex = rightGreaterIndex;
        }

        @Override
        public String toString() {
            return "(" + leftGreaterIndex + ", " + rightGreaterIndex + ')';
        }
    }

    // monotone stack
    public <T> Map<Integer, NearestGreaterResult> find(T[] data, Comparator<T> comparator) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, NearestGreaterResult> result = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            T element = data[i];
            while (!stack.isEmpty() && comparator.compare(element, data[stack.peek()]) > 0) {
                int outElementIndex = stack.pop();
                result.put(outElementIndex, new NearestGreaterResult(stack.empty() ? null : stack.peek(), i));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int outElementIndex = stack.pop();
            result.put(outElementIndex, new NearestGreaterResult(stack.empty() ? null : stack.peek(), null));
        }
        return result;
    }

    public static void main(String[] args) {
        NearestGreater algo = new NearestGreater();
        Integer[] testData = {5, 4, 6, 7, 2, 3, 0, 1};
        Map<Integer, NearestGreaterResult> resultMap = algo.find(testData, Comparator.comparingInt(o -> o));
        resultMap.forEach((key, value) -> {
            System.out.printf("%d=(%d, %d)\n", testData[key],
                    value.leftGreaterIndex == null ? null : testData[value.leftGreaterIndex],
                    value.rightGreaterIndex == null ? null : testData[value.rightGreaterIndex]);
        });
    }
}
