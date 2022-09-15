package practice.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class L2007FindOriginalArrayFromDoubledArray {
    public static int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 != 0) return new int[0];
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i : changed) {
            counts.put(i, counts.getOrDefault(i, 0) + 1);
        }

        int remCount = changed.length / 2;
        int[] res = new int[remCount];
        int i = 0;

        List<Integer> keys = counts.keySet().stream().sorted().collect(Collectors.toList());

        for (int x : keys) {
            if (counts.get(x) > counts.getOrDefault(x + x, 0))
                return new int[0];
            for (int j = 0; j < counts.get(x); ++j) {
                res[i++] = x;
                counts.put(x + x, counts.get(x + x) - 1);
            }
        }

        return res;
    }

    public static int[] findOriginalArrayAnother(int[] changed) {
        if (changed.length % 2 != 0) return new int[0];
        Arrays.sort(changed);

        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : changed) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        int remCount = changed.length / 2;
        int[] res = new int[remCount];

        Integer zeros = counts.get(0);
        int added = 0;
        if (zeros != null) {
            if (zeros % 2 == 1) return new int[0];
            added += zeros / 2;
            remCount -= zeros / 2;
        }

        int i = added * 2;
        while (remCount > 0) {
            int num = changed[i];
            int curCount = counts.get(changed[i]);
            if (curCount < 0) return new int[0];
            if (curCount > 0) {
                Integer doubleNumCount = counts.get(2 * changed[i]);
                if (doubleNumCount == null || doubleNumCount == 0) return new int[0];
                counts.put(num, curCount - 1);
                counts.put(2 * num, doubleNumCount - 1);
                remCount--;
                res[added++] = num;
            }
            i++;
        }

        return res;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(findOriginalArrayFast(new int[]{1, 3, 4, 2, 6, 8})));
//        System.out.println(Arrays.toString(findOriginalArrayFast(new int[]{1, 2, 2, 2, 4})));
//        System.out.println(Arrays.toString(findOriginalArrayFast(new int[]{0, 0, 0, 0})));
        System.out.println(Arrays.toString(findOriginalArrayAnother(new int[]{0, 3, 2, 4, 6, 0})));
        System.out.println(Arrays.toString(findOriginalArrayAnother(new int[]{1, 1, 2, 4})));
    }
}
