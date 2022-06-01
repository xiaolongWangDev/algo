package tobeorganized.string;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private static class TrieNode {
        int pass;
        int end;
        Character id; /* not a required member, it's just to better identify the cleanup process */

        public TrieNode(Character id) {
            this.id = id;
        }

        Map<Character, TrieNode> children = new HashMap<>();

        /* in a NON GC language, this simulates a destruction method */
        public void clear() {
            System.out.printf("node %c is cleared\n", id);
        }
    }

    TrieNode root = new TrieNode(null);

    public void addWord(String word) {
        TrieNode cur = root;
        cur.pass++;
        for (char c : word.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode(c));
            }
            cur = cur.children.get(c);
            cur.pass++;
        }
        cur.end++;
    }

    public int countWord(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                return 0;
            }
            cur = cur.children.get(c);
        }
        return cur.end;
    }

    public int countPrefix(String prefix) {
        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                return 0;
            }
            cur = cur.children.get(c);
        }
        return cur.pass;
    }

    public void deleteWordWithCleanup(String word) {
        if (countWord(word) == 0) return;

        TrieNode cur = root;
        cur.pass--;
        for (char c : word.toCharArray()) {
            TrieNode child = cur.children.get(c);
            if (child.pass == 1) {
                cur.children.remove(c);
            }
            if(cur.pass == 0) {
                cur.clear();
            }
            cur = child;
            cur.pass--;

        }
        cur.end--;
        if(cur.pass == 0) {
            cur.clear();
        }
    }

    public void deleteWord(String word) {
        if (countWord(word) == 0) return;

        TrieNode cur = root;
        cur.pass--;
        for (char c : word.toCharArray()) {
            TrieNode child = cur.children.get(c);
            if (child.pass == 1) {
                cur.children.remove(c);
                return;
            }
            cur = child;
            cur.pass--;
        }
        cur.end--;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.addWord("abc");
        trie.addWord("abcd");
        trie.addWord("bcd");
        trie.addWord("abc");
        System.out.println(trie.countWord("ab"));
        System.out.println(trie.countWord("abc"));
        System.out.println(trie.countWord("abcd"));
        System.out.println(trie.countPrefix("a"));
        System.out.println(trie.countPrefix("abcde"));
        trie.deleteWordWithCleanup("abc");
        System.out.println(trie.countWord("abc"));
        System.out.println(trie.countPrefix("ab"));
        trie.deleteWordWithCleanup("abc");
        System.out.println(trie.countWord("abc"));
        System.out.println(trie.countPrefix("ab"));
        System.out.println(trie.countWord("abcd"));
        trie.deleteWordWithCleanup("abcd");
        System.out.println(trie.countPrefix("a"));
    }

}
