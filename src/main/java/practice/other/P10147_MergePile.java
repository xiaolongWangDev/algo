package practice.other;

import java.util.Arrays;

/**
 * ij  0  1  2  3
 * 0   0  5  12 22
 * 1      0  3  11
 * 2         0  5
 * 3            0
 */
public class P10147_MergePile {
    public static int maxCost(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];
        int[] prefixSum = new int[piles.length];
        int sum = 0;
        for (int i = 0; i < piles.length; i++) {
            sum += piles[i];
            prefixSum[i] = sum;
        }
        // 0,1 1,2 2,3 0,2 1,3 0,3
        for (int step = 1; step < piles.length; step++) {
            for (int i = 0; i < piles.length - step; i++) {
                int j = i + step;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k + 1][j] + prefixSum[j] - (i > 0 ? prefixSum[i - 1] : 0));
                }
            }
        }

        for (var row : dp) {
            System.out.println(Arrays.toString(row));
        }
        return dp[0][piles.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(maxCost(new int[]{4, 1, 2, 3, 9}));
    }
}


