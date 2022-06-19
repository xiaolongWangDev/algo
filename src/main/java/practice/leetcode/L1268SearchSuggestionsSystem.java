package practice.leetcode;

import java.util.ArrayList;
import java.util.List;

public class L1268SearchSuggestionsSystem {


    public static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        int pass;
        int end;

        public static void addWord(TrieNode root, String word) {
            char[] charArray = word.toCharArray();
            TrieNode cur = root;
            cur.pass++;
            for (char c : charArray) {
                int nextIndex = c - 'a';
                if (cur.next[nextIndex] == null) {
                    cur.next[nextIndex] = new TrieNode();
                }
                cur = cur.next[nextIndex];
                cur.pass = cur.pass + 1;
            }
            cur.end = cur.end + 1;
        }

        public static List<String> findWordWithPrefix(TrieNode root, char[] prefix, int prefixEndIndex, int limit) {
            List<String> res = new ArrayList<>();
            TrieNode cur = root;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < prefixEndIndex; i++) {
                char c = prefix[i];
                int nextIndex = c - 'a';
                if (cur.next[nextIndex] == null) {
                    return res;
                }
                cur = cur.next[nextIndex];
                sb.append(c);
            }

            // dfs
            dfs(res, cur, sb, limit);
            return res;
        }

        private static void dfs(List<String> res, TrieNode cur, StringBuilder sb, int limit) {
            if (res.size() < limit) {
                if (cur.end > 0) {
                    res.add(sb.toString());
                }

                for (var i = 0; i < cur.next.length; i++) {
                    if (res.size() < limit && cur.next[i] != null) {
                        sb.append((char) ('a' + i));
                        dfs(res, cur.next[i], sb, limit);
                        sb.deleteCharAt(sb.length() - 1);
                    }
                }
            }
        }
    }


    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNode root = new TrieNode();
        for (var p : products) {
            TrieNode.addWord(root, p);
        }

        var prefix = searchWord.toCharArray();
        List<List<String>> res = new ArrayList<>();
        for (int i = 1; i <= prefix.length; i++) {
            res.add(TrieNode.findWordWithPrefix(root, prefix, i, 3));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(suggestedProducts(new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"}, "mouse"));
        System.out.println(suggestedProducts(new String[]{"bags", "baggage", "box", "cloths"}, "bags"));
    }
}
