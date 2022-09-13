package practice.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L46Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        rec(nums, 0, new LinkedList<>(), res);
        return res;
    }

    private static void rec(int[] nums, int starting, LinkedList<Integer> cumulated, List<List<Integer>> results) {

        if (starting == nums.length) {
            results.add(new ArrayList<>(cumulated));
        }

        for (int i = starting; i < nums.length; i++) {
            int temp = nums[starting];
            nums[starting] = nums[i];
            nums[i] = temp;

            cumulated.add(nums[starting]);
            rec(nums, starting + 1, cumulated, results);
            cumulated.pollLast();

            temp = nums[starting];
            nums[starting] = nums[i];
            nums[i] = temp;
        }
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }
}
