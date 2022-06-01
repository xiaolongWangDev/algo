package tobeorganized.heap;

import java.util.*;
import java.util.stream.Collectors;

public class Problem_KMostFrequent {
    public List<String> getMostFrequent(int k, String[] input) {
        if (k < 1) return List.of();
        Map<String, Integer> counts = new HashMap<>();
        for (String s : input) {
            counts.put(s, counts.getOrDefault(s, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.add(entry);
            } else if (minHeap.peek().getValue() < entry.getValue()) {
                minHeap.poll();
                minHeap.add(entry);
            }
        }
        return minHeap.stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Problem_KMostFrequent p = new Problem_KMostFrequent();
        System.out.println(p.getMostFrequent(2, new String[]{"abc", "abc", "bck", "efg", "efg", "efg", "bck", "bck"}));
    }
}
