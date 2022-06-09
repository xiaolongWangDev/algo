package practice.leetcode;

public class L167TwoSum2 {
    public static int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                return new int[]{l + 1, r + 1};
            }
            if (sum > target) {
                r--;
            } else {
                l++;
            }
        }
        return null;
    }
}
