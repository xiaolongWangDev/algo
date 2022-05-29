package code.twopointer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_LongestTargetSum {
    public List<Integer> findInAllPositive(int[] input, int target) {
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

    public List<Integer> findInAllInteger(int[] input, int target) {
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
            leadingSumIndex.putIfAbsent(leadingSum, i);
        }

        return lResult == null ? null : List.of(lResult, rResult);
    }

    public List<Integer> findNoGreaterThan(int[] input, int target) {
        int[] smallestSum = new int[input.length];
        int[] smallestSumEnds = new int[input.length];
        for (int i = input.length - 1; i >= 0; i--) {
            if (i + 1 <= input.length - 1) {
                smallestSum[i] = smallestSum[i + 1] > 0 ? input[i] : input[i] + smallestSum[i + 1];
                smallestSumEnds[i] = smallestSum[i + 1] > 0 ? i : smallestSumEnds[i + 1];
            } else {
                smallestSum[i] = input[i];
                smallestSumEnds[i] = i;
            }
        }

        int starting = 0;
        int ending = smallestSumEnds[0];
        int cumulated = smallestSum[0];
        int lResult = -1;
        int rResult = -1;
        if (cumulated <= target) {
            lResult = starting;
            rResult = ending;
        }
        while (starting < smallestSum.length) {
            if (ending == smallestSum.length - 1) {
                // we cannot find a longer one
                break;
            }
            if (cumulated + smallestSum[ending + 1] <= target) {
                cumulated = cumulated + smallestSum[ending + 1];
                ending = smallestSumEnds[ending + 1];
            } else {
                starting++;
                if (starting < smallestSum.length) {
                    cumulated = cumulated - input[starting - 1];
                    if (starting > ending) {
                        cumulated = smallestSum[starting];
                        ending = smallestSumEnds[starting];
                    }
                }
            }
            if (cumulated <= target && ending - starting > rResult - lResult) {
                lResult = starting;
                rResult = ending;
            }
        }
        return List.of(lResult, rResult);
    }

    public static void main(String[] args) {
        Problem_LongestTargetSum p = new Problem_LongestTargetSum();
        System.out.println(p.findInAllPositive(new int[]{1, 2, 3, 1, 1}, 5));
        System.out.println(p.findInAllInteger(new int[]{1, -2, 3, -1, -4}, 1));
        System.out.println(p.findNoGreaterThan(new int[]{1, -2, 3, 1, 10, -4}, 1));
        System.out.println(p.findNoGreaterThan(new int[]{1, 2, 3, 4}, 1));
        System.out.println(p.findNoGreaterThan(new int[]{3, 2, 3, 4}, 1));
        System.out.println(p.findNoGreaterThan(new int[]{3, 2, -10, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 1));
    }
}
