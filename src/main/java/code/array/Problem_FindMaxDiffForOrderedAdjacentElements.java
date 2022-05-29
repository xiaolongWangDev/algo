package code.array;

public class Problem_FindMaxDiffForOrderedAdjacentElements {
    public int find(int[] input) {
        Integer[] max = new Integer[input.length + 1];
        Integer[] min = new Integer[input.length + 1];

        int maxOverall = Integer.MIN_VALUE;
        int minOverall = Integer.MAX_VALUE;
        for (int o : input) {
            if (maxOverall < o) maxOverall = o;
            if (minOverall > o) minOverall = o;
        }
        if (maxOverall == minOverall) return 0;
        double bucketSize = (maxOverall - minOverall) / (input.length + 1.0);

        for (int o : input) {
            int bucketIndex = o == maxOverall ? input.length : (int) Math.floor(o / bucketSize);
            max[bucketIndex] = max[bucketIndex] == null ? o : Math.max(o, max[bucketIndex]);
            min[bucketIndex] = min[bucketIndex] == null ? o : Math.min(o, min[bucketIndex]);
        }
        int maxDiff = 0;
        Integer prevMax = max[0];
        for (int i = 1; i < input.length + 1; i++) {
            if (min[i] != null) {
                maxDiff = Math.max(maxDiff, min[i] - prevMax);
                prevMax = max[i];
            }
        }
        return maxDiff;
    }

    public static void main(String[] args) {
        Problem_FindMaxDiffForOrderedAdjacentElements p = new Problem_FindMaxDiffForOrderedAdjacentElements();
        System.out.println(p.find(new int[]{9, 0, 17, 4, 63, 72, 65, 67, 99}));
    }
}
