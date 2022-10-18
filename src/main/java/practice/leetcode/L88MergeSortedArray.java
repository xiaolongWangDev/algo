package practice.leetcode;

public class L88MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int[] res = new int[m + n];
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                res[i + j] = nums1[i];
                i++;
            } else {
                res[i + j] = nums2[j];
                j++;
            }
        }
        while (i < m) {
            res[i + j] = nums1[i];
            i++;
        }
        while (j < n) {
            res[i + j] = nums2[j];
            j++;
        }
        System.arraycopy(res, 0, nums1, 0, res.length);
    }
}
