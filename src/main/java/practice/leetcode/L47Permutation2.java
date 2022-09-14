package practice.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class L47Permutation2 {

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Long> counts = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        rec(nums.length, new LinkedList<>(), res, counts);
        return res;
    }

    private static void rec(int len, LinkedList<Integer> cumulated, List<List<Integer>> results, Map<Integer, Long> counts) {

        if (cumulated.size() == len) {
            results.add(new ArrayList<>(cumulated));
            return;
        }

        counts.forEach((num, count) -> {
            if(count > 0) {
                counts.put(num, count - 1);
                cumulated.add(num);
                rec(len, cumulated, results, counts);
                cumulated.pollLast();
                counts.put(num, count);
            }
        });
    }

    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{2, 2, 1, 1}));
    }
}
