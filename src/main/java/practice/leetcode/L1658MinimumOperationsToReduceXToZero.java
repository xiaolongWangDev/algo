package practice.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L1658MinimumOperationsToReduceXToZero {


    public static int minOperations(int[] nums, int x) {
        int l = 0;
        int target = Arrays.stream(nums).sum() - x;
        if (target < 0) return -1; // nums are all positive
        Integer minLen = null;
        int sum = 0;
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            while (l <= r && sum > target) {
                sum -= nums[l];
                l++;
            }
            if (sum == target) {
                if (minLen == null) {
                    minLen = nums.length - (r - l + 1);
                } else {
                    minLen = Math.min(minLen, nums.length - (r - l + 1));
                }
            }
        }
        return minLen == null ? -1 : minLen;
    }

    public static int usingPrefixSumToFindMiddleWindow(int[] nums, int x) {
        int target = -x;
        for (int num : nums) target += num;
        if (target == 0) return nums.length;
        if (target < 0) return -1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, L = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            L = Math.max(L, i - map.getOrDefault(sum - target, i));
            map.put(sum, i);
        }
        return L > 0 ? nums.length - L : -1;

    }

    public static int usingPrefixSum(int[] nums, int x) {
        Map<Integer, Integer> prefixSums = new HashMap<>();
        int prefixSum = 0;
        prefixSums.put(x, 0);
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (prefixSum > x) {
                break;
            }
            if (!prefixSums.containsKey(x - prefixSum)) {
                prefixSums.put(x - prefixSum, i + 1);
            }
        }

        Integer minMoves = null;

        if (prefixSums.containsKey(0)) {
            minMoves = prefixSums.get(0);
        }
        int suffixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            suffixSum += nums[nums.length - 1 - i];
            Integer moves = prefixSums.get(suffixSum);
            if (moves != null && (moves - 1) < nums.length - 1 - i) {
                if (minMoves == null) {
                    minMoves = i + 1 + moves;
                } else {
                    minMoves = Math.min(minMoves, i + 1 + moves);
                }
            }
        }
        return minMoves == null ? -1 : minMoves;
    }

    // DP is not working for the scale of the data.
    public static int dp(int[] nums, int x) {
        Integer[][][] memo = new Integer[nums.length][nums.length][x + 1];
        return dpReduce(0, nums.length - 1, x, nums, memo);
    }

    private static int dpReduce(int l, int r, int x, int[] nums, Integer[][][] memo) {
        if (x == 0) {
            return 0;
        }
        if (l > r) {
            return -1;
        }
        if (x < -0) { // nums are all positive
            return -1;
        }

        if (memo[l][r][x] != null) return memo[l][r][x];


        int leftRes = dpReduce(l + 1, r, x - nums[l], nums, memo);
        int rightRes = dpReduce(l, r - 1, x - nums[r], nums, memo);
        if (leftRes == -1 && rightRes == -1) {
            memo[l][r][x] = -1;
            return memo[l][r][x];
        } else if (leftRes == -1) {
            memo[l][r][x] = rightRes + 1;
            return memo[l][r][x];
        } else if (rightRes == -1) {
            memo[l][r][x] = leftRes + 1;
            return memo[l][r][x];
        } else {
            memo[l][r][x] = Math.min(leftRes, rightRes) + 1;
            return memo[l][r][x];
        }
    }

    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{1, 1, 4, 2, 3}, 5));
        System.out.println(minOperations(new int[]{5, 6, 7, 8, 9}, 4));
        System.out.println(minOperations(new int[]{3, 2, 20, 1, 1, 3}, 10));
        System.out.println(minOperations(new int[]{1, 1}, 3));
    }
}
