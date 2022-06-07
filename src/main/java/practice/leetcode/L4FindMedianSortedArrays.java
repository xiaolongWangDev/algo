package practice.leetcode;

public class L4FindMedianSortedArrays {

    // idea similar to merge sort
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;

        int m = (nums1.length + nums2.length) / 2;
        int prevVal;
        int currentVal = 0;
        do {
            prevVal = currentVal;
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    currentVal = nums1[i];
                    i++;
                } else {
                    currentVal = nums2[j];
                    j++;
                }
            } else if (i == nums1.length) {
                currentVal = nums2[j];
                j++;
            } else {
                currentVal = nums1[i];
                i++;
            }
        } while (i + j <= m);
        return (nums1.length + nums2.length) % 2 == 0 ? (prevVal + currentVal) / 2.0 : currentVal;
    }


    public static double findMedianSortedArraysBinarySearch(int[] num1, int[] num2) {
        int l = 0;
        int[] a = num1.length > num2.length ? num2 : num1;
        int[] b = num1.length > num2.length ? num1 : num2;

        int r = a.length - 1;
        int kth = (1 + a.length + b.length) / 2;

        while (true) {
            int i = r == -1 ? -1 : l + (r - l) / 2;
            int j = kth - (i + 1) - 1;

            Integer num1TakenLast = i == -1 ? null : a[i];
            Integer num1NotTakenFirst = i + 1 == a.length ? null : a[i + 1];
            Integer num2TakenLast = j == -1 ? null : b[j];
            Integer num2NotTakenFirst = j + 1 == b.length ? null : b[j + 1];

            if (lte(num1TakenLast, num2NotTakenFirst) && lte(num2TakenLast, num1NotTakenFirst)) {
                int leftValue = num1TakenLast != null && num2TakenLast != null ?
                        Math.max(num1TakenLast, num2TakenLast) :
                        num1TakenLast != null ?
                                num1TakenLast :
                                num2TakenLast;
                if ((a.length + b.length) % 2 == 1) {
                    return leftValue;
                } else {
                    int rightValue = num1NotTakenFirst != null && num2NotTakenFirst != null ?
                            Math.min(num1NotTakenFirst, num2NotTakenFirst) :
                            num1NotTakenFirst != null ?
                                    num1NotTakenFirst :
                                    num2NotTakenFirst;

                    return (leftValue + rightValue) / 2.0;
                }
            } else if (!lte(num2TakenLast, num1NotTakenFirst)) {
                l = i + 1;
            } else {
                r = i - 1;
            }
        }
    }

    private static boolean lte(Integer v1, Integer v2) {
        if (v1 == null || v2 == null) {
            return true;
        }
        return v1 <= v2;
    }

    public static void main(String[] args) {
//        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
//        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}));
//        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        System.out.println(findMedianSortedArraysBinarySearch(new int[]{1, 3}, new int[]{2}));
        System.out.println(findMedianSortedArraysBinarySearch(new int[]{1, 3}, new int[]{2, 4}));
        System.out.println(findMedianSortedArraysBinarySearch(new int[]{1, 2}, new int[]{3, 4}));
    }

}
