package practice.leetcode;

import java.util.Arrays;
import java.util.Stack;

public class L42TrappingRainWater {
    // method 1: pre-process. get the max value on the left and right of each i.
    public static int trapPreProcess(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        for (int i = 0; i < height.length; i++) {
            leftMax[i] = i - 1 < 0 ? -1 : Math.max(leftMax[i - 1], height[i - 1]);
        }
        for (int i = height.length - 1; i >= 0; i--) {
            rightMax[i] = i + 1 == height.length ? -1 : Math.max(rightMax[i + 1], height[i + 1]);
        }

        System.out.println(Arrays.toString(leftMax));
        System.out.println(Arrays.toString(rightMax));

        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            int delta = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (delta > 0) sum += delta;
        }
        return sum;
    }

    // method 2: monotonic stack. when we find a valley, calculate the water "above" it
    public static int trapMonotonicStack(int[] height) {
        Stack<Integer> minTopStack = new Stack<>();

        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            int currentHeight = height[i];
            while (!minTopStack.isEmpty() && height[minTopStack.peek()] < currentHeight) {
                int bottomHeightIndex = minTopStack.pop();
                if (minTopStack.isEmpty()) break;
                Integer letIndex = minTopStack.peek();
                int leftHeight = height[letIndex];
                int delta = Math.min(leftHeight, currentHeight) - height[bottomHeightIndex];
                int water = delta * (i - letIndex - 1);
                sum += water;
            }
            minTopStack.push(i);
        }
        return sum;
    }

    public static int trapArrayedStack(int[] height) {
        int[] minTopStack = new int[height.length];
        int stackTop = -1;
        int sum = 0;

        for (int i = 0; i < height.length; i++) {
            while (stackTop != -1 && height[minTopStack[stackTop]] < height[i]) {
                int poppedIndex = minTopStack[stackTop--];
                int poppedHeight = height[poppedIndex];
                if (stackTop == -1) continue;

                int leftIndex = minTopStack[stackTop];

                sum += (i - leftIndex - 1) * (Math.min(height[i], height[leftIndex]) - poppedHeight);
            }
            minTopStack[++stackTop] = i;
        }
        return sum;
    }

    // method 3: 2 pointers. this is improvement on top of method 1
    public static int trap2Pointers(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int sum = 0;
        int leftMax = -1;
        int rightMax = -1;

        while (left <= right) {

            if (height[left] < height[right]) {
                // when I enter, I know there a value bigger than myself on the right-hand side
                // that value will be bigger than any leftMax because all leftMax comes from height[left],
                // and all height[left] are smaller than that right-hand side value that make them be evaluated first
                if (leftMax <= height[left]) {
                    leftMax = height[left];
                } else {
                    // the key
                    sum += leftMax - height[left];
                }
                left ++;
            } else {
                if (rightMax <= height[right]) {
                    rightMax = height[right];
                } else {
                    // the key
                    sum += rightMax - height[right];
                }
                right --;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
//        System.out.println(trapPreProcess(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
//        System.out.println(trapMonotonicStack(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trapArrayedStack(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
//        System.out.println(trap2Pointers(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
//        System.out.println(trap2Pointers(new int[]{4, 1, 2, 3}));
    }
    //

}
