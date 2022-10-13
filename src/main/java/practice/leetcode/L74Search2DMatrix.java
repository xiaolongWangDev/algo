package practice.leetcode;

public class L74Search2DMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        // starting to top right, going down or left at each step
        int N = matrix.length;
        int M = matrix[0].length;

        int x = 0;
        int y = M - 1;
        while (y >= 0 && x < N) {
            if (matrix[x][y] == target) return true;
            if (matrix[x][y] > target) {
                y--;
            } else {
                x++;
            }
        }
        return false;
    }
}
