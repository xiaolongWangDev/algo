package practice.leetcode;

import java.util.Arrays;

public class L31NextPermutation {
    public static void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        for (; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                break;
            }
        }

        if (i > 0) {
            for (int j = nums.length - 1; j >= i; j--) {
                if (nums[j] > nums[i - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[i - 1];
                    nums[i - 1] = temp;
                    break;
                }
            }
        }

        // 3 4 5 -> 3
        // 3 4 5 6 -> 4
        for (int j = 0; j < (nums.length - i) / 2; j++) {
            int temp = nums[i + j];
            nums[i + j] = nums[nums.length - 1 - j];
            nums[nums.length - 1 - j] = temp;
        }
    }

    public static void main(String[] args) {
//        int[] in = new int[]{1, 2, 3, 4};
        int[] in = new int[]{1, 3, 2};
        nextPermutation(in);
        System.out.println(Arrays.toString(in));
    }
}
