package practice.leetcode;

import java.util.HashMap;
import java.util.Map;

public class L3LengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> met = new HashMap<>();
        int res = 0;
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            if (met.containsKey(s.charAt(r))) {
                // the max here is the most tricky thing to note
                // when we won't there's an existing occurrence, but if it's already left of l, it's not valid, so we just ignore that by keep l the same
                l = Math.max(l, met.get(s.charAt(r)) + 1);
            }
            res = Math.max(res, r - l + 1);
            met.put(s.charAt(r), r);
        }
        res = Math.max(res, s.length() - l);

        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("dvdf"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("cdd"));
        System.out.println(lengthOfLongestSubstring("abba"));
    }
}
