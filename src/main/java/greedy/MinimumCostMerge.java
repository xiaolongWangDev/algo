package greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumCostMerge {
    public int merge(List<Integer> input) {
        if (input.size() < 2) {
            return input.stream().reduce(0, Integer::sum);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.addAll(input);
        int result = 0;
        while (minHeap.size() > 1) {
            Integer first = minHeap.poll();
            Integer second = minHeap.poll();
            result += (first + second);
            minHeap.add(first + second);
        }
        return result;
    }

    public static void main(String[] args) {
        MinimumCostMerge algo = new MinimumCostMerge();
        System.out.println(algo.merge(List.of(10, 20, 30)));
    }
}
