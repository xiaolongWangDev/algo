package tobeorganized.array;

import java.util.ArrayList;
import java.util.List;

import static algorithm.BinarySearch.findFirstGreaterThanOrEqual;

public class Problem_LongestIncreasingSubSequence {

    public List<Integer> naiveFind(int[] input) {
        List<List<Integer>> longestResultEndsAt = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            int max = Integer.MIN_VALUE;
            int k = -1;
            for (int j = 0; j < i; j++) {
                if (input[i] > input[j]) {
                    if (max < longestResultEndsAt.get(j).size()) {
                        max = longestResultEndsAt.get(j).size();
                        k = j;
                    }
                }
            }
            if (k == -1) {
                longestResultEndsAt.add(List.of(input[i]));
            } else {
                List<Integer> result = new ArrayList<>(longestResultEndsAt.get(k));
                result.add(input[i]);
                longestResultEndsAt.add(result);
            }
        }

        List<Integer> result = List.of();
        for (List<Integer> candidate : longestResultEndsAt) {
            if (result.size() < candidate.size()) {
                result = candidate;
            }
        }
        return result;
    }

    public Integer betterFindLen(int[] input) {
        // each index means the LIS of length i ends with a number minEnds[i]
        List<Integer> minEnds = new ArrayList<>();
        for (int value : input) {
            Integer targetIndex = findFirstGreaterThanOrEqual(value, minEnds.toArray(Integer[]::new));
            if (targetIndex == null) {
                minEnds.add(value);
            } else {
                minEnds.set(targetIndex, value);
            }
        }

        return minEnds.size();
    }

    public static void main(String[] args) {
        Problem_LongestIncreasingSubSequence p = new Problem_LongestIncreasingSubSequence();
        int[] input = new int[]{7,7,7,7};
        System.out.println(p.naiveFind(input));
        System.out.println(p.betterFindLen(input));
    }
}
