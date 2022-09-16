package practice.leetcode;

import java.util.Stack;

public class L84LargestRectangleInHistogram {
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> maxTopStack = new Stack<>();
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!maxTopStack.isEmpty() && heights[maxTopStack.peek()] >= heights[i]) {
                int poppedBarIndex = maxTopStack.pop();
                int firstLeftLowerIndex = maxTopStack.isEmpty() ? -1 : maxTopStack.peek();
                int width = i - firstLeftLowerIndex - 1;
                max = Math.max(max, width * heights[poppedBarIndex]);
            }
            maxTopStack.push(i);
        }

        while (!maxTopStack.isEmpty()) {
            int poppedBarIndex = maxTopStack.pop();
            int firstLeftLowerIndex = maxTopStack.isEmpty() ? -1 : maxTopStack.peek();
            int width = heights.length - firstLeftLowerIndex - 1;
            max = Math.max(max, width * heights[poppedBarIndex]);
        }

        return max;
    }

    public static int largestRectangleAreaArrayStack(int[] heights) {
        int[] maxTopStack = new int[heights.length];
        int stackTop = -1;
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stackTop != -1 && heights[maxTopStack[stackTop]] >= heights[i]) {
                int poppedBarIndex = maxTopStack[stackTop--];
                int firstLeftLowerIndex = stackTop == -1 ? -1 : maxTopStack[stackTop];
                int width = i - firstLeftLowerIndex - 1;
                max = Math.max(max, width * heights[poppedBarIndex]);
            }
            maxTopStack[++stackTop] = i;
        }

        while (stackTop != -1) {
            int poppedBarIndex = maxTopStack[stackTop--];
            int firstLeftLowerIndex = stackTop == -1 ? -1 : maxTopStack[stackTop];
            int width = heights.length - firstLeftLowerIndex - 1;
            max = Math.max(max, width * heights[poppedBarIndex]);
        }

        return max;
    }

    public static int largestRectangleAreaArrayLeftAndRight(int[] heights) {

        int[] leftNearestLess = new int[heights.length];
        int[] rightNearestLess = new int[heights.length];
        leftNearestLess[0] = -1;
        rightNearestLess[heights.length - 1] = heights.length;
        for (int i = 1; i < heights.length; i++) {
            int checkerIndex = i - 1;
            // recursive hop technic
            while (checkerIndex != -1 && heights[checkerIndex] >= heights[i]) {
                checkerIndex = leftNearestLess[checkerIndex];
            }
            leftNearestLess[i] = checkerIndex;
        }
        for (int i = heights.length - 2; i >= 0; i--) {
            int checkerIndex = i + 1;
            while (checkerIndex != heights.length && heights[checkerIndex] >= heights[i]) {
                checkerIndex = rightNearestLess[checkerIndex];
            }
            rightNearestLess[i] = checkerIndex;
        }

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(heights[i] * (rightNearestLess[i] - leftNearestLess[i] - 1), max);
        }

        return max;
    }

    public static void main(String[] args) {
//        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(largestRectangleAreaArrayStack(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(largestRectangleAreaArrayLeftAndRight(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
