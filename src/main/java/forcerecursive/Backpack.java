package forcerecursive;

public class Backpack {

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

    public static void main(String[] args) {
        Backpack algo = new Backpack();
        System.out.println(algo.findMaxValue(0, new Item[]{new Item(1, 10), new Item(1, 1), new Item(3, 10), new Item(5, 3), new Item(5, 3)}, 5));
    }
}
