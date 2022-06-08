package algorithm.dp;

import java.util.Arrays;

public class ZeroOneBackpack {
    private static class Item {
        int value;
        int weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    public static int solve(Item[] items, int capacity) {
        int[] dp = new int[capacity + 1];

        for (Item item : items) {
            int[] temp = new int[capacity + 1];
            for (int j = 0; j <= capacity; j++) {
                temp[j] = Math.max(j - item.weight >= 0 ? dp[j - item.weight] + item.value : 0, dp[j]);
            }
            dp = temp;
            System.out.println(Arrays.toString(dp));
        }

        int max = 0;
        for (int j = 0; j <= capacity; j++) {
            max = Math.max(max, dp[j]);
        }

        return max;
    }

    public static void main(String[] args) {
        Item[] items = {new Item(1, 10),
                new Item(1, 1),
                new Item(3, 10),
                new Item(5, 3),
                new Item(5, 3)};

        int capacity = 11;

        System.out.println(solve(items, capacity));


    }
}
