package practice.leetcode;

public class L35SearchInsertPosition {
    // Zoned Binary Search - find first element that's not less than the target
    public static int searchInsert(int[] nums, int target) {
        int l = -1;
        int r = nums.length;
        while (l != r - 1) {
            int m = l + (r - l) / 2;
            if (nums[m] < target) {
                l = m;
            } else {
                r = m;
            }
        }
        return r;
    }
}
