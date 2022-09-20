package practice.leetcode;

import java.util.Arrays;

public class L718MaximumLengthOfRepeatedSubarray {

    /**
     * num1: [1,2,3,2,1]
     * num2: [3,2,1,4,7]
     * *            ""    1    12   123  1232 12321
     * * i\j        0     1     2     3     4     5
     * * ""    0    0     0     0     0     0     0
     * * 3     1    0     0     0     1     0     0
     * * 32    2    0     0     1     0     2
     * * 321   3    0     1     0     0
     * * 3214  4    0     0     0     0
     * * 32147 5    0     0     0     0
     */
    public static int findLength(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length + 1];
        int max = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = nums2.length; j > 0; j--) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                } else {
                    dp[j] = 0;
                }
                max = Math.max(max, dp[j]);
            }
//            System.out.println(Arrays.toString(dp));
        }
        return max;
    }

    public static void main(String[] args) {
        findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7});
    }
}
