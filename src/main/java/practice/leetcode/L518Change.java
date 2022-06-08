package practice.leetcode;

public class L518Change {
    /**
     * i\c 0  1  2  3  4  5
     * -1 [1, 0, 0, 0, 0, 0]
     * 0 [1, 1, 1, 1, 1, 1]  // 1
     * 1 [1, 1, 2, 2, 3, 3]  // 2
     * 2 [1, 1, 2, 2, 3, 4]  // 5
     */
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coin];
            }
        }
        return dp[amount];
    }
}
