package practice.leetcode;

import java.util.HashMap;
import java.util.Map;

public class L76MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        Map<Character, Integer> charCounts = new HashMap<>();
        for (Character c : t.toCharArray()) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        int l = 0, r = 0;
        int missingSum = charCounts.values().stream().mapToInt(o -> o).sum();
        Map<Character, Integer> missingCounts = new HashMap<>(charCounts);
        if (missingCounts.containsKey(s.charAt(r))) {
            missingCounts.put(s.charAt(r), missingCounts.get(s.charAt(r)) - 1);
            if (missingCounts.get(s.charAt(r)) >= 0) {
                missingSum--;
            }
        }
        Integer minStrStart = null;
        Integer minStrEnd = null;
        while (l <= r) {
            if (missingSum == 0) {
//                System.out.println(s.substring(l, r + 1));
                if (minStrStart == null || minStrEnd - minStrStart > r - l) {
                    minStrStart = l;
                    minStrEnd = r;
                }
                char leftChar = s.charAt(l);
                if(missingCounts.containsKey(leftChar)) {
                    missingCounts.put(leftChar, missingCounts.get(leftChar) + 1);
                    if(missingCounts.get(leftChar) > 0) {
                        missingSum ++;
                    }
                }
                l++;
            } else {
                r++;
                if (r == s.length()) break;
                char rightChar = s.charAt(r);
                if (missingCounts.containsKey(rightChar)) {
                    missingCounts.put(rightChar, missingCounts.get(rightChar) - 1);
                    if (missingCounts.get(rightChar) >= 0) {
                        missingSum--;
                    }
                }
            }
        }
        return minStrStart == null ? "" : s.substring(minStrStart, minStrEnd + 1);
    }

    public static void main(String[] args) {
//        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("a", "aa"));
    }
}
