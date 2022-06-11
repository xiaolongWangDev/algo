package practice.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L17CombinationsOfAPhoneNumber {
    private static final Map<Character, List<Character>> m = new HashMap<>();

    static {
        m.put('2', List.of('a', 'b', 'c'));
        m.put('3', List.of('d', 'e', 'f'));
        m.put('4', List.of('g', 'h', 'i'));
        m.put('5', List.of('j', 'k', 'l'));
        m.put('6', List.of('m', 'n', 'o'));
        m.put('7', List.of('p', 'q', 'r', 's'));
        m.put('8', List.of('t', 'u', 'v'));
        m.put('9', List.of('w', 'x', 'y', 'z'));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        solve(0, new StringBuilder(), results, digits);
        return results;
    }

    private static void solve(int i, StringBuilder sb, List<String> results, String digits) {
        if (digits.length() == i) {
            if(sb.length() > 0) results.add(sb.toString());
            return;
        }
        for (Character c : m.get(digits.charAt(i))) {
            sb.append(c);
            solve(i + 1, sb, results, digits);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
