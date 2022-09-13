package practice.leetcode;

public class L41FirstMissingPositiveNumber {
    public static int firstMissingPositive(int[] nums) {
        // get rid of non-positive values because they are useless, and we are going to give negative number a new meaning
        // use nums.length + 1 that won't pollute the "hashmap" in the next step
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = nums.length + 1;
            }
        }

        // use the array as a hashmap without wiping out the numeric information.
        // we achieve that by making the original number on the spot negative
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (index >= 0 && index < nums.length) {
                nums[index] = -Math.abs(nums[index]);
            }
        }

        // any non-negative number now indicates the corresponding number is not set
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        // if they are all set, it means the array has all the numbers from [1, nums.length]
        return nums.length + 1;
    }

    public static void main(String[] args) {
//        System.out.println(firstMissingPositive(new int[]{3, 4, -1, 1}));
        System.out.println(firstMissingPositive(new int[]{1, 1}));
    }
}
