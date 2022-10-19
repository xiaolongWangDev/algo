package practice.leetcode;

public class L55JumpGame {
    public static boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[dp.length - 1] = true;
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = Math.min(i + nums[i], dp.length - 1); j > i; j--) {
                if (dp[j]) {
                    dp[i] = true;
                    j = i;
                }
            }
        }

        return dp[0];
    }

    public static boolean greedy1(int[] nums) {
        int range = 0;
        int i = 0;
        while (i != nums.length - 1 && i <= range) {
            range = Math.max(i + nums[i], range);
            if (i == range) return false;
            i++;
        }

        return i == nums.length - 1;
    }


    public static void main(String[] args) {
        System.out.println(greedy1(new int[]{2, 3, 1, 1, 4}));
        System.out.println(greedy1(new int[]{3, 2, 1, 0, 4}));
    }
}
