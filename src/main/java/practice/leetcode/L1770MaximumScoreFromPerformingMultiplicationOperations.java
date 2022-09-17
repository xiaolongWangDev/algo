package practice.leetcode;

public class L1770MaximumScoreFromPerformingMultiplicationOperations {
    // num        [1,2,3]
    // multiplier [3,2,1]

    /**
     * let i be the start index of num inclusive, j be the end index inclusive
     * [1,2] [2,1] or [2,3] [2,1]
     * <p>
     * * i/j 0                   1                2
     * * 0   3*1 max(3+2*2, 6+2*1)                r
     * * 1                     3*2 max(6+2*3, 9+2*2)
     * * 2                                       3*3
     */

    public static int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        int offset = n - m;
        int[] dp = new int[m];
        // go for m rounds, starting from the last round, which is the base case
        for (int round = m - 1; round >= 0; round--) {
            // the multiplier of the round
            int multiplier = multipliers[round];

            // to save memory, we iterate from bottom right to up left.
            // i always ends at the 0th row, so, the start index is variable.
            for (int i = round; i >= 0; i--) {
                // the first j in each round is always be n-1, that's when i is round, so it's easy to get the j formula
                int j = n - 1 - round + i;

                // since we made the dp size m instead of n, we need to left shift the indexes
                int k = j - offset;

                if (round == m - 1) {
                    // the last round, just pick the first or the last number to multiplier the multiplier
                    // since there might be negative numbers, you cannot pull the multiplier out of the max
                    dp[k] = Math.max(multiplier * nums[i], multiplier * nums[j]);
                } else {
                    dp[k] = Math.max(dp[k - 1] + multiplier * nums[j], dp[k] + multiplier * nums[i]);
                }
            }
//            System.out.println(Arrays.toString(dp));
        }

        return dp[m - 1];
    }


    public static void main(String[] args) {
        System.out.println(maximumScore(new int[]{1, 2, 3}, new int[]{3, 2, 1}));
        System.out.println(maximumScore(new int[]{-5, -3, -3, -2, 7, 1}, new int[]{-10, -5, 3, 4, 6}));
    }
}
