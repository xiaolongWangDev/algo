package dp;

import java.util.Arrays;

public class DpUtils {
    public static int[][] get2DMemo(int N, int M) {
        int[][] memo = new int[N][M];
        fill2DArray(memo, -1);
        return memo;
    }

    public static void fill2DArray(int[][] arr, int value) {
        for (int[] row : arr) {
            Arrays.fill(row, value);
        }
    }
}
