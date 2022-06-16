package practice.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class L1048LongestStringChain {
    public static int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparing(String::length));
        Map<String, Integer> dp = new HashMap<>();
        int result = 0;
        for (var word : words) {
            dp.put(word, 1);
            for (int i = 0; i < word.length(); i++) {
                StringBuilder sb = new StringBuilder(word);
                var tryWord = sb.delete(i, i + 1).toString();
                Integer chainEndWithTryWord = dp.get(tryWord);
                if (chainEndWithTryWord != null && chainEndWithTryWord + 1 > dp.get(word)) {
                    dp.put(word, chainEndWithTryWord + 1);
                }
            }
            result = Math.max(dp.get(word), result);
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
