package dp;

public class Problem_Backpack {

    private static class Item {
        int value;
        int weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    public int findMaxValue(int i, Item[] items, int remainingCapacity) {
        if (i >= items.length || remainingCapacity <= 0) {
            return 0;
        }
        Item item = items[i];
        if (remainingCapacity >= item.weight) {
            return Math.max(item.value + findMaxValue(i + 1, items, remainingCapacity - item.weight), findMaxValue(i + 1, items, remainingCapacity));
        } else {
            return findMaxValue(i + 1, items, remainingCapacity);
        }
    }

    /*
    i \ capacity  0 1 2 3 4 5 6
    0             0 1 1 5 6 6 10
    1             0 1 1 5 6 6 10
    2             0 0 0 5 5 5 10
    3             0 0 0 5 5 5 10
    4             0 0 0 5 5 5 5
    5             0 0 0 0 0 0 0
     */
    public int findMaxValueDp(Item[] items, int capacity) {
        int[] memo = new int[capacity + 1];
        int[] buff = new int[capacity + 1];
        for (int i = items.length - 1; i >= 0; i--) {
            Item item = items[i];
            for (int j = 0; j <= capacity; j++) {
                if (j >= item.weight) {
                    buff[j] = Math.max(item.value + memo[j - item.weight], memo[j]);
                } else {
                    buff[j] = memo[j];
                }
            }
            memo = buff;
            buff = new int[capacity + 1];
        }
        return memo[capacity];
    }

    /*
    i \ capacity  0 1 2 3 4 5 6
    0             1 1 0 2 2 0 1
    1             1 1 0 2 2 0 1
    2             1 0 0 2 0 0 1
    3             1 0 0 2 0 0 1
    4             1 0 0 1 0 0 0
    5             1 0 0 0 0 0 0
     */
    public int findAllSolutionExactMatch(Item[] items, int capacity) {
        int[] memo = new int[capacity + 1];
        memo[0] = 1;
        int[] buff = new int[capacity + 1];
        for (int i = items.length - 1; i >= 0; i--) {
            Item item = items[i];
            buff[0] = 1;
            for (int j = 1; j <= capacity; j++) {
                if (j >= item.weight) {
                    buff[j] = memo[j - item.weight] + memo[j];
                } else {
                    buff[j] = memo[j];
                }
            }
            memo = buff;
            buff = new int[capacity + 1];
        }
        return memo[capacity];
    }

    public int findAllSolutionLessThan(Item[] items, int capacity) {
        int[] memo = new int[capacity + 1];
        memo[0] = 1;
        int[] buff = new int[capacity + 1];
        for (int i = items.length - 1; i >= 0; i--) {
            Item item = items[i];
            buff[0] = 1;
            for (int j = 1; j <= capacity; j++) {
                if (j >= item.weight) {
                    buff[j] = memo[j - item.weight] + memo[j];
                } else {
                    buff[j] = memo[j];
                }
            }
            memo = buff;
            buff = new int[capacity + 1];
        }
        int sum = 0;
        for (int i : memo) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        Problem_Backpack algo = new Problem_Backpack();
        Item[] items = {new Item(1, 10), new Item(1, 1), new Item(3, 10), new Item(5, 3), new Item(5, 3)};
        int cap = 6;
        System.out.println(algo.findMaxValue(0, items, cap));
        System.out.println(algo.findMaxValueDp(items, cap));
        System.out.println(algo.findAllSolutionExactMatch(items, cap));
        System.out.println(algo.findAllSolutionLessThan(items, cap));
    }
}
