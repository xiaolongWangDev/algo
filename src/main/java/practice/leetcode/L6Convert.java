package practice.leetcode;

public class L6Convert {
    public static String convert(String s, int numRows) {
        // 0   4   8   12
        // 1 3 5 7 9 11
        // 2   6   10

        // 0     6      12
        // 1   5 7   11
        // 2 4   8 10
        // 3     9

        if (numRows == 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int k = 2 * numRows - 2;
            int j = 0;
            if (i == 0) {
                while (j * k < s.length()) {
                    sb.append(s.charAt(j * k));
                    j++;
                }
            } else if (i == numRows - 1) {
                while (j * k + numRows - 1 < s.length()) {
                    sb.append(s.charAt(j * k + numRows - 1));
                    j++;
                }
            } else {
                while (j * k + i < s.length() || j * k + k - i < s.length()) {
                    if (j * k + i < s.length()) {
                        sb.append(s.charAt(j * k + i));
                    }
                    if (j * k + k - i < s.length()) {
                        sb.append(s.charAt(j * k + k - i));
                    }
                    j++;
                }

            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        System.out.println(convert("PAYPALISHIRING", 3));
//        System.out.println(convert("PAYPALISHIRING", 4));
        System.out.println(convert("AB", 2));
    }
}
