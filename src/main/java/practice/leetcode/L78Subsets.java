package practice.leetcode;

import java.util.ArrayList;
import java.util.List;

public class L78Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        rec(new ArrayList<>(), res, 0, nums);
        return res;
    }
    private static void rec(List<Integer> cumulated, List<List<Integer>> res, int cur, int[] nums){
        res.add(new ArrayList<>(cumulated));
        if(cur == nums.length) return;
        for(int i = cur; i < nums.length ;i++) {
            cumulated.add(nums[i]);
            rec(cumulated, res, i + 1, nums);
            cumulated.remove(cumulated.size() -1);
        }
    }

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1,2,3}));
    }
}
