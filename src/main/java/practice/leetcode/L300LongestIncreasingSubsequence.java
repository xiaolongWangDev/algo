package practice.leetcode;

import java.util.ArrayList;
import java.util.List;

public class L300LongestIncreasingSubsequence {
    public static int lengthOfLIS(int[] nums) {
        Integer[] seqEnds = new Integer[nums.length];
        int len = 0;
        for (int num : nums) {
            int l = -1;
            int r = len;
            while (l + 1 < r) {
                int m = l + (r - l) / 2;
                if (seqEnds[m] < num) {
                    l = m;
                } else {
                    r = m;
                }
            }

            if (r == len) {
                seqEnds[len] = num;
                len++;
            } else {
                seqEnds[r] = num;
            }
        }

        return len;
    }


    /*
    for the list 10,1 ,7 ,3, 6, 4
    constructs the following arrays
    1: [1]
    2: [1,3]
    3: [1,3,4]
    It keeps the increasing subsequence that's closest to the current index.

    Whenever we are at an index, we do a binary search in the above structure, by comparing the last element in
    each list with current value. The goal is to find one list would accept current value at the end and still be
    increasing.

    the binary search here is zoned binary search. It determines the boundary of valid and invalid lists.

    the monotonic property is when lisOfLenM.get(lisOfLenM.size() - 1) < num, the shorter lists' end elements would all
    be smaller than num. This is guaranteed by how we added values

     */
    public static List<Integer> lis(int[] nums) {
        List<List<Integer>> seqEnds = new ArrayList<>();
        for (int num : nums) {
            int l = -1;
            int r = seqEnds.size();
            while (l + 1 < r) {
                int m = l + (r - l) / 2;
                List<Integer> lisOfLenM = seqEnds.get(m);
                if (lisOfLenM.get(lisOfLenM.size() - 1) < num) {
                    l = m;
                } else {
                    r = m;
                }
            }

            if (r == seqEnds.size()) {
                ArrayList<Integer> newLenLis = new ArrayList<>();
                if (seqEnds.size() > 0) {
                    newLenLis.addAll(seqEnds.get(seqEnds.size() - 1));
                }
                newLenLis.add(num);
                seqEnds.add(newLenLis);
            } else {
                seqEnds.get(r).set(seqEnds.get(r).size() - 1, num);
            }
        }

        return seqEnds.get(seqEnds.size() - 1);
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10, 1, 7, 3, 6, 4, 5, 2, 9}));
        System.out.println(lengthOfLIS(new int[]{7, 7, 7, 7}));
        System.out.println(lis(new int[]{10, 1, 7, 3, 6, 4, 5, 2, 9}));
    }
}
