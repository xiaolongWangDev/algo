package practice.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class L1338ReduceArraySizeToTheHalf {
    public static int minSetSize(int[] arr) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int i : arr) {
            counts.put(i, counts.getOrDefault(i, 0) + 1);
        }

        int size = 1;
        int total = 0;
//        List<Integer> sortedCounts = counts.values().stream().sorted(Comparator.comparingInt((Integer o) -> o).reversed()).collect(Collectors.toList());
//        for (int count : sortedCounts) {
//            total += count;
//            if (total >= arr.length / 2) return size;
//            size++;
//        }

        // use bucket sort to get O(n) time complexity for larger input
        // or we could just use a normal sort like the commented out code.
        int[] buckets = new int[100001];
        for (Integer count : counts.values()) {
            buckets[count]++;
        }
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] > 0) {
                for (int j = 0; j < buckets[i]; j++) {
                    total += i;
                    if (total >= arr.length / 2) return size;
                    size++;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(minSetSize(new int[]{3, 3, 3, 3, 5, 5, 5, 2, 2, 7}));
//        System.out.println(minSetSize(new int[]{1,2,3,4,5,6,7,8}));
    }
}
