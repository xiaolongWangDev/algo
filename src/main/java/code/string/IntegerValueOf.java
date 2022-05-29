package code.string;

public class IntegerValueOf {
    public int valueOf(String s) {
        if (!isValid(s)) {
            throw new IllegalArgumentException();
        }
        boolean isNeg = s.charAt(0) == '-';
        int valHolder = 0;
        int startIndex = isNeg ? 1 : 0;
        int underflowGuard = Integer.MIN_VALUE / 10;
        int underflowGuardDigit = Integer.MIN_VALUE % 10;
        for (int i = startIndex; i < s.length(); i++) {
            int digit = s.charAt(i) - '0';
            if (valHolder < underflowGuard || (valHolder == underflowGuard && digit > -underflowGuardDigit)) {
                throw new IllegalArgumentException();
            }
            valHolder = 10 * valHolder - digit;
        }
        if (valHolder == Integer.MIN_VALUE && !isNeg) throw new IllegalArgumentException();
        return isNeg ? valHolder : -valHolder;
    }

    private boolean isValid(String s) {
        if (s.length() == 0) {
            return false;
        }
        if (s.charAt(0) == '0' && s.length() != 1) return false;
        if (s.charAt(0) == '-' && (s.length() == 1 || s.charAt(1) == '0')) return false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') {
                if (i != 0 || s.charAt(i) != '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IntegerValueOf algo = new IntegerValueOf();
        System.out.println(algo.isValid("-"));
        System.out.println(algo.isValid("$"));
        System.out.println(algo.isValid("00"));
        System.out.println(algo.isValid("01"));
        System.out.println(algo.isValid("-12340!"));
        System.out.println(algo.isValid("-1"));
        System.out.println(algo.isValid("1000"));
        System.out.println(algo.isValid("-12340"));

        System.out.println(algo.valueOf("32768"));
        System.out.println(algo.valueOf("-2147483648"));
        System.out.println(algo.valueOf("2147483647"));
        // failures
        System.out.println(algo.valueOf("5000000000"));
        System.out.println(algo.valueOf("-2147483649"));
        System.out.println(algo.valueOf("2147483648"));
    }
}
