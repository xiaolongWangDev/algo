package practice.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * use hashmap to keep the remainder
 */
public class L1TwoSum {
    public int[] solve(int[] nums, int target) {
        // remainder, index
        int[] result = new int[2];
        Map<Integer, Integer> remain = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            // check first, so we don't grab the number itself back
            Integer otherIndex = remain.get(num);
            if(remain.get(num) != null) {
                result[0] = i;
                result[1] = otherIndex;
            }

            remain.put(target - num, i);

        }

        return result;
    }

    public static void main(String[] args) {
        var q = new L1TwoSum();
        var input1 = new int[]{3, 3};
        System.out.println(Arrays.toString(q.solve(input1, 6)));
    }
}
