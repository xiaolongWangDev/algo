package practice.zuosbook.chapter1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MountsSeeEachOther {
    static int solve(int[] heights) {
        int tallestIndex = -1;
        int tallest = 0;
        for (int i = 0; i < heights.length; i++) {
            if (tallest < heights[i]) {
                tallest = heights[i];
                tallestIndex = i;
            }
        }

        // now we found the first tallest mount, and we'll start iterating from there
        int cur = tallestIndex;
        int count = 0;
        List<List<Integer>> allPairs = new ArrayList<>();
        Stack<LinkedList<Integer>> maxStack = new Stack<>();
        do {
            while (!maxStack.isEmpty() && heights[maxStack.peek().peekLast()] < heights[cur]) {
                LinkedList<Integer> poppedIndexes = maxStack.pop();
                int n = poppedIndexes.size();
                count += 2 * n + n * (n - 1) / 2;

                // this part is extra, it's for printing all the pair indexes.
                // the stack won't be empty because the max value is pushed in first,
                // and we'll only pop things out when cur value is greater than that
                int leftIndex = maxStack.peek().peekLast();
                int rightIndex = cur;
                addPairs(allPairs, poppedIndexes, leftIndex, rightIndex);
            }
            if (maxStack.isEmpty() || heights[maxStack.peek().peekLast()] != heights[cur]) {
                maxStack.push(new LinkedList<>());
            }
            maxStack.peek().addLast(cur);
            cur = cur + 1 == heights.length ? 0 : cur + 1;
        } while (cur != tallestIndex);

        while (!maxStack.isEmpty()) {
            if (maxStack.size() > 2) {
                LinkedList<Integer> poppedIndexes = maxStack.pop();
                int n = poppedIndexes.size();
                count += 2 * n + n * (n - 1) / 2;

                // this part is extra, it's for printing all the pair indexes.
                int leftIndex = maxStack.peek().peekLast();
                int rightIndex = tallestIndex;
                addPairs(allPairs, poppedIndexes, leftIndex, rightIndex);
            } else if (maxStack.size() == 2) {
                LinkedList<Integer> poppedIndexes = maxStack.pop();
                int n = poppedIndexes.size();
                int maxHeightMountCount = maxStack.peek().size();
                if (maxHeightMountCount > 1) {
                    count += 2 * n + n * (n - 1) / 2;
                } else {
                    count += n + n * (n - 1) / 2;
                }

                // this part is extra, it's for printing all the pair indexes.
                int leftIndex = maxStack.peek().peekLast();
                int rightIndex = tallestIndex;
                addPairs(allPairs, poppedIndexes, leftIndex, rightIndex);
            } else {
                LinkedList<Integer> poppedIndexes = maxStack.pop();
                int n = poppedIndexes.size();
                count += n * (n - 1) / 2;

                // this part is extra, it's for printing all the pair indexes.
                for (int i : poppedIndexes) {
                    for (int j : poppedIndexes) {
                        if (i != j) {
                            allPairs.add(List.of(i, j));
                        }
                    }
                }
            }
        }

        System.out.println(allPairs);

        return count;

    }

    private static void addPairs(List<List<Integer>> allPairs, LinkedList<Integer> poppedIndexes, int leftIndex, int rightIndex) {
        for (int i : poppedIndexes) {
            allPairs.add(List.of(i, leftIndex));
            if (leftIndex != rightIndex) allPairs.add(List.of(i, rightIndex));
            for (int j : poppedIndexes) {
                if (i != j) {
                    allPairs.add(List.of(i, j));
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solve(new int[]{4, 4, 5, 3, 2, 5, 4, 3, 5, 4, 2, 4}));
        System.out.println(solve(new int[]{4, 1, 5, 3, 2}));
    }
}
