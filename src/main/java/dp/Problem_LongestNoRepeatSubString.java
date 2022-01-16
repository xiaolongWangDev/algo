package dp;

import java.util.HashMap;
import java.util.Map;

public class Problem_LongestNoRepeatSubString {
    public int find(char[] input) {
        if (input.length == 0) return 0;
        int[] lengthEndsAt = new int[input.length];
        Map<Character, Integer> lastSeenAt = new HashMap<>();
        lengthEndsAt[0] = 1;
        lastSeenAt.put(input[0], 0);
        for (int i = 1; i < input.length; i++) {
            Integer j = lastSeenAt.get(input[i]);
            j = j == null ? -1 : j;
            lengthEndsAt[i] = j != -1 ? Math.min(lengthEndsAt[i - 1] + 1, i - j) : lengthEndsAt[i - 1] + 1;
            lastSeenAt.put(input[i], i);
        }

        int max = 0;
        int j = -1;
        for (int i = 1; i < lengthEndsAt.length; i++) {
            if (max < lengthEndsAt[i]) {
                max = lengthEndsAt[i];
                j = i;
            }
        }
        for (int i = j - max + 1; i <= j; i++) {
            System.out.print(input[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        Problem_LongestNoRepeatSubString p = new Problem_LongestNoRepeatSubString();
        System.out.println(p.find("abcabcbb".toCharArray()));
        System.out.println(p.find("bbbbb".toCharArray()));
        System.out.println(p.find("pwwkew".toCharArray()));
    }

}
