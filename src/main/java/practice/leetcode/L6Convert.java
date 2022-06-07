package practice.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            if (i == 0 || i == numRows - 1) {
                while (j * k + i < s.length()) {
                    sb.append(s.charAt(j * k + i));
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

    public static String convertWithExtraSpace(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(s.length(), numRows); i++) {
            rows.add(new StringBuilder());
        }

        int currentRow = 0;
        boolean goingDown = true;

        for (int i = 0; i < s.length(); i++) {
            rows.get(currentRow).append(s.charAt(i));
            if (i != 0 && (currentRow == 0 || currentRow == numRows - 1)) {
                goingDown = !goingDown;
            }
            currentRow = goingDown ? currentRow + 1 : currentRow - 1;
        }

        return rows.stream().map(StringBuilder::toString).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
        System.out.println(convert("PAYPALISHIRING", 4));
        System.out.println(convert("AB", 2));
        System.out.println(convertWithExtraSpace("PAYPALISHIRING", 3));
        System.out.println(convertWithExtraSpace("PAYPALISHIRING", 4));
        System.out.println(convertWithExtraSpace("AB", 2));
    }
}
