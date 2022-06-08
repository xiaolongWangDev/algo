package algorithm.dp;

import helper.Item;

import java.util.Arrays;

public class UnboundedKnapsack {
    public static int maxValueSumOfAnyValidWeight(Item[] items, int capacity) {
        int[] dp = new int[capacity + 1];

        for (Item item : items) {
            // start j from the item weight.
            // for cases when j < item.weight, current item cannot be picked, it falls back to f(i-i, j), which is
            // already in dp[j] because we "compressed" the 2d array
            for (int j = item.weight; j <= capacity; j++) {
                dp[j] = Math.max(dp[j - item.weight] + item.value, dp[j]);
            }
            System.out.println(Arrays.toString(dp));
        }

        return dp[capacity];
    }

    public static void main(String[] args) {
        Item[] items = {new Item(1, 10),
                new Item(1, 1),
                new Item(3, 10),
                new Item(5, 3),
                new Item(5, 2)};

        int capacity = 11;

        System.out.println(maxValueSumOfAnyValidWeight(items, capacity));
    }
}
