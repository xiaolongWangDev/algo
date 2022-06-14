package practice.leetcode;

import java.util.Arrays;

public class L26RemoveDuplicatesFromSortedArray {
    public static int removeDuplicates(int[] nums) {
        int i = 0;
        int cur = 0;
        Integer prev = null;
        while (i < nums.length) {
            if (prev == null || prev != nums[i]) {
                nums[cur] = nums[i];
                cur ++;
            }
            prev = nums[i];
            i++;
        }

        return cur;
    }

    public static void main(String[] args) {
        int[] input = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(input));
        System.out.println(Arrays.toString(input));
    }
}
