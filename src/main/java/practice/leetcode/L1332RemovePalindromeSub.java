package practice.leetcode;

public class L1332RemovePalindromeSub {
    // mostly important: it says "remove subsequence", so you can remove all "a" in 1 move
    public static int removePalindromeSub(String s) {
        if (s.equals(new StringBuilder(s).reverse().toString())) {
            return 1;
        } else {
            return 2;
        }
    }
}
