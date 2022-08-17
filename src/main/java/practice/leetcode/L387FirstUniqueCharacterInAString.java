package practice.leetcode;

import java.util.Arrays;

public class L387FirstUniqueCharacterInAString {
    public static int firstUniqChar(String s) {
        int[] first = new int[26];
        Arrays.fill(first, -1);
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            first[c - 'a'] = first[c - 'a'] == -1 ? i : -2;
        }
        int min = -1;
        for (int f : first) {
            if (f >= 0) {
                if (min == -1) {
                    min = f;
                } else {
                    min = Math.min(min, f);
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("leetcode"));
        System.out.println(firstUniqChar("loveleetcode"));
        System.out.println(firstUniqChar("aabb"));
    }
}
