package practice.leetcode;

public class L85MaximalRectangle {
    public static int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        if (n == 0) {
            return 0;
        }
        int m = matrix[0].length;

        int[] heights = new int[m];
        int max = 0;
        for (var row : matrix) {
            for (int i = 0; i < m; i++) {
                if (row[i] == '0') {
                    heights[i] = 0;
                } else {
                    heights[i] += 1;
                }
            }
            max = Math.max(max, maxRecInHistogram(heights));
        }
        return max;
    }

    private static int maxRecInHistogram(int[] heights) {
        int[] leftNearestShorter = new int[heights.length];
        leftNearestShorter[0] = -1;
        for (int i = 1; i < heights.length; i++) {
            int checkerIndex = i - 1;
            while (checkerIndex != -1 && heights[checkerIndex] >= heights[i]) {
                checkerIndex = leftNearestShorter[checkerIndex];
            }
            leftNearestShorter[i] = checkerIndex;
        }

        int[] rightNearestShorter = new int[heights.length];
        rightNearestShorter[heights.length - 1] = heights.length;
        for (int i = heights.length - 2; i >= 0; i--) {
            int checkerIndex = i + 1;
            while (checkerIndex != heights.length && heights[checkerIndex] >= heights[i]) {
                checkerIndex = rightNearestShorter[checkerIndex];
            }
            rightNearestShorter[i] = checkerIndex;
        }

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, (rightNearestShorter[i] - leftNearestShorter[i] - 1) * heights[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        char[][] d = new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(maximalRectangle(d));
    }

}
