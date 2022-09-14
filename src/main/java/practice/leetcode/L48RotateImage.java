package practice.leetcode;

import java.util.Arrays;

public class L48RotateImage {
    public static void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length / 2; i++) {
            rotateLoop(matrix, i, i, matrix.length - 1 - i, matrix.length - 1 - i);
        }
    }

    public static void rotateLoop(int[][] matrix, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {

        for (int i = 0; i < bottomRightX - topLeftX; i++) {
            // 3 * 3
            // 0,0 -> 0,3
            // 0,3 -> 3,3
            // 3,3 -> 3,0
            // 3,0 -> 0,0
            /*
             * \  ----------> y
             * |
             * |
             * |
             * |
             * v
             * x
             */

            int topRightX = topLeftX;
            int topRightY = bottomRightY;

            int bottomLeftX = bottomRightX;
            int bottomLeftY = topLeftY;

            int temp = matrix[topLeftX][topLeftY + i];
            matrix[topLeftX][topLeftY + i] = matrix[bottomLeftX - i][bottomLeftY];
            matrix[bottomLeftX - i][bottomLeftY] = matrix[bottomRightX][bottomRightY - i];
            matrix[bottomRightX][bottomRightY - i] = matrix[topRightX + i][topRightY];
            matrix[topRightX + i][topRightY] = temp;
        }
    }

    public static void main(String[] args) {
        int[][] data = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
        };
//        int[][] data = new int[][]{
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9}
//        };
        rotate(data);
        System.out.println(Arrays.deepToString(data));
    }
}
