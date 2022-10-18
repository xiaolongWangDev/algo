package practice.leetcode;

import java.util.*;

public class L90Subsets2 {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (var num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        List<List<Integer>> results = new ArrayList<>();
        rec(new ArrayList<>(), results, counts);
        return results;
    }

    private static void rec(List<Integer> cumulated, List<List<Integer>> results, Map<Integer, Integer> counts) {

        if (counts.isEmpty()) {
            results.add(new ArrayList<>(cumulated));
            return;
        }

        Iterator<Map.Entry<Integer, Integer>> iter = counts.entrySet().iterator();
        Map.Entry<Integer, Integer> anyEntry = iter.next();
        counts.remove(anyEntry.getKey());
        for (int i = 0; i <= anyEntry.getValue(); i++) {
            rec(cumulated, results, counts);
            cumulated.add(anyEntry.getKey());
        }
        for (int i = 0; i <= anyEntry.getValue(); i++) cumulated.remove(cumulated.size() - 1);
        counts.put(anyEntry.getKey(), anyEntry.getValue());
    }


    public static List<List<Integer>> subsetsWithDup1(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> results = new ArrayList<>();
        rec(0, new ArrayList<>(), results, nums);
        return results;
    }

    private static void rec(int cur, List<Integer> cumulated, List<List<Integer>> results, int[] NUMS) {
        results.add(new ArrayList<>(cumulated));

        for (int i = cur; i < NUMS.length; i++) {
            if (i != cur && NUMS[i - 1] == NUMS[i]) {
                continue;
            }
            cumulated.add(NUMS[i]);
            rec(i + 1, cumulated, results, NUMS);
            cumulated.remove(cumulated.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(subsetsWithDup1(new int[]{1, 2, 2}));
    }

}

