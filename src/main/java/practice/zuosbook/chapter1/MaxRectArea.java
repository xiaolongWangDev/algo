package practice.zuosbook.chapter1;

import tobeorganized.graph.Link;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class MaxRectArea {
    public static int solve(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] cumulatedHeights = new int[n][m];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                var cumulatedHeight = i == 0 ? 0 : cumulatedHeights[i - 1][j];
                cumulatedHeights[i][j] = matrix[i][j] == 0 ? 0 : matrix[i][j] + cumulatedHeight;
            }
            max = Math.max(max, maxArea(cumulatedHeights[i]));
        }

        return max;
    }

    public static int maxArea(int[] columns) {
        Stack<Integer> minStack = new Stack<>();
        int max = 0;
        for (int i = 0; i < columns.length; i++) {
            while (!minStack.isEmpty() && columns[minStack.peek()] > columns[i]) {
                Integer poppedIndex = minStack.pop();
                int leftIndex = minStack.isEmpty() ? -1 : minStack.peek();
                int area = (i - leftIndex - 1) * columns[poppedIndex];
                max = Math.max(max, area);
            }
            minStack.add(i);
        }

        while (!minStack.isEmpty()) {
            Integer poppedIndex = minStack.pop();
            int leftIndex = minStack.isEmpty() ? -1 : minStack.peek();
            int area = (columns.length - leftIndex - 1) * columns[poppedIndex];
            max = Math.max(max, area);
        }
        return max;

    }

    public static void main(String[] args) {
        int[][] data = new int[][]{
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };
        System.out.println(solve(data));
    }
}
