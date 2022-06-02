package practice.leetcode;

import java.util.*;

import static practice.leetcode.L1TwoSum.orderedTwoSumAllUniqueValues;

// O(n^2)
public class L15ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Integer prev = null;
        List<List<Integer>> threeSumResult = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (prev == null || num != prev) {
                var twoSumResult = orderedTwoSumAllUniqueValues(nums, -num, i + 1);
                for (var result : twoSumResult) {
                    result.addFirst(num);
                    threeSumResult.add(result);
                }
            }
            prev = num;
        }

        return threeSumResult;
    }

    public static void main(String[] args) {
        var q = new L15ThreeSum();
        var input1 = new int[]{-1, 0, 1, 2, -1, -4};
        var input2 = new int[]{0, 0, 0, 0};
        System.out.println(q.threeSum(input1));
        System.out.println(q.threeSum(input2));
    }
}
