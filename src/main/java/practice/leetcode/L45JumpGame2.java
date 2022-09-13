package practice.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

public class L45JumpGame2 {
    /**
     * [2,3,1,1,4]
     * <p>
     * \ 0 1 2 3 4
     * 2 0 1 1 n n
     * 3 0 1 1 2 2
     * 1 0 1 1 2 2
     * 1 0 1 1 2 2
     * 4
     */
    public static int jump(int[] nums) {
        Integer[] dp = new Integer[nums.length];
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < nums.length) {
                    dp[i + j] = dp[i + j] == null ? dp[i] + 1 : Math.min(dp[i] + 1, dp[i + j]);
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[nums.length - 1];
    }

    // 1st heuristic is: already populated value won't be updated to a better value. So we'll only update new value
    // 2nd heuristic is: values before and including dp[i] won't be useful for future iterations, so instead of keeping
    // them in the memory, we can use a queue and keep evict them as we iterate.
    // 3rd heuristic is: base on 1st, as soon as we reach the target index, it's the best value, and we can return

    public static int jumpOp(int[] nums) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        for (int i = 0; i < nums.length - 1; i++) {
            int num = nums[i];
            int baseSteps = queue.pollFirst();
            for (int j = queue.size(); j < num; j++) {
                if (i + j + 1 == nums.length - 1) {
                    return baseSteps + 1;
                }
                queue.add(baseSteps + 1);
            }
//            System.out.println(queue);
        }
        return queue.getFirst();
    }

    // essentially the same as the solution using queue. It just won't bother moving values into/out of the collection
    public static int jumpGreedy(int[] nums) {
        int steps = 0;
        int curBfsLevelEnd = 0;
        int nextBfsLevelEnd = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (i + nums[i] >= nums.length - 1) {
                return steps + 1;
            }
            nextBfsLevelEnd = Math.max(nextBfsLevelEnd, i + nums[i]);
            if (i == curBfsLevelEnd) {
                steps++;
                curBfsLevelEnd = nextBfsLevelEnd;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
//        jump(new int[]{2,3,1,1,4});
        System.out.println(jumpOp(new int[]{2, 3, 1, 1, 4}));
    }
}
