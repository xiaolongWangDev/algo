package algorithm.dp;

import helper.Item;

import java.util.Arrays;

public class ZeroOneKnapsack {
    /**
     * i\c 0  1  2  3  4  5  6  7  8  9 10 11
     * -1 [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
     * 0 [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1]
     * 1 [0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2]
     * 2 [0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 4]
     * 3 [0, 1, 1, 5, 6, 6, 6, 6, 6, 6, 6, 6]
     * 4 [0, 1, 1, 5, 6, 6,10,11,11,11,11,11]
     */
    public static int maxValueSumOfAnyValidWeight(Item[] items, int capacity) {
        int[] dp = new int[capacity + 1];

        for (Item item : items) {
            // for this problem we could avoid the use of the temp array by starting iterating from the right
            // this is because the new value only depends on the value on its left
            // i.e. f(i, j) depends on f(i-1,j) and f(i-1, j - weight)
//            int[] temp = new int[capacity + 1];
            for (int j = capacity; j > -1; j--) {
                dp[j] = Math.max(j - item.weight >= 0 ? dp[j - item.weight] + item.value : 0, dp[j]);
//                temp[j] = Math.max(j - item.weight >= 0 ? dp[j - item.weight] + item.value : 0, dp[j]);
            }
//            dp = temp;
            System.out.println(Arrays.toString(dp));
        }

        return dp[capacity];
    }

    /**
     * i\c 0  1  2  3  4  5  6  7  8  9 10 11
     * -1 [0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]
     * 0 [0,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1]
     * 1 [0, 1,-1,-1,-1,-1,-1,-1,-1,-1, 3, 4]
     * 2 [0, 1,-1,-1,-1,-1,-1,-1,-1,-1, 3, 4]
     * 3 [0, 1,-1, 5, 6,-1,-1,-1,-1,-1, 3, 4]
     * 4 [0, 1,-1, 5, 6,-1,10,11,-1,-1, 3, 4]
     */
    public static int maxValueSumOfTargetWeight(Item[] items, int capacity) {
        int[] dp = new int[capacity + 1];
        dp[0] = 0;
        for (int j = 1; j <= capacity; j++) {
            dp[j] = -1;
        }

        for (Item item : items) {
            int[] temp = new int[capacity + 1];
            for (int j = 0; j <= capacity; j++) {
                if (j - item.weight >= 0 && dp[j - item.weight] != -1) {
                    temp[j] = Math.max(dp[j - item.weight] + item.value, dp[j]);
                } else {
                    temp[j] = dp[j];
                }
            }
            dp = temp;
            System.out.println(Arrays.toString(dp));
        }

        return dp[capacity];
    }


    public static void main(String[] args) {
        Item[] items = {new Item(1, 10),
                new Item(1, 1),
                new Item(3, 10),
                new Item(5, 3),
                new Item(5, 3)};

        int capacity = 11;

        System.out.println(maxValueSumOfAnyValidWeight(items, capacity));
        System.out.println(maxValueSumOfTargetWeight(items, capacity));


    }
}
