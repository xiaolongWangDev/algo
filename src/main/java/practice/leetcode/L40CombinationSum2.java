package practice.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class L40CombinationSum2 {

    static class NumAndCount {
        int num;
        int count;

        public NumAndCount(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target, Counter c) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> counts = new HashMap<>();
        for (int candidate : candidates) {
            counts.put(candidate, counts.getOrDefault(candidate, 0) + 1);
        }

        List<NumAndCount> numAndCounts = counts.entrySet().stream().map(o -> new NumAndCount(o.getKey(), o.getValue())).collect(Collectors.toList());
        rec(0, new LinkedList<>(), 0, res, numAndCounts, target, c);
        return res;
    }

    public static void rec(int curIndex, LinkedList<Integer> cumulated, int sum, List<List<Integer>> result, List<NumAndCount> NUM_AND_COUNTS, int TARGET, Counter c) {
        c.count += 1;
//        System.out.println(cumulated);
        if (sum == TARGET) {
            result.add(new ArrayList<>(cumulated));
            return;
        }

        if (curIndex == NUM_AND_COUNTS.size() || sum > TARGET) {
            return;
        }

        int i = NUM_AND_COUNTS.get(curIndex).count;
        while (i >= 0) {
            int newSum = sum + NUM_AND_COUNTS.get(curIndex).num * i;
            if (newSum <= TARGET) {
                for (int j = 0; j < i; j++) cumulated.add(NUM_AND_COUNTS.get(curIndex).num);
                rec(curIndex + 1, cumulated, newSum, result, NUM_AND_COUNTS, TARGET, c);
                for (int j = 0; j < i; j++) cumulated.pollLast();
            }
            i--;
        }
    }

    public static List<List<Integer>> combinationSum2BetterTrimming(int[] candidates, int target, Counter c) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(0, new LinkedList<>(), 0, results, candidates, target, c);
        return results;
    }

    private static void backtrack(int curIndex, LinkedList<Integer> cumulated, int sum, List<List<Integer>> result, int[] CANDIDATES, int TARGET, Counter c) {
        c.count += 1;
        if (sum == TARGET) {
            result.add(new ArrayList<>(cumulated));
            return;
        }

        for (int i = curIndex; i < CANDIDATES.length; ++i) {
            if (i > curIndex && CANDIDATES[i] == CANDIDATES[i - 1]) continue;
            if(sum + CANDIDATES[i] > TARGET) return; // this line makes all the difference
            // if adding current number makes the sum invalid, nothing more than this would make the sum valid.
            // and all numbers right to this number are no less than it. So, no valid results there
            cumulated.addLast(CANDIDATES[i]);
            backtrack(i + 1, cumulated, sum + CANDIDATES[i], result, CANDIDATES, TARGET, c);
            cumulated.pollLast();
        }
    }

    static class Counter {
        int count = 0;
    }

    public static void main(String[] args) {
        Counter c1 = new Counter();
        System.out.println(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8, c1));
        System.out.println(c1.count);
        System.out.println(combinationSum2(new int[]{2, 5, 2, 1, 2}, 5, c1));
        Counter c2 = new Counter();
        System.out.println(combinationSum2BetterTrimming(new int[]{10, 1, 2, 7, 6, 1, 5}, 8, c2));
        System.out.println(c2.count);
    }
}
