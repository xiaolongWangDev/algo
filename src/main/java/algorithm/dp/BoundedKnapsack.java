package algorithm.dp;

import helper.MultiItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoundedKnapsack {
    // smartly create virtual goods, 1, 2, 4, 8, ... remainder
    private static List<MultiItem> repack(MultiItem orig) {
        int remainingCount = orig.count;
        int newCount = 1;
        List<MultiItem> res = new ArrayList<>();
        while (remainingCount >= newCount) {
            res.add(new MultiItem(newCount, newCount * orig.value, newCount * orig.weight));
            remainingCount -= newCount;
            newCount *= 2;
        }
        if (remainingCount != 0) {
            res.add(new MultiItem(remainingCount, remainingCount * orig.value, remainingCount * orig.weight));
        }

        return res;
    }


    public static int maxValueSumOfAnyValidWeight(MultiItem[] items, int capacity) {
        List<MultiItem> newItems = new ArrayList<>();
        for (var item : items) {
            newItems.addAll(repack(item));
        }

        int[] dp = new int[capacity + 1];

        for (var item : newItems) {
            for (int j = capacity; j >= item.weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - item.weight] + item.value);
            }
            System.out.println(Arrays.toString(dp));
        }

        return dp[capacity];
    }

    /**            0  1  2  3  4  5  6  7  8  9  10
     * 1/2/3      [0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2]
     * 2/4/6      [0, 0, 0, 2, 2, 2, 4, 4, 4, 6, 6]
     * 4/8/12     [0, 0, 0, 2, 2, 2, 4, 4, 4, 6, 6]
     * 1/2/3      [0, 0, 0, 2, 2, 2, 4, 4, 4, 6, 6]
     * 1/4/3      [0, 0, 0, 4, 4, 4, 6, 6, 6, 8, 8]
     * 1/3/2      [0, 0, 3, 4, 4, 7, 7, 7, 9, 9, 9]
     * 2/6/4      [0, 0, 3, 4, 6, 7, 9,10,10,13,13]
     */
    public static void main(String[] args) {
        System.out.println(maxValueSumOfAnyValidWeight(new MultiItem[]{
                new MultiItem(8, 2, 3),
                new MultiItem(1, 4, 3),
                new MultiItem(3, 3, 2),
        }, 10));
    }
}
