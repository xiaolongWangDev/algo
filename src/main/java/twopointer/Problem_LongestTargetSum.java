package twopointer;

import java.util.List;

public class Problem_LongestTargetSum {
    public List<Integer> find(int[] input, int target) {
        if (input.length == 0) return null;
        int l = 0;
        int r = 0;
        int sum = input[l];
        Integer lResult = null;
        Integer rResult = null;
        while (r < input.length) {
            if (sum < target) {
                r++;
                if (r < input.length) sum += input[r];
            } else if (sum > target) {
                sum -= input[l];
                l++;
            } else {
                if (lResult == null) lResult = l;
                if (rResult == null) rResult = r;
                if (r - l > rResult - lResult) {
                    lResult = l;
                    rResult = r;
                }
                sum -= input[l];
                l++;
            }
        }
        return List.of(lResult, rResult);
    }

    public static void main(String[] args) {
        Problem_LongestTargetSum p = new Problem_LongestTargetSum();
        System.out.println(p.find(new int[]{1, 2, 3, 1, 1}, 5));
    }
}
