package twopointer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_LongestTargetSum {
    public List<Integer> findSimple(int[] input, int target) {
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
        return lResult == null ? null : List.of(lResult, rResult);
    }

    public List<Integer> findMedium(int[] input, int target) {
        if (input.length == 0) return null;
        Map<Integer, Integer> leadingSumIndex = new HashMap<>();
        int leadingSum = 0;
        Integer lResult = null;
        Integer rResult = null;
        for (int i = 0; i < input.length; i++) {
            leadingSum += input[i];
            if (leadingSumIndex.get(leadingSum - target) != null) {
                int l = leadingSumIndex.get(leadingSum - target) + 1;
                if (lResult == null) lResult = l;
                if (rResult == null) rResult = i;
                if (i - l > rResult - lResult) {
                    lResult = l;
                    rResult = i;
                }
            }
            leadingSumIndex.put(leadingSum, i);
        }

        return lResult == null ? null : List.of(lResult, rResult);
    }

    public static void main(String[] args) {
        Problem_LongestTargetSum p = new Problem_LongestTargetSum();
        System.out.println(p.findSimple(new int[]{1, 2, 3, 1, 1}, 5));
        System.out.println(p.findMedium(new int[]{1, -2, 3, -1, -4}, 1));
    }
}
