package array;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// a variant of the rain water collection problem
// this one only relates to the 2 bounds' height and any height in between won't affect
public class Problem_CollectRainWater2 {

    public int collect(int[] heights) {
        Map<Integer, List<Integer>> minAndMaxIndex = new HashMap<>();
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            for (int j = 1; j <= height; j++) {
                List<Integer> existingMaxAndMin = minAndMaxIndex.getOrDefault(j, List.of(Integer.MAX_VALUE, Integer.MIN_VALUE));
                minAndMaxIndex.put(j, List.of(Math.min(existingMaxAndMin.get(0), i), Math.max(existingMaxAndMin.get(1), i)));
            }
        }

        int max = 0;
        for (Map.Entry<Integer, List<Integer>> entry : minAndMaxIndex.entrySet()) {
            max = Math.max(max, entry.getKey() * (entry.getValue().get(1) - entry.getValue().get(0) + 1));
        }
        return max;
    }

    /*
    the key observation:
    L .......... R
    when the value of R is larger than that of L,
    Changing R's value won't get you a better result. So shrinking the R side bound is unnecessary.
    and vice versa.
    the follow algorithm does a good job pruning those branches
     */
    public int collect2(int[] heights) {
        int l = 0;
        int r = heights.length - 1;
        int lMaxHeight = -1;
        int rMaxHeight = -1;
        int max = 0;
        while (l <= r) {
            boolean taller = false;
            if (heights[l] > lMaxHeight) {
                lMaxHeight = heights[l];
                taller = true;
            }
            if (heights[r] > rMaxHeight) {
                rMaxHeight = heights[r];
                taller = true;
            }
            if (taller) {
                max = Math.max((r - l + 1) * Math.min(lMaxHeight, rMaxHeight), max);
            }

            if (heights[l] < heights[r]) {
                l++;
            } else if (heights[l] > heights[r]) {
                r--;
            } else {
                l++;
                r--;
            }

        }
        return max;
    }

    public static void main(String[] args) {
        Problem_CollectRainWater2 p = new Problem_CollectRainWater2();
        System.out.println(p.collect(new int[]{10, 8, 12, 5, 2, 4, 11, 3, 7}));
        System.out.println(p.collect2(new int[]{10, 8, 12, 5, 2, 4, 11, 3, 7}));
    }
}
