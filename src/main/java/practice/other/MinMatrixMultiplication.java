package practice.other;

import java.util.Arrays;

public class MinMatrixMultiplication {
    /**
     * ij0 1 2 3 4 5
     * 0 0
     * 1   0
     * 2     0
     * 3       0
     * 4         0
     * 5           0
     */
    public static int minMultiplications(int[] dimensions) {
        int[][] dp = new int[dimensions.length][dimensions.length];
        for (int step = 2; step < dimensions.length; step++) {
            for (int i = 0; i < dimensions.length - step; i++) {
                int j = i + step;
                for (int k = i + 1; k < j; k++) {
                    if (dp[i][j] == 0)
                        dp[i][j] = dp[i][k] + dp[k][j] + dimensions[i] * dimensions[k] * dimensions[j];
                    else {
                        dp[i][j] = Math.min(dp[i][k] + dp[k][j] + dimensions[i] * dimensions[k] * dimensions[j], dp[i][j]);
                    }
                }
            }
        }

        for (var row : dp) {
            System.out.println(Arrays.toString(row));
        }
        return dp[0][dimensions.length - 1];
    }
    // [0,1] = 0 [1,3]=[1,2]*[2*3]=35*15*5=2625 [3,5]=[3,4]*[4,5]=5*10*20=1000 [5*6]=0
    // [0,1] * [1,3]= 0 + 2625 + 30*35*5 = 7875   [3,5] * [5*6] = 1000 + 0 + 5*20*25=3500
    // [0,3] * [3,6]: 1250 + 5250 + 30*5*25 = = 6500 + 3750 =10250
    public static void main(String[] args) {
        System.out.println(minMultiplications(new int[]{30, 35, 15, 5, 10, 20, 25}));
    }
}
