package array;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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

    public List<Integer> betterFind(int[] input) {
        List<List<Integer>> minEnds = new ArrayList<>();
        BinarySearch search = new BinarySearch();
        for (int value : input) {
            Integer targetIndex = search.minGreaterThan(minEnds, value, 0, minEnds.size() - 1, l -> l.get(l.size() - 1));
            if (targetIndex == null) {
                List<Integer> newRecord = minEnds.size() == 0 ? new ArrayList<>() : new ArrayList<>(minEnds.get(minEnds.size() - 1));
                newRecord.add(value);
                minEnds.add(newRecord);
            } else {
                minEnds.get(targetIndex).set(targetIndex, value);
            }
        }

        return minEnds.get(minEnds.size() - 1);
    }

    public static void main(String[] args) {
        Problem_LongestIncreasingSubSequence p = new Problem_LongestIncreasingSubSequence();
        int[] input = new int[]{10, 1, 7, 3, 6, 4, 5, 2};
        System.out.println(p.naiveFind(input));
        System.out.println(p.betterFind(input));
    }
}
