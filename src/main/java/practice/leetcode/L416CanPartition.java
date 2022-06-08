package practice.leetcode;

// https://labuladong.github.io/algo/3/25/81/
// This is an exact match 01 knapsack problem
public class L416CanPartition {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int j = target; j > -1; j--) {
                if (j - num >= 0 && dp[j - num]) {
                    dp[j] = true;
                }
            }
        }
        return dp[target];
    }

}
