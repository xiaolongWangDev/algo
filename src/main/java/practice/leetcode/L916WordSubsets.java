package practice.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L916WordSubsets {
    public static List<String> wordSubsets(String[] words1, String[] words2) {
        Map<Character, Integer> word2LetterCounts = new HashMap<>();
        for (var word : words2) {
            Map<Character, Integer> tempWord2LetterCounts = getCountMap(word);
            tempWord2LetterCounts.forEach((k, v) -> {
                int count = word2LetterCounts.getOrDefault(k, 0);
                if (v > count) word2LetterCounts.put(k, v);
            });
        }
        List<String> result = new ArrayList<>();
        for (var word : words1) {
            var counts = getCountMap(word);
            if (hasAll(counts, word2LetterCounts)) {
                result.add(word);
            }
        }
        return result;
    }

    private static boolean hasAll(Map<Character, Integer> counts, Map<Character, Integer> word2LetterCounts) {
        for (Map.Entry<Character, Integer> entry : word2LetterCounts.entrySet()) {
            Character k = entry.getKey();
            Integer v = entry.getValue();
            int aCount = counts.getOrDefault(k, 0);
            if (aCount < v) return false;
        }
        return true;
    }

    private static Map<Character, Integer> getCountMap(String word) {
        Map<Character, Integer> tempWord2LetterCounts = new HashMap<>();
        for (var letter : word.toCharArray()) {
            int count = tempWord2LetterCounts.getOrDefault(letter, 0);
            tempWord2LetterCounts.put(letter, count + 1);
        }
        return tempWord2LetterCounts;
    }

    public static void main(String[] args) {
        System.out.println(wordSubsets(new String[]{"amazon", "apple", "facebook", "google", "leetcode"}, new String[]{"e", "o"}));
        System.out.println(wordSubsets(new String[]{"amazon", "apple", "facebook", "google", "leetcode"}, new String[]{"e", "l"}));
        System.out.println(wordSubsets(new String[]{"amazon", "apple", "facebook", "google", "leetcode"}, new String[]{"eoo", "le"}));
    }
}
