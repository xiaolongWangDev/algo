package practice.other;

import datastructure.MonotonicStack;
import helper.ExtremeType;
import helper.ValueAndIndex;

import java.util.Arrays;
import java.util.Comparator;

public class P5788_template_MonotonicStack {
    public static int[] solve(int[] input) {
        MonotonicStack<ValueAndIndex<Integer>> ms = new MonotonicStack<>(Comparator.comparingInt(ValueAndIndex::getValue), ExtremeType.MIN);
        int[] res = new int[input.length];
        for (int i = input.length - 1; i >= 0; i--) {
            int val = input[i];
            var valAndIndex = new ValueAndIndex<>(val, i);
            ms.evictInvalid(valAndIndex);
            if (ms.isEmpty()) {
                res[i] = -1;
            } else {
                res[i] = ms.peekExtreme().getIndex();
            }
            ms.add(valAndIndex);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solve(new int[]{1, 4, 2, 3, 5})));
    }
}
