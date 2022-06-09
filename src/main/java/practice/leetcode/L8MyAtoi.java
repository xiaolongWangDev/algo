package practice.leetcode;

public class L8MyAtoi {

    public static int myAtoi(String s) {
        boolean metFistDigit = false;
        boolean isNegative = false;
        boolean trimedLeadingSpaces = false;
        boolean signMet = false;
        int underflowGuard = Integer.MIN_VALUE / 10;
        int underflowGuardDigit = 8;
        int cumulated = 0;
        for (char c : s.toCharArray()) {
            if (isDigit(c)) {
                metFistDigit = true;
                int digit = c - '0';
                if (
                        cumulated < underflowGuard ||
                                cumulated == underflowGuard && (
                                        (isNegative && digit > underflowGuardDigit) ||
                                                (!isNegative && digit >= underflowGuardDigit)
                                )
                ) {
                    return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }

                // use the negative half, to get slightly larger range
                cumulated = 10 * cumulated - digit;
            } else {
                if (metFistDigit) break;
                if (c != ' ') trimedLeadingSpaces = true;
                if (trimedLeadingSpaces) {
                    if (c != '-' && c != '+') return 0;
                    if (signMet) return 0;
                    if (c == '-') isNegative = true;
                    signMet = true;
                }
            }
        }

        return isNegative ? cumulated : -cumulated;

    }

    private static boolean isDigit(Character c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("  -00009999  "));
    }
}
