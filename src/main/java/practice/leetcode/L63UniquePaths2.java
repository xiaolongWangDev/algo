package practice.leetcode;

import java.util.Arrays;

public class L63UniquePaths2 {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    if (i == m - 1 && j == n - 1) {
                        dp[i][j] = 1;
                    } else {
                        int bottom = i + 1 == m ? 0 : dp[i + 1][j];
                        int right = j + 1 == n ? 0 : dp[i][j + 1];
                        dp[i][j] = bottom + right;

                    }
                }
            }
        }
        for (var row : dp) {
            for (var cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
        return dp[0][0];
    }

    public static int uniquePathsWithObstaclesMemoryEfficient(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[m - 1][n - 1] == 1) return 0;

        int dpArrLen = Math.min(m, n);
        int[] dp = new int[dpArrLen];
        if (m >= n) {
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (obstacleGrid[i][j] == 1) {
                        dp[j] = 0;
                    } else {
                        if (i == m - 1 && j == n - 1) {
                            dp[j] = 1;
                        } else {
                            int botVal = i == m - 1 ? 0 : dp[j];
                            int rightVal = j == n - 1 ? 0 : dp[j + 1];
                            dp[j] = botVal + rightVal;
                        }
                    }
                }
            }
        } else {
            for (int j = n - 1; j >= 0; j--) {
                for (int i = m - 1; i >= 0; i--) {
                    if (obstacleGrid[i][j] == 1) {
                        dp[i] = 0;
                    } else {
                        if (i == m - 1 && j == n - 1) {
                            dp[i] = 1;
                        } else {
                            int botVal = i == m - 1 ? 0 : dp[i + 1];
                            int rightVal = j == n - 1 ? 0 : dp[i];
                            dp[i] = botVal + rightVal;
                        }
                    }
                }
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        int[][] data = new int[][]{
                {0, 0, 0, 0}
                , {0, 1, 0, 0}
                , {0, 0, 0, 0}
        };

        System.out.println(uniquePathsWithObstaclesMemoryEfficient(data));
    }
}
