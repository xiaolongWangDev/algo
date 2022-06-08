package practice.leetcode;

public class L7Reverse {
    public static int reverse(int x) {
        boolean negative = x < 0;
        if (negative) {
            x = -x;
        }
        long cumulated = 0L;
        while (x > 0) {
            cumulated = 10 * cumulated + x % 10;
            x = x / 10;
        }
        if (negative) {
            if (cumulated > Integer.MAX_VALUE + 1L) return 0;
            return (int) -cumulated;
        } else {
            if (cumulated > Integer.MAX_VALUE) return 0;
            return (int) cumulated;
        }
    }

    public static int reverseNotUsingLong(int x) {
        boolean negative = x < 0;
        if (negative) {
            x = -x;
        }
        int cumulated = 0;
        int underflowGuard = Integer.MIN_VALUE / 10;
        int underflowGuardDigit = 8;

        // use the negative half because it's longer
        while (x > 0) {
            int digit = x % 10;
            if (cumulated < underflowGuard
                    || (cumulated == underflowGuard
                        && ((negative && digit > 8) || (!negative && digit > 7)))) {
                return 0;
            }
            cumulated = 10 * cumulated - digit;
            x = x / 10;
        }
        if (negative) {
            return cumulated;
        } else {
            return -cumulated;
        }
    }

    public static void main(String[] args) {
        System.out.println(reverseNotUsingLong(2000000007));
        System.out.println(reverseNotUsingLong(-2000000003));
        System.out.println(reverseNotUsingLong(-123));
        System.out.println(reverseNotUsingLong(56643));
    }
}
