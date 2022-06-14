package practice.leetcode;

import java.util.Arrays;

public class L27RemoveElement {
    public static int removeElement(int[] nums, int val) {
        int i = 0;
        int cur = 0;
        while (i < nums.length) {
            if (nums[i] != val) {
                nums[cur++] = nums[i];
            }
            i++;
        }
        return cur;
    }

    public static void main(String[] args) {
        int[] in = new int[]{0,1,2,2,3,0,4,2};
        System.out.println(removeElement(in, 2));
        System.out.println(Arrays.toString(in));
    }
}
