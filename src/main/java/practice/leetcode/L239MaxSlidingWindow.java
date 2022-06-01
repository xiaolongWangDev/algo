package practice.leetcode;

import datastructure.MonotonicQueue;
import helper.ValueAndIndex;

import java.util.Arrays;
import java.util.Comparator;

public class L239MaxSlidingWindow {
    public int[] getExtremesPerWindow(int[] input, int windowSize, boolean getMax) {
        MonotonicQueue<ValueAndIndex<Integer>> mq = new MonotonicQueue<>(Comparator.comparing(ValueAndIndex::getValue), getMax);

        int[] results = new int[input.length - windowSize + 1];

        for (int i = 0; i < input.length; i++) {
            Integer val = input[i];
            mq.add(new ValueAndIndex<>(val, i));
            int windowLeftIndex = i - windowSize + 1;
            // keep evicting values that have been moved out of the window
            while (mq.peekExtreme().getIndex() < windowLeftIndex) {
                mq.popExtreme();
            }
            // only start adding after window has full coverage
            if (windowLeftIndex >= 0) {
                results[windowLeftIndex] = mq.peekExtreme().getValue();
            }
        }
        return results;
    }

    public static void main(String[] args) {
        var q = new L239MaxSlidingWindow();
        var input1 = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        var input2 = new int[]{7, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(q.getExtremesPerWindow(input1, 3, true)));
        System.out.println(Arrays.toString(q.getExtremesPerWindow(input2, 3, true)));
        System.out.println(Arrays.toString(q.getExtremesPerWindow(input1, 3, false)));
        System.out.println(Arrays.toString(q.getExtremesPerWindow(input2, 3, false)));
    }
}
