package practice.leetcode;

public class L29DivideTwoIntegers {
    public static int divide(int dividend, int divisor) {
        boolean negative = false;
        if (dividend < 0) {
            negative = true;
        } else {
            dividend = -dividend;
        }
        if (divisor < 0) {
            negative = !negative;
        } else {
            divisor = -divisor;
        }
        int quotient = 0;
        while (dividend <= divisor) {
            int toDivide = divisor;
            int q = -1;
            while ((toDivide + toDivide) < 0 && dividend <= toDivide + toDivide) {
                q = q + q;
                toDivide = toDivide + toDivide;
            }
            dividend -= toDivide;
            quotient += q;
        }

        if (negative) {
            return quotient;
        } else {
            return quotient == Integer.MIN_VALUE ? Integer.MAX_VALUE : -quotient;
        }
    }

    public static void main(String[] args) {
//        System.out.println(divide(-2147483648, -1));
//        System.out.println(divide(-2147483648, 1));
//        System.out.println(divide(-2147483648, 4));
        System.out.println(divide(2147483647, 3));

    }
}
