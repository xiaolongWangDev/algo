package practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class L56MergeIntervals {
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt((int[] interval) -> interval[0]));
        int[] prev = null;
        List<int[]> results = new ArrayList<>();
        for (int[] interval : intervals) {
            if (prev != null) {
                if (Math.max(prev[0], interval[0]) <= Math.min(prev[1], interval[1])) {
                    prev = new int[]{Math.min(prev[0], interval[0]), Math.max(prev[1], interval[1])};
                } else {
                    results.add(prev);
                    prev = interval;
                }
            } else {
                prev = interval;
            }
        }
        results.add(prev);
//        results.forEach(o -> System.out.println(Arrays.toString(o)));
        return results.toArray(int[][]::new);
    }

    public static void main(String[] args) {
        merge(new int[][]{{1, 4}, {2, 5}, {6, 8}, {9, 10}});
    }
}
