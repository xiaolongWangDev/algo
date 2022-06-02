package practice.leetcode;

import java.util.*;

import static practice.leetcode.L1TwoSum.orderedTwoSumAllUniqueValues;

/**
 * O(max(n^(k - 1), nlog(n))
 */
public class L18FourSum {
    public static Collection<LinkedList<Integer>> kSum(int[] nums, int target, int start, int k) {
        if (k == 2) {
            return orderedTwoSumAllUniqueValues(nums, target, start);
        }
        List<LinkedList<Integer>> results = new ArrayList<>();
        for (int i = start; i < nums.length; i++) {
            if (i != start && nums[i] == nums[i - 1]) {
                continue;
            }
            int num = nums[i];
            for (var subResult : kSum(nums, target - num, i + 1, k - 1)) {
                subResult.addFirst(num);
                results.add(subResult);
            }
        }

        return results;
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return new ArrayList<>(kSum(nums, target, 0, 4));
    }

    public static void main(String[] args) {
        var input1 = new int[]{1, 0, -1, 0, -2, 2};
        var input2 = new int[]{2, 2, 2, 2};
        System.out.println(fourSum(input1, 0));
        System.out.println(fourSum(input2, 8));
    }
}
