package practice.leetcode;

public class L53MaximumSubarray {
    // O(n)
    public static int maxSubArray(int[] nums) {
        int preMaxEnding = nums[0];
        int max = preMaxEnding;
        for (int i = 1; i < nums.length; i++) {
            preMaxEnding = Math.max(nums[i], preMaxEnding + nums[i]);
            max = Math.max(max, preMaxEnding);
        }

        return max;
    }

    // though this is O(log(n)), it appears it runs slower
    // this is slower
    public static int maxSubArrayDivideAndConquer(int[] nums) {
        return rec(nums, 0, nums.length - 1).maxSum;
    }


    static class Sums {
        int maxLeftAttachedSum;
        int maxRightAttachedSum;
        int maxSum;
        int sum;

        public Sums(int maxLeftAttachedSum, int maxRightAttachedSum, int maxSum, int sum) {
            this.maxLeftAttachedSum = maxLeftAttachedSum;
            this.maxRightAttachedSum = maxRightAttachedSum;
            this.maxSum = maxSum;
            this.sum = sum;
        }
    }

    private static Sums rec(int[] nums, int l, int r) {
        if (l == r) {
            return new Sums(nums[l], nums[l], nums[l], nums[l]);
        }
        int m = (l + r) >> 1;
        Sums leftSums = rec(nums, l, m);
        Sums rightSums = rec(nums, m + 1, r);
        return new Sums(
                Math.max(leftSums.maxLeftAttachedSum, leftSums.sum + rightSums.maxLeftAttachedSum),
                Math.max(rightSums.maxRightAttachedSum, leftSums.maxRightAttachedSum + rightSums.sum),
                Math.max(Math.max(leftSums.maxSum, rightSums.maxSum), leftSums.maxRightAttachedSum + rightSums.maxLeftAttachedSum),
                leftSums.sum + rightSums.sum
        );
    }


}
