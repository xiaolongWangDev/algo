package practice.zuosbook.chapter1;

import java.util.*;

public class MaxMinDeltaLteNum {
    public static int solve(int[] data, int num) {
        Deque<Integer> maxQueue = new LinkedList<>();
        Deque<Integer> minQueue = new LinkedList<>();
        int r = 0;
        int[] res = new int[data.length];
        for (int l = 0; l < data.length; l++) {
            // for each l, find the nearest r that makes the delta invalid, grow the windows as r increases
            while (r < data.length && isValueValid(data, r, num, minQueue, maxQueue)) {
                while (!maxQueue.isEmpty() && data[maxQueue.peekLast()] <= data[r]) {
                    maxQueue.pollLast();
                }
                maxQueue.addLast(r);
                while (!minQueue.isEmpty() && data[minQueue.peekLast()] >= data[r]) {
                    minQueue.pollLast();
                }
                minQueue.addLast(r);
                r++;
            }
            res[l] = r - l;
            // at the end of each loop for l, check if the extreme in the max/min queues need to be evicted
            if (minQueue.peekFirst() == l) {
                minQueue.pollFirst();
            }
            if (maxQueue.peekFirst() == l) {
                maxQueue.pollFirst();
            }
        }

        // printing some eye candy
        for (int i = 0; i < res.length; i++) {
            for (int k = i; k < i + res[i]; k++) {
                System.out.printf("(%d,%d) ", i, k);
            }
        }
        return Arrays.stream(res).sum();
    }

    // this allows us to check without polluting the queues
    private static boolean isValueValid(int[] data, int i, int num, Deque<Integer> minQueue, Deque<Integer> maxQueue){
        int newMax = maxQueue.isEmpty()? data[i] : Math.max(data[maxQueue.peekFirst()], data[i]);
        int newMin = minQueue.isEmpty()? data[i] : Math.min(data[minQueue.peekFirst()], data[i]);
        return newMax - newMin <= num;
    }

    public static void main(String[] args) {
        System.out.println(solve(new int[]{3, 1, 6, 5, 4, 2, 7}, 2));
        System.out.println(solve(new int[]{3, 1, 6, 5, 4, 2, 7}, 3));
    }
}
