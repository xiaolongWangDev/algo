package tobeorganized.array;

public class Problem_KthSmallestFromTwoOrderedList {
    // k is 1 based
    public int kth(int[] a, int[] b, int k) {
        int[] longer = a.length > b.length ? a : b;
        int[] shorter = a == longer ? b : a;
        int longerLen = longer.length;
        int shorterLen = shorter.length;

        if (k > longerLen + shorterLen) {
            throw new IllegalArgumentException("k is greater than the total length");
        }

        if (shorterLen >= k) {
            return upMedian(shorter, 0, k - 1, longer, 0, k - 1);
        } else if (k > longerLen) {
            if (shorter[k - longerLen - 1] > longer[longerLen - 1]) return shorter[k - longerLen - 1];
            if (longer[k - shorterLen - 1] > shorter[shorterLen - 1]) return longer[k - shorterLen - 1];
            return upMedian(shorter, k - longerLen, shorterLen - 1, longer, k - shorterLen, longerLen - 1);
        } else {
            if (longer[k - shorterLen - 1] > shorter[shorterLen - 1]) return longer[k - shorterLen - 1];
            return upMedian(shorter, 0, shorterLen - 1, longer, k - shorterLen, k - 1);
        }
    }

    private int upMedian(int[] a, int aLeft, int aRight, int[] b, int bLeft, int bRight) {
        int aMiddle = aLeft + ((aRight - aLeft) >> 1);
        int bMiddle = bLeft + ((bRight - bLeft) >> 1);
        if (a[aMiddle] == b[bMiddle]) {
            return a[aMiddle];
        }
        if (aRight == aLeft) {
            return Math.min(a[aMiddle], b[bMiddle]);
        }
        if ((aRight - aLeft + 1) % 2 == 0) {
            // a b c d
            // w x y z
            // if b > x, c d w x disqualify
            // recurse to arr1[l, mid] arr2[mid+1, r]
            if (a[aMiddle] > b[bMiddle]) {
                return upMedian(a, aLeft, aMiddle, b, bMiddle + 1, bRight);
            } else {
                return upMedian(a, aMiddle + 1, aRight, b, bLeft, bMiddle);
            }
        } else {
            // a b c
            // x y z
            // b > y, then b > a, b > y > x. b, c cannot be. x cannot be
            // then if y > a, y is the result
            // otherwise, recurse to arr1[l, m-1] , arr2[m+1,r]
            if (a[aMiddle] > b[bMiddle]) {
                if (b[bMiddle] > a[aMiddle - 1]) return b[bMiddle];
                return upMedian(a, aLeft, aMiddle - 1, b, bMiddle + 1, bRight);
            } else {
                if (a[aMiddle] > b[bMiddle - 1]) return a[aMiddle];
                return upMedian(a, aMiddle + 1, aRight, b, bLeft, bMiddle - 1);
            }
        }
    }

    public static void main(String[] args) {
        Problem_KthSmallestFromTwoOrderedList p = new Problem_KthSmallestFromTwoOrderedList();
        int[] a = new int[]{1, 2, 3, 4};
        int[] b = new int[]{5, 6, 7, 8};
        int[] b1 = new int[]{1, 3, 4, 5};
        int[] b2 = new int[]{1, 1, 1, 1};
        System.out.println(p.upMedian(a, 0, 3, b, 0, 3));
        System.out.println(p.upMedian(b, 0, 3, a, 0, 3));
        System.out.println(p.upMedian(a, 0, 3, b1, 0, 3));
        System.out.println(p.upMedian(a, 0, 3, b2, 0, 3));


        int[] s = new int[]{1, 10, 42, 79, 80, 1000};
        int[] l = new int[]{23, 24, 56, 62, 90, 100, 200, 400, 1002, 2002};
        for(int i = 1; i <= s.length + l.length; i++) {
            System.out.println(p.kth(s, l, i));
//            System.out.println(p.kth(l, s, i));
        }

    }
}
