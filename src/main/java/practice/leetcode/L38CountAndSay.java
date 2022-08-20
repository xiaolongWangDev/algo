package practice.leetcode;

public class L38CountAndSay {
    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String subDigitString = countAndSay(n - 1);
        Integer prevDigit = null;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : subDigitString.toCharArray()) {
            int digit = c - '0';
            if (prevDigit != null && digit != prevDigit) {
                sb.append(count);
                sb.append(prevDigit);
                count = 0;
            }
            prevDigit = digit;
            count++;
        }
        if (count > 0) {
            sb.append(count);
            sb.append(prevDigit);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(3));
        System.out.println(countAndSay(4));
        System.out.println(countAndSay(5));
    }
}
