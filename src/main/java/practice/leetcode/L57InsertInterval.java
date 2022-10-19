package practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L57InsertInterval {
    public static int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> newIntervals = new ArrayList<>();
        int mergedStart = newInterval[0];
        int mergedEnd = newInterval[1];
        int i = 0;
        while (i < intervals.length && (intervals[i][1] < newInterval[0])) newIntervals.add(intervals[i++]);
        while (i < intervals.length && Math.max(intervals[i][0], newInterval[0]) <= Math.min(intervals[i][1], newInterval[1])) {
            mergedStart = Math.min(intervals[i][0], mergedStart);
            mergedEnd = Math.max(intervals[i][1], mergedEnd);
            i++;
        }
        newIntervals.add(new int[]{mergedStart, mergedEnd});
        while (i < intervals.length) newIntervals.add(intervals[i++]);

        return newIntervals.toArray(new int[newIntervals.size()][2]);
    }

    public static void main(String[] args) {
//        var res = insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8});
        var res = insert(new int[][]{}, new int[]{5,7});
        for (var row : res) {
            System.out.println(Arrays.toString(row));
        }
    }
}
