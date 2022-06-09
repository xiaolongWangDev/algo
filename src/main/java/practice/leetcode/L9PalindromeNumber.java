package practice.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class L9PalindromeNumber {
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x == 0) return true;
        List<Integer> digits = new ArrayList<>();
        while (x > 0) {
            digits.add(x % 10);
            x = x / 10;
        }
        for (int i = 0; i < digits.size() / 2; i++) {
            if (!Objects.equals(digits.get(i), digits.get(digits.size() - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean smartAssIsPalindrome(int x) {
        if (x < 0) return false;
        if (x == 0) return true;

        int revertedNumber = 0;
        // when reverted number is no smaller than the remaining for the first time,
        // it means one of the following:
        // 1. reverted number is of the same length (e.g. 1221 1121)
        // 2. reverted number is 1 digit's longer (e.g. 121)
        // 3. reverted number is 2 digits' longer (e.g 1321)
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        return x == revertedNumber || x == revertedNumber / 10;
    }

    public static void main(String[] args) {
//        System.out.println(isPalindrome(1001));
        System.out.println(smartAssIsPalindrome(1321));
    }
}
