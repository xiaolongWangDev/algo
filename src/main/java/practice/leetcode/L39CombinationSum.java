package practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class L39CombinationSum {

    public static class Info {
        boolean good;
        List<List<Integer>> oldList;
        List<List<Integer>> newList;

        public Info() {
        }

        public Info(List<List<Integer>> oldList, List<List<Integer>> newList) {
            this.good = true;
            this.oldList = oldList;
            this.newList = newList;
        }
    }

    /**
     * \      0      1      2      3      4      5      6      7
     * 2 [ true, false,  true, false,  true, false,  true, false]
     * 3 [ true, false,  true,  true,  true,  true,  true,  true]
     * 6 [ true, false,  true,  true,  true,  true,  true,  true]
     * 7 [ true, false,  true,  true,  true,  true,  true,  true]
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Info[] dp = new Info[target + 1];
        dp[0] = new Info(List.of(), List.of(List.of()));
        for (int i = 1; i < dp.length; i++) dp[i] = new Info();
        List<List<Integer>> result = new ArrayList<>();
        for (int candidate : candidates) {
            for (int j = candidate; j <= target; j++) {
                if (dp[j - candidate].good || dp[j].good) {
                    List<List<Integer>> oldList = new ArrayList<>();
                    List<List<Integer>> newList = new ArrayList<>();

                    if (dp[j].good) {
                        oldList.addAll(dp[j].oldList);
                        oldList.addAll(dp[j].newList);
                    }

                    if (dp[j - candidate].good) {
                        for (List<Integer> combination : dp[j - candidate].oldList) {
                            List<Integer> newCombination = new ArrayList<>(combination);
                            newCombination.add(candidate);
                            newList.add(newCombination);
                        }
                        for (List<Integer> combination : dp[j - candidate].newList) {
                            List<Integer> newCombination = new ArrayList<>(combination);
                            newCombination.add(candidate);
                            newList.add(newCombination);
                        }
                    }

                    dp[j] = new Info(oldList, newList);
                } else {
                    dp[j] = new Info();
                }

                if (j == target) {
                    if (dp[target].good) result.addAll(dp[j].newList);
                }
            }
            System.out.println(Arrays.stream(dp).map(o -> o.good).collect(Collectors.toList()));
        }
        return result;
    }

    public static List<List<Integer>> combinationSumBacktrack(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSumBacktrackRec(0, 0, new ArrayList<>(), result, target, candidates);
        return result;
    }

    public static void combinationSumBacktrackRec(int i, int sum, List<Integer> cumulated, List<List<Integer>> result, int TARGET, int[] CANDIDATES) {
        if (sum == TARGET) {
            result.add(List.copyOf(cumulated));
            return;
        }

        if (i == CANDIDATES.length) {
            return;
        }

        int newSum = sum;
        int added = 0;
        while (newSum <= TARGET) {
            combinationSumBacktrackRec(i + 1, newSum, cumulated, result, TARGET, CANDIDATES);
            newSum += CANDIDATES[i];
            cumulated.add(CANDIDATES[i]);
            added++;
        }

        // remove elements added in this rec
        for (int j = added; j > 0; j--) {
            cumulated.remove(cumulated.size() - 1);
        }
    }

    public static void main(String[] args) {
//        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
//        System.out.println(combinationSum(new int[]{1, 2}, 1));
        System.out.println(combinationSumBacktrack(new int[]{2, 3, 6, 7}, 7));
        System.out.println(combinationSumBacktrack(new int[]{1, 2}, 1));
    }
}
