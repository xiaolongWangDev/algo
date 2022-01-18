package dp;

import java.util.HashMap;
import java.util.Map;

public class Problem_MaxXor0Sections {
    public int find(int[] input) {
        // holds the max valid (xor == 0) sections count when last section ends on i;
        int[] dp = new int[input.length];

        Map<Integer, Integer> lastIndexOfXorSum = new HashMap<>();
        int currentXorSum = 0;

        for (int i = 0; i < input.length; i++) {
            // 2 cases:
            // case 1: the section that i belongs to is not valid
            // so dp[i] = dp[i-1]
            // case 2: i's section is valid.
            // we want to find the beginning of this section
            // we use a cumulative xor sum array to help

            currentXorSum = i == 0 ? input[i] : currentXorSum ^ input[i];
            if (i == 0) {
                dp[i] = input[0] == 0 ? 1 : 0;
            } else {
                // there's no previous section before current section which i is in
                if (lastIndexOfXorSum.get(currentXorSum) == null) {
                    // current section sums to 0, so we have a valid section
                    if (currentXorSum == 0) {
                        dp[i] = 1;
                    } else {
                        // i is not in a valid section, we can just rely on dp[i-1]
                        dp[i] = dp[i - 1];
                    }
                } else {
                    // get the better of the two: dp[i-1] or dp[lastIndexOfXorSum.get(xorSum[i + 1])] + 1
                    // dp[i-1] is already explained above
                    // lastIndexOfXorSum.get(xorSum[i + 1]) is the last index before i where their xorSum are the same
                    // This means from lastIndexOfXorSum.get(xorSum[i + 1]) + 1 to i, all the elements will xorSum to 0
                    // put in another way: it's a section -- also the last valid section
                    // therefore dp[lastIndexOfXorSum.get(xorSum[i + 1])] holds the max count of sections before the last
                    // we just need to add 1 to it to get the count of dp[i] with i exists in a valid section
                    dp[i] = Math.max(dp[i - 1], dp[lastIndexOfXorSum.get(currentXorSum)] + 1);
                }
            }
            lastIndexOfXorSum.put(currentXorSum, i);
        }

        return dp[input.length - 1];
    }

    public static void main(String[] args) {
        Problem_MaxXor0Sections p = new Problem_MaxXor0Sections();
        System.out.println(p.find(new int[]{3, 2, 1, 0, 1, 0, 2, 0, 3, 2, 1, 0, 4, 0}));
        System.out.println(p.find(new int[]{0}));
        System.out.println(p.find(new int[]{1}));
        System.out.println(p.find(new int[]{1, 2, 3}));
        System.out.println(p.find(new int[]{0, 0}));
        System.out.println(p.find(new int[]{4, 1, 2, 3}));
    }
}
