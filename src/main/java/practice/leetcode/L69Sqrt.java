package practice.leetcode;

public class L69Sqrt {
    public static int mySqrt(int x) {
        int l = 0;
        int r = x;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            long midSqr = ((long) mid) * mid;
            if (midSqr <= x) {
                l = mid;
            } else {
                r = mid;
            }
        }

        return l;
    }
}
