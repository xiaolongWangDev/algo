package practice.leetcode;

/**
 * starting from the top right corner,
 * at each point, we can choose either to go down or go left depending on the value comparison result.
 * if we end up on an invalid point, then the target does not exist
 */
public class L240Search2DMatrix2 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int i = 0;
        int j = m - 1;
        while (i < n && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }

        return false;
    }


}
