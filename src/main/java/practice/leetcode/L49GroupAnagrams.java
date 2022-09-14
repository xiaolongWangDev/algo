package practice.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class L49GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] cArray = s.toCharArray();
            Arrays.sort(cArray);
            String orderedStr = Arrays.toString(cArray);
            map.putIfAbsent(orderedStr, new ArrayList<>());
            map.get(orderedStr).add(s);
        }

        return new ArrayList<>(map.values());
    }

    public static List<List<String>> groupAnagramsSmartHash(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            String hash = smartHash(s);
            map.putIfAbsent(hash, new ArrayList<>());
            map.get(hash).add(s);
        }

        return new ArrayList<>(map.values());
    }

    private static String smartHash(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            sb.append(count[i]);
            sb.append('#');
        }
        return sb.toString();
    }

}
