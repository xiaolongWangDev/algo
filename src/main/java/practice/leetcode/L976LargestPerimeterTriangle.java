package practice.leetcode;

import java.util.Arrays;

public class L976LargestPerimeterTriangle {
    public static int largestPerimeter(int[] nums) {
        if (nums.length < 3) return 0;
        Arrays.sort(nums);
        int k = nums.length - 1;
        while (k >= 2) {
            if (nums[k] < nums[k - 1] + nums[k - 2]) return nums[k - 2] + nums[k - 1] + nums[k];
            k--;
        }
        return 0;
    }
}
