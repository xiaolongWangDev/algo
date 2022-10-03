package practice.leetcode;

import java.util.Arrays;

public class L1155NumberOfDiceRollsWithTargetSum {

    public static int numRollsToTarget(int n, int k, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            int[] tmp = new int[target + 1];
            Arrays.fill(tmp, 0);
            int rollingSum = 0;
            int jmin = Math.max(1, target - k * (n - 1 - i));
            for (int j = Math.max(1, jmin - k); j < jmin; j++) {
                rollingSum += dp[j - 1];
            }
            for (int j = jmin; j <= target - (n - 1 - i); j++) {
                rollingSum += dp[j - 1];
                rollingSum %= 1000000007;
                if (j - k - 1 >= 0) {
                    rollingSum -= dp[j - k - 1];
                    if(rollingSum < 0) rollingSum += 1000000007;
                }
                tmp[j] = rollingSum;
            }
            dp = tmp;
            System.out.println(Arrays.toString(dp));
        }

        return dp[target];
    }

    /**
     * n = 5, k = 6, target = 7
     * removed unnecessary calculations
     * [0, 1, 1, 1, -1, -1, -1, -1]
     * [0, 0, 1, 2, 3, -1, -1, -1]
     * [0, 0, 0, 1, 3, 6, -1, -1]
     * [-1, 0, 0, 0, 1, 4, 10, -1]
     * [-1, -1, -1, -1, -1, -1, -1, 15]
     */
    public static int numRollsToTargetImproved(int n, int k, int target) {

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            // i = n -1; j_min = target;
            // i = n -2; j_min = target - 6;
            int jmin = Math.max(0, target - k * (n - 1 - i));
            int[] tmp = new int[target + 1];
            Arrays.fill(tmp, -1);

            for (int j = target - (n - 1 - i); j >= jmin; j--) {
                int counts = 0;
                for (int l = 1; l <= Math.min(j, k); l++) {
                    counts += dp[j - l];
                    counts %= 1_000_000_007;
                }
                tmp[j] = counts;
            }
            dp = tmp;
            System.out.println(Arrays.toString(dp));
        }

        return dp[target];
    }


    /**
     * naive dp.
     * values in the top right and bottom left corners are not needed.
     * [0, 1, 1, 1, 1, 1, 1, 0]
     * [0, 0, 1, 2, 3, 4, 5, 6]
     * [0, 0, 0, 1, 3, 6, 10, 15]
     * [0, 0, 0, 0, 1, 4, 10, 20]
     * [0, 0, 0, 0, 0, 1, 5, 15]
     */
    public static int numRollsToTargetNaive(int n, int k, int target) {

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= 0; j--) {
                int counts = 0;
                for (int l = 1; l <= Math.min(j, k); l++) {
                    counts += dp[j - l];
                    counts %= 1_000_000_007;
                }
                dp[j] = counts;
            }
            System.out.println(Arrays.toString(dp));
        }

        return dp[target];
    }

    public static void main(String[] args) {
//        System.out.println(numRollsToTargetNaive(5, 5, 7));
        System.out.println(numRollsToTargetImproved(5, 6, 7));
    }
}
