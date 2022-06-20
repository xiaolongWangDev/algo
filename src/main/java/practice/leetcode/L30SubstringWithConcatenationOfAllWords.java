package practice.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L30SubstringWithConcatenationOfAllWords {
    public static List<Integer> findSubstring(String s, String[] words) {

        Map<String, Integer> wordCounts = new HashMap<>();

        int wordLen = words[0].length();
        int matchLen = wordLen * words.length;

        List<Integer> res = new ArrayList<>();
        for (int start = 0; start < wordLen; start++) {
            wordCounts.clear();
            for (var word : words) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }

            int r = start;
            for (int l = start; l + matchLen - 1 < s.length(); l += wordLen) {
                if (r < l) r = l;
                while (r + wordLen - 1 < s.length()) {
                    var wordCan = s.substring(r, r + wordLen);
                    if (!wordCounts.containsKey(wordCan) || wordCounts.get(wordCan) == 0) break;
                    r = r + wordLen;
                    wordCounts.put(wordCan, wordCounts.get(wordCan) - 1);
                }
                if (r - l == matchLen) {
                    res.add(l);
                }

                wordCounts.computeIfPresent(s.substring(l, l + wordLen), (k, v) -> v + 1);
            }
        }

        return res;
    }

    public static List<Integer> findAllOccurrenceOfWord(String s, String word) {
        List<Integer> res = new ArrayList<>();
        int i;
        int wordLen = word.length();
        int base = 0;
        while (true) {
            i = s.indexOf(word);
            if (i == -1) break;
            res.add(base + i);
            base = base + i + 1;
            if (i + wordLen < s.length()) {
                s = s.substring(i + 1);
            } else {
                s = "";
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(findAllOccurrenceOfWord("aacaaacaa", "aa"));
//        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
//        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"}));
//        System.out.println(findSubstring("abarfoofoobarthefoobarman", new String[]{"foo","bar","the"}));
        System.out.println(findSubstring("aaaaaaaaaaaaaa", new String[]{"aa", "aa"}));
    }
}
