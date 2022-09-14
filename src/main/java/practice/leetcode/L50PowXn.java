package practice.leetcode;

public class L50PowXn {
    public static double myPow(double x, int n) {

        if (x == 0) return 0;
        if (n == 0) return 1;

        int rem = n;
        double product = 1;
        double multiplier = x;

        // n can be represented as binary, don't need to multiply n times,
        // but only logN times
        // e.g. n = 100, it's binary is 1100100
        while (rem != 0) {
            boolean set = rem % 2 == 1;
            if (set) {
                product *= multiplier;
            }
            multiplier = multiplier * multiplier;
            rem = rem >>> 1;
        }

        return n < 0 ? 1 / product : product;
    }

    public static void main(String[] args) {

        System.out.println(myPow(2, 10));
    }
}
